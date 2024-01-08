package hello.upload01.file;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FileRepository {

  private static Map<Long, Item> store    = new HashMap<>();
  private static Long            sequence = 0L;

  public Item save(Item item) {
    item.setId(++sequence);
    store.put(item.getId(), item);
    log.info("saved item data = {}", item);
    return item;
  }

  public Item findById(Long id) {
    return store.get(id);
  }

}
