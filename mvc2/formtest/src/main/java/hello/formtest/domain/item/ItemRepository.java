package hello.formtest.domain.item;


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

    /**
     * 저장
     *
     * @param item
     * @return item.getId();
     */
    public Long save(Item item) {

        item.setId(++sequence);
        store.put(item.getId(), item);

        return item.getId();
    }

    public Item findById(Long id) {

        return store.get(id);
    }

    public List<Item> findByAll() {

        return new ArrayList<>(store.values());
    }


    public void update(Long id, Item updateParam) {
        log.info("updateParam = {}", updateParam);

        Item item = findById(id);

        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {

        store.clear();
    }


}
