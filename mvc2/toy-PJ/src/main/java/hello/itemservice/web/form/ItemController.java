package hello.itemservice.web.form;

import hello.itemservice.domain.item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.engine.IElementDefinitionsAware;
import org.thymeleaf.spring5.processor.SpringInputGeneralFieldTagProcessor;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();

        model.addAttribute("items", items);

        return "form/items";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());

        return "form/addForm";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute Item item, BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        //검증 실패시
        if(!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", "itemName ERROR"));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "PRICE ERROR"));
        }
        if(item.getQuantity() == null || item.getQuantity() > 10000) {
            bindingResult.addError(
                    new FieldError("item", "quantity", item.getQuantity(), false, null, null, "QUANTITY ERROR"));
        }
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", "Price * Quantity <= 10000"));
            }
        }

        if(bindingResult.hasErrors()) {
            log.info("ERROR MESSEGE = {}", bindingResult);
            return "form/addForm";
        }

        //검증 성공시
        Item save = itemRepository.save(item);

        return "redirect:/form/items/" + save.getId();
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "form/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item, BindingResult bindingResult, Model model) {

        //검증 실패시
        if(!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", "itemName ERROR"));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "PRICE ERROR"));
        }
        if(item.getQuantity() == null || item.getQuantity() > 10000) {
            bindingResult.addError(
                    new FieldError("item", "quantity", item.getQuantity(), false, null, null, "QUANTITY ERROR"));
        }
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", "Price * Quantity <= 10000"));
            }
        }

        if(bindingResult.hasErrors()) {
            log.info("ERROR MESSEGE = {}", bindingResult);
            return "form/editForm";
        }

        itemRepository.update(itemId, item);

        return "redirect:/form/items/" + itemId;
    }


    @PostConstruct
    public void postItem() {
        itemRepository.save(new Item("itemA", 51030, 30));
        itemRepository.save(new Item("itemB", 12312, 52));
        itemRepository.save(new Item("itemC", 553, 31));
        itemRepository.save(new Item("itemD", 15003, 64));
    }

}
