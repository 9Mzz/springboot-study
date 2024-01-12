package hello.itemuplaodform.form;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ItemsRepository {

  public static final Map<Long, Items> store    = new HashMap<>();
  private static      Long             sequence = 0L;


  public Items save(Items items) {
    items.setId(++sequence);
    store.put(items.getId(), items);
    log.info("saved data = {}", items);
    return items;
  }

  public Items findById(Long id) {
    return store.get(id);
  }


}
