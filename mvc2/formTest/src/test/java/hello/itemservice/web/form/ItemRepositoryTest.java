package hello.itemservice.web.form;

import hello.itemservice.ItemServiceApplication;
import hello.itemservice.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @Test
    void save() {

        Item testA = new Item("testA", 15000, 53);
        Item testB = new Item("testB", 432990, 530);

        Item saveA = itemRepository.save(testA);

        Item findA = itemRepository.findById(saveA.getId());

        Assertions.assertThat(findA)
                  .isEqualTo(saveA);

    }


    @Test
    void findAll() {
        Item testA = new Item("testA", 15000, 53);
        Item testB = new Item("testB", 432990, 530);

        Item saveA = itemRepository.save(testA);
        Item saveB = itemRepository.save(testB);

        List<Item> itemList = itemRepository.findAll();
        Assertions.assertThat(itemList.size())
                  .isEqualTo(2);
    }


    @Test
    void update() {
        Item testA = new Item("testA", 15000, 53);
        Item testB = new Item("testB", 432990, 530);

        Item saveA = itemRepository.save(testA);

        itemRepository.update(saveA.getId(), testB);
        Assertions.assertThat(testA.getItemName())
                  .isEqualTo(testB.getItemName());

    }

    @BeforeEach
    void clearStore() {
        itemRepository.clearStore();
    }
}