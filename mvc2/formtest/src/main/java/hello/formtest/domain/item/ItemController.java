package hello.formtest.domain.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/form/items")
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String main(Model model) {

        List<Item> items = itemRepository.findByAll();
        model.addAttribute("items", items);

        return "form/items";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("item", new Item());

        return "form/addForm";
    }

    @PostMapping("/add")
    public String add(Item item, Model model) {

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "form/item";
    }


    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/item";
    }

    @GetMapping("/{itemId}/edit")
    public String updateForm(@PathVariable Long itemId, Model model) {

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long itemId, Item item, Model model) {

        itemRepository.update(itemId, item);

        model.addAttribute("item", item);

        return "form/item";
    }

    @PostConstruct
    public void postItem() {

        Item itemA = new Item("itemA", 42000, 20);
        Item itemB = new Item("itemB", 19500, 23);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

    }


}
