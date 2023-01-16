package org.example.testing.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "device")
public class DeviceEntity extends AbstractEntity {

    @Column
    private Integer dpi;

    @Column
    private Integer width;

    @Column
    private Integer height;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "newspaper_name")
    private String newspaperName;
}
