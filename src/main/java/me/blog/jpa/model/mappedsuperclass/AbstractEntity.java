package me.blog.jpa.model.mappedsuperclass;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    private Long id;

    private OffsetDateTime createdTime;
    private OffsetDateTime modifiedTime;
    private OffsetDateTime removedTime;
}
