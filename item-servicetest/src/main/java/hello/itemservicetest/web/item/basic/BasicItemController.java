package hello.itemservicetest.web.item.basic;

import hello.itemservicetest.domain.item.Item;
import hello.itemservicetest.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/basic/items";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item item) {
        itemRepository.save(item);

        return "redirect:/basic/items";
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
    public void basicData() {
        Item itemA = new Item("itemA", 20400, 14);
        Item itemB = new Item("itemB", 413400, 54);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }

}
