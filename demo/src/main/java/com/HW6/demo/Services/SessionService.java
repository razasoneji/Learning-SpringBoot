package com.HW6.demo.Services;


import com.HW6.demo.Entities.Session;
import com.HW6.demo.Entities.User;
import com.HW6.demo.Repositories.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        if(sessionRepository.findNoOfSessionsByUserName(user.getUsername()) >= getAllowedSessions(user)) {
            //Dealing with more than or equal no of sections.
            // hence we need to delete the least recently used ones
            log.info(" Some Old Session deleted for user "+user.getUsername());
            Session sessionLeastRecentlyUsed = sessionRepository.findTopByOrderByLastUsedAtAsc();
            sessionRepository.delete(sessionLeastRecentlyUsed);

        }
        log.info("Created a new Session for user "+ user.getUsername());
        // now we have enough slots to add a session.
        sessionRepository.save(new Session(user,LocalDateTime.now(),refreshToken));


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
    }
}
