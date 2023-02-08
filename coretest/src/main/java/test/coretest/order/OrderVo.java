package test.coretest.order;

public class OrderVo {

    private Long   memberId;
    private String itemName;
    private int    itemPrice;
    private int    salePrice;

    public int totalPrice() {
        return itemPrice - salePrice;
    }

    //
    public OrderVo() {
    }

    public OrderVo(Long memberId, String itemName, int itemPrice, int salePrice) {
        this.memberId  = memberId;
        this.itemName  = itemName;
        this.itemPrice = itemPrice;
        this.salePrice = salePrice;
    }
    //

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

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
    //

    @Override
    public String toString() {
        return "OrderVo{" + "memberId=" + memberId + ", itemName='" + itemName + '\'' + ", itemPrice=" + itemPrice + ", salePrice=" + salePrice + '}';
    }
}
