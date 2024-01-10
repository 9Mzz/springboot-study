package hello.newproject.controller;

import hello.newproject.repository.ItemRepository;
import hello.newproject.vo.Item;
import hello.newproject.vo.ItemAddVo;
import hello.newproject.vo.ItemUpdateVo;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

  private final ItemRepository itemRepository;

  @GetMapping()
  public String items(Model model) {
    List<Item> items = itemRepository.findAll();
    model.addAttribute("items", items);

    return "items/items";
  }

  @GetMapping("/add")
  public String itemAddForm(@ModelAttribute("item") ItemAddVo itemAddVo) {

    return "items/addForm";
  }

  @PostMapping("/add")
  public String itemAdd(@Validated @ModelAttribute("item") ItemAddVo itemAddVo, BindingResult bindingResult) {

    //검증 logic
    if(itemAddVo.getPrice() != null && itemAddVo.getQuantity() != null) {
      int requiredPrice = 10000;
      int resultPrice   = itemAddVo.getPrice() * itemAddVo.getQuantity();
      if(resultPrice < requiredPrice) {
        bindingResult.reject("totalPriceMin", new Object[]{requiredPrice, resultPrice}, null);
      }
    }
    if(bindingResult.hasErrors()) {
      log.info("bindingResult = {} ", bindingResult);
      return "items/addForm";
    }

    Item item = new Item();
    item.setItemName(itemAddVo.getItemName());
    item.setPrice(itemAddVo.getPrice());
    item.setQuantity(itemAddVo.getQuantity());
    itemRepository.save(item);

    return "redirect:/items";
  }

  @GetMapping("/{itemId}")
  public String getItem(@PathVariable("itemId") Long itemId, Model model) {
    Item item = itemRepository.findById(itemId);
    model.addAttribute("item", item);
    return "items/item";
  }

  @GetMapping("/{itemId}/edit")
  public String editItemForm(@PathVariable("itemId") Long itemId, Model model) {
    Item item = itemRepository.findById(itemId);
    model.addAttribute("item", item);
    return "items/editForm";
  }

  @PostMapping("/{itemId}/edit")
  public String editItem(@PathVariable("itemId") Long itemId, @Validated @ModelAttribute("item") ItemUpdateVo itemParam,
      BindingResult bindingResult, Model model,
      RedirectAttributes redirectAttributes) {

    if(itemParam.getPrice() != null && itemParam.getQuantity() != null) {
      int totalResult = itemParam.getPrice() * itemParam.getQuantity();
      if(totalResult < 10000) {
        bindingResult.reject("totalPriceMin", new Object[]{10000, totalResult}, null);
        return "items/editForm";
      }
    }
    if(bindingResult.hasErrors()) {
      log.info("error code = {}", bindingResult);
      return "items/editForm";
    }

    Item form = new Item();
    form.setItemName(itemParam.getItemName());
    form.setPrice(itemParam.getPrice());
    form.setQuantity(itemParam.getQuantity());

    Item item = itemRepository.itemEdit(itemId, form);
    model.addAttribute("item", item);
    redirectAttributes.addAttribute("itemId", itemId);
    return "redirect:/items/{itemId}";
  }


}
