package com.HW6.demo.Services;


import com.HW6.demo.Entities.Session;
import com.HW6.demo.Entities.User;
import com.HW6.demo.Repositories.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SessionService {

    private final int allowedInFree;

    private final int allowedInBasic;

    private final int allowedInPremium;

    private final SessionRepository sessionRepository;

    private static final Logger log = Logger.getLogger(SessionService.class.getName());

    @Autowired
    public SessionService(@Value("${free.allowed}") int allowedInFree, @Value("${basic.allowed}") int allowedInBasic, @Value("${premium.allowed}") int allowedInPremium, SessionRepository sessionRepository) {
        this.allowedInFree = allowedInFree;
        this.allowedInBasic = allowedInBasic;
        this.allowedInPremium = allowedInPremium;
        this.sessionRepository = sessionRepository;
    }





    public void generateNewSession(User user,String refreshToken) {
        log.info("Inside the generate new session for user " + user.getUsername());
        if(sessionRepository.findNoOfSessionsByUserName(user.getUsername()) >= getAllowedSessions(user)) {
            log.info("User has already sessions equal to what he can have, hence we are deleting the recently used session");
            //Dealing with more than or equal no of sections.
            // hence we need to delete the least recently used ones
            log.info(" Some Old Session deleted for user "+user.getUsername());
            Session sessionLeastRecentlyUsed = sessionRepository.findTopByOrderByLastUsedAtAsc();
            log.info("Some least recently used session found for deletion for user "+user.getUsername());
            sessionRepository.delete(sessionLeastRecentlyUsed);
            log.info("Session deleted for user "+user.getUsername());

        }
        log.info("Created a new Session for user "+ user.getUsername());
        // now we have enough slots to add a session.
        sessionRepository.save(new Session(user,LocalDateTime.now(),refreshToken));
        log.info("Session has been generated for user " + user.getUsername());


    }


    private int getAllowedSessions(User user) {
        if(user.getSubscription().name().equals("FREE")){
            return allowedInFree;
        }
        if(user.getSubscription().name().equals("BASIC")){
            return allowedInBasic;
        }
        if(user.getSubscription().name().equals("PREMIUM")){
            return allowedInPremium;
        }
        return allowedInFree;
    }


    public boolean exists(String refreshToken) {
        log.info("In the EXISTS method of SessionService ");
        if(!sessionRepository.existsSessionByRefreshToken(refreshToken)){
            log.info("The session does not exist");
            return false;
        }
        log.info("The session exists");
        return true;
    }

    @Transactional
    public void deleteByRefreshToken(String refreshToken) {
        log.info("In the DELETE method of SessionService ");
        sessionRepository.removeSessionByRefreshToken(refreshToken);
        log.info("The session deleted");
    }

    @Transactional
    public User getUserByRefreshToken(String refreshToken) {
        Optional<Session> session = sessionRepository.findByRefreshToken(refreshToken);
        return session.get().getUser();
    }
}
