package hello.itemservice.web.form;

import hello.itemservice.ItemServiceApplication;
import hello.itemservice.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.PostConstruct;
import java.sql.SQLTransactionRollbackException;
import java.util.*;


@Slf4j
@Repository
public class ItemRepository {


    private static final Map<Long, Item> store    = new HashMap<>();
    private static       Long            sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);

        store.put(item.getId(), item);
        log.info("saved data = {}", item);
        return item;
    }


    public Item findById(Long itemId) {

        return store.get(itemId);
    }

    public List<Item> findAll() {

        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {

        Item item = findById(itemId);
        item.setItemName(updateParam.getItemName());
        item.setQuantity(updateParam.getQuantity());
        item.setPrice(updateParam.getPrice());

        item.setOpen(updateParam.isOpen());
        item.setItemType(updateParam.getItemType());
        item.setRegions(updateParam.getRegions());
        item.setDeliveryCode(updateParam.getDeliveryCode());

        log.info("update data = {}", item);
    }

    public void clearStore() {

        store.clear();
    }


}
