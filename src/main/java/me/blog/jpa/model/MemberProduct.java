package me.blog.jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import me.blog.jpa.model.MemberProduct.MemberProductId;

@Entity
@IdClass(MemberProductId.class)
public class MemberProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // MemberProductId.member와 연결

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // MemberProductId.product와 연결

    public static class MemberProductId implements Serializable {
        private String member; // MemberProduct.member와 연결
        private String product; // MemberProduct.product와 연결
    }
}
