package me.blog.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "locker_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
