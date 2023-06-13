package hello.itemservice.web.form;

import hello.itemservice.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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

        log.info("save item = {}", item);

        return item;
    }

    public Item findById(Long id) {

        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long findId, Item beforeItem) {

        Item item = findById(findId);

        log.info("update Data = {}", beforeItem);

        item.setItemName(beforeItem.getItemName());
        item.setPrice(beforeItem.getPrice());
        item.setQuantity(beforeItem.getQuantity());
        item.setOpen(beforeItem.isOpen());
        item.setItemType(beforeItem.getItemType());
        item.setRegions(beforeItem.getRegions());
        item.setDeliveryCode(beforeItem.getDeliveryCode());

    }

    public void clearStore() {
        store.clear();
    }


}
