package hello.itemservicetest.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void clearStore() {
        itemRepository.clearStore();
    }

    @Test
    void itemSave() {
        Item item1  = new Item("itemA", 10000, 20);
        Item result = itemRepository.itemSave(item1);

        org.assertj.core.api.Assertions.assertThat(item1)
                                       .isEqualTo(result);

    }

    @Test
    void itemModify() {
        Item item1      = new Item("itemA", 100000, 40);
        Item saveResult = itemRepository.itemSave(item1);

        Item updateForm   = new Item("itemB", 200000, 40);
        Item modifyResult = itemRepository.itemModify(saveResult.getId(), updateForm);

        org.assertj.core.api.Assertions.assertThat(item1)
                                       .isEqualTo(modifyResult);

    }


    @Test
    void findAll() {
        Item item1 = new Item("itemA", 100000, 40);
        Item item2 = new Item("itemb", 200000, 30);

        itemRepository.itemSave(item1);
        itemRepository.itemSave(item2);

        List<Item> result = itemRepository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size())
                                       .isEqualTo(2);

    }


}