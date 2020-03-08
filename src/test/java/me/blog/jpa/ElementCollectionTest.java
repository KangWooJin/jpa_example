package me.blog.jpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import me.blog.jpa.model.elementCollection.User;

@DataJpaTest
class ElementCollectionTest {
    @Autowired
    private TestEntityManager em;

    @Test
    void changeElementCollectionTest() {
        User user = new User();
        List<String> interestList = user.getInterestList();
        interestList.add("축구");
        interestList.add("노래");
        interestList.add("여행");

        em.persist(user);
        em.flush();

        interestList.remove("노래");
        interestList.add("요리");

        em.persist(user);
        em.flush();
    }
}
