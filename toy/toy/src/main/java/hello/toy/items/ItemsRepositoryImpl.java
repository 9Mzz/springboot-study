package hello.toy.items;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Primary
@Repository
public class ItemsRepositoryImpl implements ItemsRepository {

    private static final Map<Long, Items> store    = new HashMap<>();
    private static       Long             sequence = 0L;

    @Override
    public Items save(Items items) {
        items.setId(++sequence);
        store.put(items.getId(), items);
        log.info("items add = {}", items);
        return items;
    }

    @Override
    public Items findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Items> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void itemsUpdate(Long itemId, Items updateItems) {
        Items newItem = findById(itemId);
        newItem.setItemName(updateItems.getItemName());
        newItem.setPrice(updateItems.getPrice());
        newItem.setQuantity(updateItems.getQuantity());


    }
}
