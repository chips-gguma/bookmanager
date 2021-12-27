package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findByName(String name); // 동일한 name에 대한 출력을 위해 List로 선언
//    Optional<User> findByName(String name); // 리턴 타입이 고정된 것이 아니라 db 설계 상 레코드가 몇 개 존재하는지에 따라 리턴 타입을 개발자가 지정할 수 있음
    Set<User> findByName(String name);

    Set<User> findUserByNameIs(String name);
    Set<User> findUserByName(String name);
    Set<User> findUserByNameEquals(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    List<User> findFirst1ByName(String name);

    // List<User> findTop1ByName(String name);

    List<User> findLast1ByName(String name); // 인식하지 않은 query는 무시

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime yesterday); // After는 특정 날짜보다 큰 것

    List<User> findByIdAfter(Long id);

    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday); // before, after는 equals를 포함하지 않는다.

    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<User> findByIdBetween(Long id1, Long id2); // between은 양끝의 조건을 포함, id1, id2를 포함한 사잇값들 찾음

    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    List<User> findByIdIsNotNull();

//    List<User> findByAddressIsNotEmpty();

    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);

    List<User> findTop1ByName(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name); // select top1 where name order by id desc

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name); // id에 역순, email에 정순

    List<User> findFirstByName(String name, Sort sort);

    Page<User> findByName(String name, Pageable pageable); // Page는 페이징에 대한 응답 값, Pageable은 페이징 요청을 하는 요청 값

    @Query(value = "select * from user limit 1;", nativeQuery = true) // 그대로 쿼리 실행
    Map<String, Object> findRawRecord();    // 해당 쿼리가 Map에 저장

}
