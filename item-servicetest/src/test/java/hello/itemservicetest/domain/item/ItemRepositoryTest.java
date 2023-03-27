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
        Item itemA      = new Item("itemA", 10000, 20);
        Item saveResult = itemRepository.save(itemA);

        Item findResult = itemRepository.findById(saveResult.getId());
        Assertions.assertThat(findResult)
                  .isEqualTo(saveResult);
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 10000, 20);
        Item itemB = new Item("itemB", 30000, 10);
        Item itemC = new Item("itemC", 12000, 0);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        List<Item> result = itemRepository.findAll();
        Assertions.assertThat(result.size())
                  .isEqualTo(3);

    }

    @Test
    void itemModify() {
        Item itemA     = new Item("itemA", 10000, 20);
        Item itemASave = itemRepository.save(itemA);
        Item itemAFind = itemRepository.findById(itemASave.getId());

        Item dummyA = new Item("itemB", 14020, 30);
        itemRepository.itemModify(itemASave.getId(), dummyA);
        Item itemAFind2 = itemRepository.findById(itemASave.getId());

        Assertions.assertThat(itemAFind2.getItemName())
                  .isEqualTo(dummyA.getItemName());

    }

    @BeforeEach
    void clearStore() {
        itemRepository.clearStore();
    }
}