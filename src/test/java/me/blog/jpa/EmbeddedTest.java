package me.blog.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import me.blog.jpa.model.embedded.AdminProfile;
import me.blog.jpa.model.embedded.PrivateData;

@DataJpaTest
class EmbeddedTest {
    @Autowired
    private TestEntityManager em;

    @Test
    void 임베디드_객체_공유시에_문제_테스트() {
        AdminProfile firstAdmin = new AdminProfile();
        PrivateData privateData = new PrivateData("서울", "000-000-000", "abc@naver.com");

        firstAdmin.setPrivateData(privateData);

        em.persist(firstAdmin);
        em.flush();

        AdminProfile secondAdmin = new AdminProfile();
        PrivateData firstPrivateData = firstAdmin.getPrivateData();
        firstPrivateData.setAddress("인천");

        secondAdmin.setPrivateData(firstPrivateData);

        em.persist(secondAdmin);

        em.flush();

        AdminProfile firstAdminResult = em.find(AdminProfile.class, firstAdmin.getId());
        AdminProfile secondAdminResult = em.find(AdminProfile.class, secondAdmin.getId());

        System.out.println("firstAdmin address: " + firstAdminResult.getPrivateData().getAddress());
        System.out.println("secondAdmin address: " + secondAdminResult.getPrivateData().getAddress());
        assertThat(firstAdminResult.getPrivateData().getAddress()).isEqualTo("인천");
        assertThat(secondAdminResult.getPrivateData().getAddress()).isEqualTo("인천");
    }
}
