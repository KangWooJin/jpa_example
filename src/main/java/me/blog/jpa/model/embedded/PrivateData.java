package me.blog.jpa.model.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class PrivateData {
    private String address;
    private String phoneNumber;
    private String email;
}
