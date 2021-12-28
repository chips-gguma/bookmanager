package com.fastcampus.jpa.bookmanager.domain.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

// 엔티티 리스너를 활용함으로써 반복되는 코드를 줄일 수 있다. (중복되는 필드명에 대한)
public class MyEntityListener { // 해당 엔티티를 받아야 한다.
    @PrePersist
    public void prePersist(Object o) { // 어떤 타입인지 알기 위해 object를 강제화한다.
        if(o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o) {
        if(o instanceof Auditable) {
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
