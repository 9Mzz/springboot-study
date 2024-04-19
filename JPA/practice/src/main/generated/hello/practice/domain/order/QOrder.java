package hello.practice.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 337318619L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final hello.practice.domain.QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final hello.practice.domain.QMember member;

    public final DateTimePath<java.util.Date> orderDate = createDateTime("orderDate", java.util.Date.class);

    public final ListPath<hello.practice.domain.OrderItem, hello.practice.domain.QOrderItem> orderItems = this.<hello.practice.domain.OrderItem, hello.practice.domain.QOrderItem>createList("orderItems", hello.practice.domain.OrderItem.class, hello.practice.domain.QOrderItem.class, PathInits.DIRECT2);

    public final EnumPath<hello.practice.domain.OrderStatus> status = createEnum("status", hello.practice.domain.OrderStatus.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new hello.practice.domain.QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
        this.member = inits.isInitialized("member") ? new hello.practice.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

