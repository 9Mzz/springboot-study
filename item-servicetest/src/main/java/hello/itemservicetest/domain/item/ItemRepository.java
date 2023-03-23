package hello.itemservicetest.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store    = new HashMap<>();
    private static       Long            sequence = 0L;

    //상품 저장
    public Item itemSave(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    //상품 수정
    public Item itemModify(Long id, Item updateParam) {
        Item findItem = findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());


        return findItem;
    }

    //id로 상품 검색
    public Item findById(Long id) {

        return store.get(id);
    }

    //모든 상품 검색
    public List<Item> findAll() {


        return new ArrayList<>(store.values());
    }

    //저장소 초기화
    public void clearStore() {
        store.clear();
    }

}
