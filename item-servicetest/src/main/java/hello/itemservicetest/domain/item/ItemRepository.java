package hello.itemservicetest.domain.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Repository;
import org.thymeleaf.engine.IElementDefinitionsAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemRepository {

    //기본 설정
    private static final Map<Long, Item> store    = new HashMap<>();
    private static       Long            sequence = 0L;

    //저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    /**
     * 상품 수정
     *
     * @param id
     * @param itemParam
     * @return
     */
    public Item itemModify(Long id, Item itemParam) {
        Item result = findById(id);
        result.setItemName(itemParam.getItemName());
        result.setPrice(itemParam.getPrice());
        result.setQuantity(itemParam.getQuantity());

        return result;
    }

    /**
     * ID로 상품 검색
     *
     * @param id
     * @return
     */
    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     * 모든 상품 리스트
     *
     * @return
     */
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * store clear
     */
    public void clearStore() {
        store.clear();

    }
}
