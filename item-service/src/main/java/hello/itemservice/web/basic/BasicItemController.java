package hello.itemservice.web.basic;


import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
        System.out.println("BasicItemController.items");


        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }


    @GetMapping("/{itemId}")
        System.out.println("BasicItemController.a");
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String save(){
        return "/basic/addForm";
    }



    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 30));
        itemRepository.save(new Item("itemB", 20000, 40));

    }
}
