package com.zara.company;

import com.zara.company.infrastructure.adapters.out.h2database.price.FinalPriceOfProductsRepository;
import com.zara.company.infrastructure.adapters.out.h2database.price.PriceEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StartDatabaseWithData implements InitializingBean {
    private final FinalPriceOfProductsRepository startDataRepository;
    public StartDatabaseWithData(FinalPriceOfProductsRepository startDataRepository) {
        this.startDataRepository = startDataRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        this.startDataRepository.save(new PriceEntity(1L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_DATE_TIME), 1L, 35455L, "0", 35.50, "EUR"));
        this.startDataRepository.save(new PriceEntity(2L, LocalDateTime.parse("2020-06-14T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 2L, 35455L, "0", 25.45, "EUR"));
        this.startDataRepository.save(new PriceEntity(3L, LocalDateTime.parse("2020-06-15T00:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-15T11:00:00", DateTimeFormatter.ISO_DATE_TIME), 3L, 35455L, "0", 30.50, "EUR"));
        this.startDataRepository.save(new PriceEntity(4L, LocalDateTime.parse("2020-06-15T16:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_DATE_TIME), 4L, 35455L, "0", 38.95, "EUR"));

    }
}
