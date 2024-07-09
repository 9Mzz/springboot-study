package hello.test.service;

import hello.test.domain.Item;
import hello.test.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 저장
     */
    public Long itemSave(Item item) {
        return itemRepository.save(item)
                .getId();
    }


}
