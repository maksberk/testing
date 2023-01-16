package org.example.testing.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    private String id;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;


}
