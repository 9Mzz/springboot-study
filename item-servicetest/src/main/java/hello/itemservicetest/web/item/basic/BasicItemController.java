package hello.itemservicetest.web.item.basic;

import hello.itemservicetest.domain.item.Item;
import hello.itemservicetest.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        log.info("items");
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "/basic/items";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Long id = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", id);
        redirectAttributes.addAttribute("status", true);


        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{id}")
    public String item(@PathVariable("id") Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "/basic/item";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, @ModelAttribute Item item) {
        itemRepository.itemModify(id, item);


        return "redirect:/basic/items/{id}";
    }

    @PostConstruct
    public void postCons() {
        Item itemA = new Item("itemA", 14000, 42);
        Item itemB = new Item("itemB", 149200, 12);
        Item itemC = new Item("itemC", 52000, 2);
        Item itemD = new Item("itemD", 13200, 52);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);
        itemRepository.save(itemD);

    }

}
