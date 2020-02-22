package me.blog.jpa.model.embedded;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Value
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PrivateData {
    private String address;
    private String phoneNumber;
    private String email;
}
