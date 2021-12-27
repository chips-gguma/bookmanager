package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PreUpdate;

@Component
public class UserEntityListener {
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @PreUpdate
    public void preUpdate(Object o) {
        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
