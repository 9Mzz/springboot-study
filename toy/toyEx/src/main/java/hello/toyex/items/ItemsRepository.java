package hello.toyex.items;

import java.util.List;

public interface ItemsRepository {

    Items save(Items items);

    Items findById(Long itemId);

    List<Items> findAll();

    void updateItems(Long itemId, Items updateParam);

}
