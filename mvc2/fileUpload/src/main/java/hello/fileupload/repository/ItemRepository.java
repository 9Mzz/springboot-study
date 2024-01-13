package hello.fileupload.repository;

import hello.fileupload.dto.Item;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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

  public Item findById(Long id) {
//    log.info("findById data = {}", store.get(id));
    return store.get(id);
  }

}
