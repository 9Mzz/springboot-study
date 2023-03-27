package hello.itemservicetest.domain.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemRepository {


    private static final Map<Long, Item> store    = new HashMap<>();
    private static       Long            sequence = 0L;


    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {

        return new ArrayList<>(store.values());
    }

    public void itemModify(Long itemId, Item itemParam) {
        Item newItem = findById(itemId);
        newItem.setItemName(itemParam.getItemName());
        newItem.setPrice(itemParam.getPrice());
        newItem.setQuantity(itemParam.getQuantity());

        log.info("new item = {}", newItem.toString());
    }

    public void clearStore() {
        store.clear();
    }
}
