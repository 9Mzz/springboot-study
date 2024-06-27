package hello.itemservice.repository.v2;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static hello.itemservice.domain.QItem.item;

@Repository
@Transactional
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory factory;

    public ItemQueryRepositoryV2(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond) {
        String  itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        return factory.select(item)
                .from(item)
                .where(likeItemName(itemName), maxPrice(maxPrice))
                .fetch();
    }

    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }

    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }
}
