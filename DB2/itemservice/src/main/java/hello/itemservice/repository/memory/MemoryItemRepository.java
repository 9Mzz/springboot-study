package hello.itemservice.repository.memory;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final Map<Long, Item> store    = new HashMap<>();
    private static       Long            sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public void update(Long id, ItemUpdateDto updateParam) {
        Item item = findById(id).orElseThrow();
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String  itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        return store.values().stream().filter(item -> {
            if(ObjectUtils.isEmpty(itemName)) {
                return true;
            }
            return item.getItemName().contains(itemName);
        }).filter(item -> {
            if(maxPrice == null) {
                return true;
            }
            return item.getPrice() <= maxPrice;
        }).toList();
        //                .collect(Collectors.toList());
    }

    public void clearStore() {
        store.clear();
    }


}