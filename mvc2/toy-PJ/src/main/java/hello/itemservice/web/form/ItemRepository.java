package hello.itemservice.web.form;


import hello.itemservice.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ItemRepository {

    private static final Map<Long, Item> store    = new HashMap<>();
    private static       Long            sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        //log
        log.info("save item = {}", item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Item updateItem) {
        Item newItem = findById(id);
        newItem.setItemName(updateItem.getItemName());
        newItem.setPrice(updateItem.getPrice());
        newItem.setQuantity(updateItem.getQuantity());

        //log
        log.info("update item = {}", newItem);
    }

    public void clearStore() {
        store.clear();
    }


}
