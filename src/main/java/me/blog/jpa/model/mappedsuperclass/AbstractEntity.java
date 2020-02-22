package me.blog.jpa.model.mappedsuperclass;

import java.time.OffsetDateTime;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    private Long id;

    private OffsetDateTime createdTime;
    private OffsetDateTime modifiedTime;
    private OffsetDateTime removedTime;
}
