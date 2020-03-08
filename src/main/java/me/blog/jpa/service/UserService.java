package me.blog.jpa.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQuery;

import lombok.RequiredArgsConstructor;
import me.blog.jpa.model.elementCollection.QUser;
import me.blog.jpa.model.elementCollection.User;

@RequiredArgsConstructor
@Service
public class UserService {
    private final EntityManager entityManager;
    //    private static final QUser qUser = QUser.user;
    private final QUser qUser = QUser.user;

    public User findById(Long id) {
        JPAQuery<User> query = new JPAQuery<>(entityManager);

        query.from(qUser)
             .where(qUser.id.eq(id));

        return query.fetchOne();
    }
}
