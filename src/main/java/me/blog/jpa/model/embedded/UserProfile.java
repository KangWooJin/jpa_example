package me.blog.jpa.model.embedded;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    PrivateData privateData;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "office_address")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "office_phone_number")),
            @AttributeOverride(name = "email", column = @Column(name = "office_email"))
    })
    PrivateData officePrivateData;
}
