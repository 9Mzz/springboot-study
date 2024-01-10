package hello.newproject.repository;

import hello.newproject.vo.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ItemRepository {

  private static final Map<Long, Item> store   = new HashMap<>();
  private static       Long            sequcne = 0L;

  public Item save(Item item) {
    item.setId(++sequcne);
    store.put(item.getId(), item);
    log.info("saved data ={}", item);
    return item;
  }

  public Item findById(Long id) {
    return store.get(id);
  }

  public List<Item> findAll() {
    return new ArrayList<>(store.values());
  }

  public Item itemEdit(Long beFormLong, Item beforeItem) {
    Item newItem = findById(beFormLong);
    newItem.setItemName(beforeItem.getItemName());
    newItem.setPrice(beforeItem.getPrice());
    newItem.setQuantity(beforeItem.getQuantity());
    return newItem;
  }

}
