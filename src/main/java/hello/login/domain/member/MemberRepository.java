package hello.login.domain.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class MemberRepository {

    private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0L);

    public Member save(Member member){
        //++sequence 쓰면 원자적으로 연산이 아니여서 동시성 터질 위험이 있음
        member.setId(sequence.incrementAndGet());
        log.info("save: member={}" , member);
        store.put(member.getId() , member);
        return member;
    }
     public Optional<Member> findByLoginId(String loginId){
         //List<Member> all = findAll();
        /* for (Member member : all) {
             if (member.getLoginId().equals(loginId));{
                 return Optional.of(member);
             }
         }
         return Optional.empty();*/
         return findAll().stream()
                 .filter(m -> m.getLoginId().equals(loginId))
                 .findFirst();

     }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
