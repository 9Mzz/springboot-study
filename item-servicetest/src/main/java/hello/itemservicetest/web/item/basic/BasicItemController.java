package hello.itemservicetest.web.item.basic;

import hello.itemservicetest.domain.item.Item;
import hello.itemservicetest.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String item(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "/basic/items";
    }

    @PostConstruct
    public void beforeAct() {
        Item itemA = new Item("testA", 12000, 20);
        Item itemB = new Item("itemB", 24000, 10);

        itemRepository.itemSave(itemB);
        itemRepository.itemSave(itemA);

    }

}
