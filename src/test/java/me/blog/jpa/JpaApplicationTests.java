package me.blog.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestEntityManager
@SpringBootTest
class JpaApplicationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void contextLoads() {
        System.out.println(entityManager.getClass().getName());
    }

}
