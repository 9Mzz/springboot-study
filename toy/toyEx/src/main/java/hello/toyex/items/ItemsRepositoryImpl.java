package hello.toyex.items;

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
        log.info("item save = {}", items);
        return items;
    }

    @Override
    public Items findById(Long itemId) {
        return store.get(itemId);
    }

    @Override
    public List<Items> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void updateItems(Long itemId, Items updateParam) {
        Items afterItems = findById(itemId);
        afterItems.setItemName(updateParam.getItemName());
        afterItems.setPrice(updateParam.getPrice());
        afterItems.setQuantity(updateParam.getQuantity());
    }


}
