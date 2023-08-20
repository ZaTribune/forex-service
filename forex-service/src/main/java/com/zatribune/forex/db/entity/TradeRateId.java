package com.zatribune.forex.db.entity;

import lombok.*;
import javax.persistence.Cacheable;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Embeddable
public class TradeRateId implements Serializable {

        private String source;
        private String destination;
}
