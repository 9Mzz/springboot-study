package hello.toyex.items;

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

    @GetMapping
    public String itemHome(Model model) {
        List<Items> items = itemsRepository.findAll();
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/add")
    public String itemsAddForm(@ModelAttribute("item") Items items) {
        return "items/addForm";
    }

    @PostMapping("/add")
    public String itemsAdd(@Validated @ModelAttribute("item") ItemsAddForm itemsParam, BindingResult bindingResult) {

        if(itemsParam.getPrice() != null && itemsParam.getQuantity() != null) {
            int requiredPrice = 100000;
            int totalPrice    = itemsParam.getPrice() * itemsParam.getQuantity();
            if(totalPrice < requiredPrice) {
                bindingResult.reject("totalPriceMin", null);
            }
        }
        if(bindingResult.hasErrors()) {
            return "items/addForm";
        }
        Items newItems = new Items(itemsParam.getItemName(), itemsParam.getPrice(), itemsParam.getQuantity());
        itemsRepository.save(newItems);
        return "redirect:/items";
    }

    @GetMapping("/{itemId}")
    public String getItem(@PathVariable("itemId") Long itemId, Model model) {
        Items items = itemsRepository.findById(itemId);
        model.addAttribute("item", items);
        return "items/item";
    }

    @GetMapping("/{itemId}/edit")
    public String itemEditForm(@PathVariable("itemId") Long itemId, Model model) {
        Items items = itemsRepository.findById(itemId);
        model.addAttribute("item", items);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String itemEdit(@PathVariable("itemId") Long itemId,
                           @Validated @ModelAttribute("item") ItemsUpdateForm updateParam,
                           BindingResult bindingResult) {

        if(updateParam.getPrice() != null && updateParam.getQuantity() != null) {
            int requiredPrice = 100000;
            int totalPrice    = updateParam.getPrice() * updateParam.getQuantity();
            if(totalPrice < requiredPrice) {
                bindingResult.reject("totalPriceMin", null);
            }
        }
        if(bindingResult.hasErrors()) {
            return "items/addForm";
        }

        Items items = new Items();
        items.setItemName(updateParam.getItemName());
        items.setPrice(updateParam.getPrice());
        items.setQuantity(updateParam.getQuantity());
        itemsRepository.updateItems(itemId, items);
        return "redirect:/items/{itemId}";
    }


}

