package hello.itemservicetest.domain.item;

import jdk.jshell.spi.ExecutionControl;
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


    public Long save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item.getId();
    }

    public void itemModify(Long id, Item itemParam) {
        Item modifyItem = findById(id);
        modifyItem.setItemName(itemParam.getItemName());
        modifyItem.setPrice(itemParam.getPrice());
        modifyItem.setQuantity(itemParam.getQuantity());
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }


}
