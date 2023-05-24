package hello.formtest.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @Test
    void save() {

        Item itemA = new Item("itemA", 42000, 20);
        Item itemB = new Item("itemB", 19500, 23);

        Long itemASave = itemRepository.save(itemA);
        Long itemBSave = itemRepository.save(itemB);

        Assertions.assertThat(itemA.getId())
                  .isEqualTo(itemASave);
    }

    @Test
    void findByAll() {

        Item itemA = new Item("itemA", 42000, 20);
        Item itemB = new Item("itemB", 19500, 23);

        Long itemASave = itemRepository.save(itemA);
        Long itemBSave = itemRepository.save(itemB);

        List<Item> itemList = itemRepository.findByAll();
        Assertions.assertThat(itemList.size())
                  .isEqualTo(2);
    }

    @Test
    void update() {
        Item itemA = new Item("itemA", 42000, 20);
        Item itemB = new Item("itemB", 19500, 23);

        Long itemASave = itemRepository.save(itemA);

        itemRepository.update(itemASave, itemB);

        System.out.println("itemA = " + itemA);

    }

    @AfterEach
    void clearStore() {
        itemRepository.clearStore();

    }
}