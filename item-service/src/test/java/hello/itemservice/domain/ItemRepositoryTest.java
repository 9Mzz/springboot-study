package hello.itemservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void clearStore() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(saveItem.getId());

        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();


        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void update() {
        //given
        Item item1    = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(item1);
        Long itemId   = saveItem.getId();

        //when
        Item updateParam = new Item("itemB", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item item = itemRepository.findById(itemId);

        assertThat(item.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(item.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(item.getQuanity()).isEqualTo(updateParam.getQuanity());


    }


}