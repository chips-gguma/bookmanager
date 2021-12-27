package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
//    @Transactional
    void crud() { // create, read, update, delete
//        User user = new User();
//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "name")); // 필드 name의 역순으로 정렬
//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L)); // Long 타입의 1, 3, 5번을 보여줌
//        users.forEach(System.out::println);
//        User user1 = new User("jack", "jack@fastcampus.com");
//        User user2 = new User("steve", "steve@fastcampus.com");
//
//        userRepository.saveAll(Lists.newArrayList(user1, user2));
//
//        List<User> users = userRepository.findAll();
//
//        users.forEach(System.out::println);
//        User user = userRepository.getOne(1L);
//        User user = userRepository.findById(1L).orElse(null);
//        userRepository.saveAndFlush(new User("new martin", "newmartin@fastcampus.com"));

//        long count = userRepository.count();
//        boolean exists = userRepository.existsById(1L);
//        System.out.println(exists);
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        userRepository.deleteById(1L);
        userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // in 절로 1, 3번 조회
        userRepository.deleteAllInBatch();

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void newTest() { // 페이징
        // PageRequest가 구현체
        Page<User> users = userRepository.findAll(PageRequest.of(1, 3)); // zero base이므로 page 1은 두 번째 페이지

        System.out.println("page : " + users);
        System.out.println("totalElements : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages()); // 페이지 사이즈 / elements
        System.out.println("numberOfElements : " + users.getNumberOfElements()); // 레코드 수, 한 페이지 당 3개의 레코드가 들어감
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize()); // 페이징할 때 나눈 크기

        users.getContent().forEach(System.out::println);
    }

    @Test
    void newTest1() {
        // qbe example code
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // name 무시, 이 줄을 빼면 and로 name과 email에 대해 매칭되는 것을 찾음
                .withMatcher("email", endsWith()); // email 매칭

        Example<User> example = Example.of(new User("ma", "naver.com"), matcher); // matcher에 해당하는 것을 다 빼도 and로 name과 email에 해당하는 것을 매칭

        User user = new User();
        user.setEmail("discord");

        ExampleMatcher matcher1 = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example1 = Example.of(user, matcher1);

        userRepository.findAll(example1).forEach(System.out::println);
    }

    @Test
    void newTest2() {
        // update query
        userRepository.save(new User("david", "david@fastcampus.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("gguma-updated@naver.com");

        userRepository.save(user);
    }

    @Test
    void selectTest() {
//        System.out.println(userRepository.findByName("denis"));
//
//        System.out.println("findByEmail : " + userRepository.findByEmail("0804lee@naver.com"));
//        System.out.println("getByEmail : " + userRepository.getByEmail("0804lee@naver.com"));
//        System.out.println("readByEmail : " + userRepository.readByEmail("0804lee@naver.com"));
//        System.out.println("queryByEmail : " + userRepository.queryByEmail("0804lee@naver.com"));
//        System.out.println("searchByEmail : " + userRepository.searchByEmail("0804lee@naver.com"));
//        System.out.println("streamByEmail : " + userRepository.streamByEmail("0804lee@naver.com"));
//        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("0804lee@naver.com"));
//
//
//        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("Yuju"));
//        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("Yuju"));
//        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("Yuju"));

        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("i_am_your_joo@naver.com", "Yuju"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("i_am_your_joo@naver.com", "denis"));

        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));

        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty()); // collection type의 NotEmpty check

        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("Yuju", "gguma"))); // 성능적인 이슈로 인해 항상 in절의 데이터를 사전 검토하는 것이 중요

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("Yu"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("ju"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("uj"));
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%uj%"));

    }

    @Test
    void pagingAndSortingTest() {
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("Yuju"));
        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("Yuju"));

        System.out.println("findTop1ByNameOrderByIdDesc : " + userRepository.findTop1ByNameOrderByIdDesc("Yuju"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("Yuju"));
        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("Yuju", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        // 가독성을 좋게 하기 위해 Sort에 대한 method를 따로 구현할 수도 있음

        System.out.println("findByNameWithPaging : " + userRepository.findByName("Yuju", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();

        user.setName("martin");
        user.setEmail("martin2@fastcampus.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrtin");

        userRepository.save(user2);
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest() {
        User user = new User();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");

        userRepository.save(user); // insert

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrtin");

        userRepository.save(user2); // update

        userRepository.deleteById(4L); // delete
    }

    @Test
    void prePersistTest() {
        User user = new User();

        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");
        // user.setCreatedAt(LocalDateTime.now());
        // user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("martin2@fastcampus.com"));
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is : " + user);

        user.setName("martin22");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("martin-new@fastcampus.com");
        user.setName("martin-new");

        userRepository.save(user);

        user.setName("martin-new-new");

        userRepository.save(user); // update

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}
