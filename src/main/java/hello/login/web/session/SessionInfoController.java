package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@Slf4j
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "session not";
        }
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={} , value={}" , name , session.getAttribute(name)));
        log.info("sessionId={}" ,session.getId());
        log.info("sessionId={}" , session.getMaxInactiveInterval());
        log.info("sessionId={}" , new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}" , new Date(session.getLastAccessedTime()));
        log.info("isNew={}" , session.isNew());
        return "session output";
    }
}
