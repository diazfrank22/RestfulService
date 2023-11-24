package com.zara.company.infrastructure.adapters.out.h2database.price;

import com.zara.company.domain.entities.Price;

public class FinalPriceOfProductsMapper {

    public static Price entityToDomain(PriceEntity priceEntity) {

        return new Price(
                         priceEntity.getProductId()
                       , priceEntity.getBrandId()
                       , priceEntity.getPriceList()
                       , priceEntity.getStartDate()
                       , priceEntity.getPrice()
                       , priceEntity.getPriority());
    }

    public static PriceEntity domainToEntity(Price price) {

        return new PriceEntity( price.getPriceId()
                              , price.getBrandId()
                              , price.getStartDate()
                              , price.getEndDate()
                              , price.getPriceList()
                              , price.getProductId()
                              , price.getPriority()
                              , price.getPrice()
                              , price.getCurrency());
    }
}
