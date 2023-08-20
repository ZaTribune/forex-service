package com.zatribune.forex.db.repository;

import com.zatribune.forex.db.entity.TradeRate;
import com.zatribune.forex.db.entity.TradeRateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRateRepository extends JpaRepository<TradeRate, TradeRateId> {

}
