package hello.toy.items;

import java.util.List;

public interface ItemsRepository {

    Items save(Items items);

    Items findById(Long id);

    List<Items> findAll();

    void itemsUpdate(Long itemId, Items updateItems);

}
