package hello.itemservicetest.web.item.basic;

import hello.itemservicetest.domain.item.Item;
import hello.itemservicetest.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

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
    public String add(@ModelAttribute Item item, Model model) {
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
    public void dummyData() {
        Item itemA = new Item("itemA", 10000, 20);
        Item itemB = new Item("itemB", 30000, 10);
        Item itemC = new Item("itemC", 12000, 0);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

    }

}
