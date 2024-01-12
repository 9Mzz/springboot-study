package hello.totalproject.items;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController {

  private final ItemsRepository itemsRepository;

  @GetMapping()
  public String itemsHome(Model model) {

    List<Items> items = itemsRepository.findAll();
    model.addAttribute("items", items);

    return "items/items";
  }

  @GetMapping("/add")
  public String itemAddForm(@ModelAttribute("item") Items item) {
    return "items/addForm";
  }

  @PostMapping("/add")
  public String itemAdd(@Validated @ModelAttribute("item") ItemsAddForm itemsParam, BindingResult bindingResult) {

    extractLocalError(itemsParam.getPrice(), itemsParam.getQuantity(), bindingResult);

    if(bindingResult.hasErrors()) {
      log.info("error code = {}", bindingResult);
      return "items/addForm";
    }

    Items newItem = new Items();
    newItem.setItemName(itemsParam.getItemName());
    newItem.setPrice(itemsParam.getPrice());
    newItem.setQuantity(itemsParam.getQuantity());
    Items item = itemsRepository.save(newItem);

    return "redirect:/items";
  }

  @GetMapping("/{itemId}")
  public String item(@PathVariable("itemId") Long itemId, Model model) {
    Items item = itemsRepository.findById(itemId);
    model.addAttribute("item", item);
    return "items/item";
  }

  @GetMapping("/{itemId}/edit")
  public String itemEditForm(@PathVariable("itemId") Long itemId, Model model) {
    Items item = itemsRepository.findById(itemId);
    model.addAttribute("item", item);
    return "items/editForm";
  }

  @PostMapping("/{itemId}/edit")
  public String itemEdit(@PathVariable("itemId") Long itemId, @Validated @ModelAttribute("item") ItemsUpdateForm updateForm,
      BindingResult bindingResult) {

    extractLocalError(updateForm.getPrice(), updateForm.getQuantity(), bindingResult);

    if(bindingResult.hasErrors()) {
      log.info("error code = {}", bindingResult);
      return "items/addForm";
    }

    Items itemsForm = new Items();
    itemsForm.setItemName(updateForm.getItemName());
    itemsForm.setPrice(updateForm.getPrice());
    itemsForm.setQuantity(updateForm.getQuantity());

    itemsRepository.itemsEdit(itemId, itemsForm);
    return "redirect:/items/{itemId}";
  }

  private static void extractLocalError(Integer price, Integer quantity, BindingResult bindingResult) {
    if(price != null && quantity != null) {
      int requiredResult = 10000;
      int totalResult    = price * quantity;
      if(totalResult < requiredResult) {
        bindingResult.reject("totalPriceMin", new Object[]{totalResult, requiredResult}, null);
      }
    }
  }


}
