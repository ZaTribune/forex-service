package com.zatribune.forex.db.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "OPERATION")
public class Operation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "CHAR(64)")
    private String id;

    private String content;

    @Column(name = "CREATION_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @Column(name = "UPDATE_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;
}
