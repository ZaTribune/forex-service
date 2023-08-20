package com.zatribune.forex.db.entity;


import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "TRADE_RATE")
@DynamicUpdate
public class TradeRate {


    @EmbeddedId
    private TradeRateId id;

    private Double rate;


    @Column(name = "CREATION_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @Column(name = "UPDATE_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

}
