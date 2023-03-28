package hello.itemservicetest.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @Test
    void save() {
        Item itemA   = new Item("itemA", 14000, 42);
        Long itemAId = itemRepository.save(itemA);

        Item itemASearch = itemRepository.findById(itemAId);

        Assertions.assertThat(itemASearch)
                  .isEqualTo(itemA);
    }

    @Test
    void itemModify() {
        Item itemA   = new Item("itemA", 14000, 42);
        Long itemAId = itemRepository.save(itemA);
        Item itemB   = new Item("itemB", 42310, 22);
        itemRepository.itemModify(itemAId, itemB);
        //        System.out.println("itemA = " + itemA);

    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 14000, 42);
        Item itemB = new Item("itemB", 149200, 12);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        List<Item> itemList = itemRepository.findAll();
        Assertions.assertThat(itemList.size()).isEqualTo(2);
    }

    @Test
    void clearStore() {
        itemRepository.clearStore();
    }
}