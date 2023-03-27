package hello.itemservicetest.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    private ItemRepository itemRepository = new ItemRepository();

    @Test
    void save() {
        Item itemA     = new Item("itemA", 20400, 14);
        Item saveItemA = itemRepository.save(itemA);
        Item findItemA = itemRepository.findById(saveItemA.getId());

        Assertions.assertThat(findItemA)
                  .isEqualTo(saveItemA);

    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 20400, 14);
        Item itemB = new Item("itemB", 413400, 54);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> itemList = itemRepository.findAll();
        Assertions.assertThat(itemList.size())
                  .isEqualTo(2);
    }

    @Test
    void itemModify() {
        Item itemA     = new Item("itemA", 20400, 14);
        Item saveItemA = itemRepository.save(itemA);

        Item itemB = new Item("itemB", 413400, 54);
        itemRepository.itemModify(saveItemA.getId(), itemB);
    }

    @BeforeEach
    void clearStore() {
        itemRepository.clearStore();
    }
}