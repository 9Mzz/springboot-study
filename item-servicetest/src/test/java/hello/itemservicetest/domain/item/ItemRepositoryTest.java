package hello.itemservicetest.domain.item;

import org.apache.juli.logging.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @Test
    void save() {
        Item itemA    = new Item("itemA", 10000, 20);
        Item saveData = itemRepository.save(itemA);

        Item findData = itemRepository.findById(saveData.getId());

        assertThat(findData).isEqualTo(saveData);
    }

    @Test
    void itemModify() {
        Item itemA    = new Item("itemA", 10000, 20);
        Item saveData = itemRepository.save(itemA);

        Item itemB        = new Item("itemB", 20000, 20);
        Item modifyResult = itemRepository.itemModify(saveData.getId(), itemB);

        assertThat(modifyResult.getItemName()).isEqualTo(itemB.getItemName());
        assertThat(modifyResult.getPrice()).isEqualTo(itemB.getPrice());
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 10000, 20);
        Item itemB = new Item("itemB", 20000, 20);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> result = itemRepository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size())
                                       .isEqualTo(2);

    }

    @AfterEach
    void clearStore() {

        itemRepository.clearStore();

    }
}