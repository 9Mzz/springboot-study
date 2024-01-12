package hello.totalproject.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ItemsRepository {

  private static final Map<Long, Items> store    = new HashMap<>();
  private static       Long             sequence = 0L;

  public Items save(Items items) {
    items.setId(++sequence);
    store.put(items.getId(), items);
    log.info(" saved items = {}", items);
    return items;
  }

  public Items findById(Long id) {
    return store.get(id);
  }

  public List<Items> findAll() {
    return new ArrayList<>(store.values());
  }

  public Items itemsEdit(Long itemId, Items itemParam) {
    Items newItems = findById(itemId);
    newItems.setItemName(itemParam.getItemName());
    newItems.setPrice(itemParam.getPrice());
    newItems.setQuantity(itemParam.getQuantity());
    log.info("updated data = {}", newItems);
    return newItems;
  }

}
