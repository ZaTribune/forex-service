package com.zatribune.forex.domain.mapper;



import com.zatribune.forex.db.entity.TradeRate;
import com.zatribune.forex.domain.TradeRateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TradeRateMapper {

    @Mapping(source = "id.source", target = "source")
    @Mapping(source = "id.destination", target = "destination")
    TradeRateResponse toResponse(TradeRate entity);

    @Mapping(source = "source", target = "id.source")
    @Mapping(source = "destination", target = "id.destination")
    TradeRate toEntity(TradeRateResponse customer);

}
