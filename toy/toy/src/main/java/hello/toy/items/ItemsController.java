package hello.toy.items;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemsRepository itemsRepository;

    @GetMapping()
    public String items(Model model) {
        List<Items> items = itemsRepository.findAll();
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/add")
    public String itemsAddForm(@ModelAttribute("item") ItemsAddForm itemsAddForm) {

        return "items/addForm";
    }

    @PostMapping("/add")
    public String itemsAdd(@Validated @ModelAttribute("item") ItemsAddForm itemsAddForm, BindingResult bindingResult) {

        if(itemsAddForm.getPrice() != null && itemsAddForm.getQuantity() != null) {
            int resultPrice = 10000;
            int totalPrice  = itemsAddForm.getPrice() * itemsAddForm.getQuantity();
            if(totalPrice < resultPrice) {
                bindingResult.reject("totalPriceMin", new Object[]{resultPrice, totalPrice}, null);
            }
        }
        if(bindingResult.hasErrors()) {
            return "items/addForm";
        }
        Items newItems = new Items(itemsAddForm.getItemName(), itemsAddForm.getPrice(), itemsAddForm.getQuantity());
        itemsRepository.save(newItems);
        return "redirect:/items";
    }

    @GetMapping("/{itemId}")
    public String getItems(@PathVariable("itemId") Long itemId, Model model) {
        Items items = itemsRepository.findById(itemId);
        model.addAttribute("item", items);
        return "items/item";
    }

    @GetMapping("/{itemId}/edit")
    public String itemsUpdateForm(@PathVariable("itemId") Long itemId, Model model) {
        Items items = itemsRepository.findById(itemId);
        model.addAttribute("item", items);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String itemsUpdate(@PathVariable("itemId") Long itemId,
                              @Validated @ModelAttribute("item") ItemsUpdateForm updateForm,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "items/editForm";
        }

        Items newItems = new Items(updateForm.getItemName(), updateForm.getPrice(), updateForm.getQuantity());
        itemsRepository.itemsUpdate(itemId, newItems);

        return "redirect:/items/{itemId}";
    }

}
