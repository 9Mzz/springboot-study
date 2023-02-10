package test.core.order;

public class OrderVo {

    //필드
    private Long   memberId;
    private String itemName;
    private int    itemPrice;
    private int    discountPrice;

    private int calculatePrice() {
        return itemPrice - discountPrice;
    }

    //생성자
    public OrderVo(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId      = memberId;
        this.itemName      = itemName;
        this.itemPrice     = itemPrice;
        this.discountPrice = discountPrice;
    }


    //메소드
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }
    //
    @Override
    public String toString() {
        return "OrderVo{" + "memberId=" + memberId + ", itemName='" + itemName + '\'' + ", itemPrice=" + itemPrice + ", discountPrice=" + discountPrice + '}';
    }
}
