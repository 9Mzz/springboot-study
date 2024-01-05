package hello.upload.domain;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ItemRepository {

  private final Map<Long, Item> store    = new HashMap<>();
  private       Long            sequence = 0L;

  public Item save(Item item) {
    item.setId(++sequence);
    store.put(item.getId(), item);
    log.info("item = {}", item);
    return item;
  }

  public Item findById(Long id) {
    return store.get(id);
  }


}
