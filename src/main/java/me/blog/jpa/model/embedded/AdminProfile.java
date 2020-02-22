package me.blog.jpa.model.embedded;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AdminProfile {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    PrivateData privateData;
}
