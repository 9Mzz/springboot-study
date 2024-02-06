package hello.toyex.file;

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
public class ItemRepositoryImpl implements ItemRepository {

    private static final Map<Long, Item> store   = new HashMap<>();
    private static       Long                sequcne = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequcne);
        store.put(item.getId(), item);
        log.info("save data = {}", item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
}
