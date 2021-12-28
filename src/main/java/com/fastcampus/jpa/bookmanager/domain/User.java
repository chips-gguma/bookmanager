package com.fastcampus.jpa.bookmanager.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // final이나 @NonNull이 없다면 NoArgsConstructor와 동일
@Data
@Builder
@Entity
@EntityListeners(value = {AuditingEntityListener.class, UserEntityListener.class}) // 여러 개 지정 가능
// @Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = { @UniqueConstraint(columnNames = {"email"})})
public class User implements Auditable {
    @Id // Primary Key(PK)임을 지정
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING) // index값으로 출력되던 것이 지정한 String 값으로 출력됨
    private Gender gender;

    // @Column(name = "crtdat") // db에서 쓰일 컬럼명 지정
    @Column(updatable = false) // 필요시 update 값 저장 X
    @CreatedDate
    private LocalDateTime createdAt; // 생성 시간

    // @Column(nullable = false) // not null
    // @Column(insertable = false) // 필요시 insert 값 저장 X
    @LastModifiedDate
    private LocalDateTime updatedAt; // 수정 시간


//    @Transient // 영속성 처리에서 제외되므로 db에 반영 X, 객체에서 따로 쓸 필드가 필요할 때 사용
//    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

    /*

    @PrePersist // insert method 호출 전 실행
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate // merge method 호출 전 실행
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    */

    /*

    @PostPersist
    public void postPersist() {
        log.info(">>>> postPersist");
    }

    @PostUpdate
    public void postUpdate() {
        log.info(">>>> postUpdate");
    }

    @PreRemove // delete method 호출 전 실행
    public void preRemove() {
        log.info(">>>> preRemove");
    }

    @PostRemove
    public void postRemove() {
        log.info(">>>> postRemove");
    }

    @PostLoad // select 조회가 일어난 직후 실행
    public void postLoad() {
        log.info(">>>> postLoad");
    }

     */

}

