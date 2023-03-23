package hello.itemservice.domain;

import lombok.experimental.PackagePrivate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {

    //static
    //실무에서는 hashmap 대신 LinkedHashMap<> 사용
    private static final Map<Long, Item> store = new HashMap<>();

    // 실무에서는 Long 대신 AtomicLong 사용
    private static Long sequence = 0L; //static


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

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuanity(updateParam.getQuanity());

    }

    public void clearStore() {
        store.clear();
    }

}
