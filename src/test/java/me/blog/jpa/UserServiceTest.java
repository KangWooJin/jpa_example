package me.blog.jpa;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import me.blog.jpa.model.elementCollection.QUser;
import me.blog.jpa.model.elementCollection.QUserInventory;
import me.blog.jpa.model.elementCollection.User;
import me.blog.jpa.model.elementCollection.UserInventory;
import me.blog.jpa.service.UserService;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureTestEntityManager
class UserServiceTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private UserService userService;

    @Test
    void queryDslTest() {
        generateUser(101);

        JPAQuery<User> query = new JPAQuery(em.getEntityManager());

        QUser user = QUser.user;
        query.select(user.id)
             .from(user)
             .groupBy(user.name, user.id)
             .having(user.price.sum().gt(10));

        List<User> result = query.fetch();

        assertThat(result).isNotNull();
    }

    @Test
    void queryDslJoinTest() {
        User user = new User();
        UserInventory userInventory = new UserInventory();
        user.setUserInventory(userInventory);
        em.persist(user);
        em.persist(userInventory);

        em.flush();

        JPAQuery<User> query = new JPAQuery(em.getEntityManager());

        QUser qUser = QUser.user;
        QUserInventory qUserInventory = QUserInventory.userInventory;

        query.from(qUser)
             .join(qUser.userInventory, qUserInventory)
             .where(qUser.userInventory.id.eq(userInventory.getId()));

        User result = query.fetchOne();

        assertThat(result).isNotNull();
    }

    @Test
    void queryDslSubQueryTest() {
        User user = new User();
        em.persist(user);
        em.flush();

        JPAQuery<User> query = new JPAQuery(em.getEntityManager());
        QUser qUser = QUser.user;
        SubQueryExpression subQueryExpression = JPAExpressions.select(qUser)
                                                              .from(qUser)
                                                              .where(qUser.name.eq("woojin"));
        query.from(qUser)
             .where(qUser.id.in(subQueryExpression));

        List<User> result = query.fetch();

        assertThat(result).isNotNull();
    }

    @Test
    void dynamicQueryTest() {
        User user = new User();
        em.persist(user);
        em.flush();

        JPAQuery<User> query = new JPAQuery(em.getEntityManager());
        QUser qUser = QUser.user;
        query.from(qUser);

        BooleanBuilder builder = new BooleanBuilder();

        String name = "woojin";
        if (name != null) {
            builder.and(qUser.name.eq(name));
        }

        query.where(builder);

        List<User> result = query.fetch();

        assertThat(result).isNotNull();
    }

    private void generateUser(int size) {
        for (int i = 0; i < size; ++i) {
            User user = new User();

            em.persist(user);
        }
        em.flush();
    }

    @Test
    void findByIdTest() {
        // Given
        User user = createUser();

        // When
        User actual = userService.findById(user.getId());

        // Then
        assertThat(actual).isNotNull();
    }

    public User createUser() {
        User user = new User();
        User persist = em.persist(user);

        em.flush();

        return persist;
    }
}
