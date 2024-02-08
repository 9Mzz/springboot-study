package hello.toy.money;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class MoneyService {

    private final MoneyRepository moneyRepository;

    @Autowired
    public MoneyService(MoneyRepository moneyRepository) {
        this.moneyRepository = moneyRepository;
    }

    public void bizLogic(String toId, String fromId, int money) {
        Member toMember   = moneyRepository.findById(toId);
        Member fromMember = moneyRepository.findById(fromId);

        moneyRepository.update(toId, toMember.getMoney() - money);
        validation(fromMember);

        moneyRepository.update(fromId, fromMember.getMoney() + money);

    }

    private void validation(Member fromMember) {
        if(fromMember.getMember_id()
                .equals("ex")) {
            throw new IllegalStateException("송금 중 오류 발생");
        }
    }


}
