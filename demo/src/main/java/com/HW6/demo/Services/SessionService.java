package com.HW6.demo.Services;


import com.HW6.demo.Entities.User;
import com.HW6.demo.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final int allowedInFree;

    private final int allowedInBasic;

    private final int allowedInPremium;

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(@Value("${free.allowed}") int allowedInFree, @Value("${basic.allowed}") int allowedInBasic, @Value("${premium.allowed}") int allowedInPremium, SessionRepository sessionRepository) {
        this.allowedInFree = allowedInFree;
        this.allowedInBasic = allowedInBasic;
        this.allowedInPremium = allowedInPremium;
        this.sessionRepository = sessionRepository;
    }





    public void generateNewSession(User user , String refreshToken) {
        if(sessionRepository.findNoOfSessionsByUserName(user.getUsername()) >= getAllowedSessions(user)) {
            throw new RuntimeException("Already have a session");
        }

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
