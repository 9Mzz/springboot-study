package hello.test.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemParam {

    private Long itemId;
    private int  count;

    public ItemParam(Long itemId, int count) {
        this.itemId = itemId;
        this.count  = count;
    }
}
