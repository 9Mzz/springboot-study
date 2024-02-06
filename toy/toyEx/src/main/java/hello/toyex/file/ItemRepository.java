package hello.toyex.file;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll();
}
