package com.zara.company.infrastructure.adapters.out.h2database.price;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FinalPriceOfProductsRepositoryTest {

     @Autowired
     FinalPriceOfProductsRepository startDataRepository;

    @Test
    public void findFinalPriceOfProductsTest() {

        List<PriceEntity> expectedPrices = new ArrayList<>();

        var pricesEntity = new PriceEntity(7L, 1l, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 2L, 35455L, "0", 25.45, "EUR");

        expectedPrices.add(pricesEntity);
        startDataRepository.save(pricesEntity);

        List<PriceEntity> finAllFields = startDataRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(1l, 35455l, LocalDate.parse("2020-08-20"));

        assertEquals(expectedPrices.toString(), finAllFields.toString());
    }


    @Test
    public void findAllFinalPriceOfProductsTest() {

        List<PriceEntity> expectedPrices = new ArrayList<>();

        expectedPrices.add(new PriceEntity(7L,  2l, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 0L, 35455L, "0", 25.45, "EUR"));
        expectedPrices.add(new PriceEntity(8L,  2l, LocalDateTime.parse("2020-08-21T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T19:30:30", DateTimeFormatter.ISO_DATE_TIME), 1L, 35455L, "1", 25.45, "EUR"));
        expectedPrices.add(new PriceEntity(9L,  1l, LocalDateTime.parse("2020-08-22T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T20:20:10", DateTimeFormatter.ISO_DATE_TIME), 1L, 35455L, "0", 25.45, "EUR"));
        expectedPrices.add(new PriceEntity(10L, 1l, LocalDateTime.parse("2020-08-23T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T21:30:00", DateTimeFormatter.ISO_DATE_TIME), 0L, 35455L, "1", 25.45, "EUR"));
        expectedPrices.add(new PriceEntity(11L, 1l, LocalDateTime.parse("2020-08-24T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T22:20:00", DateTimeFormatter.ISO_DATE_TIME), 1L, 35455L, "0", 25.45, "EUR"));
        expectedPrices.add(new PriceEntity(12L, 1l, LocalDateTime.parse("2020-08-25T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T23:30:00", DateTimeFormatter.ISO_DATE_TIME), 0L, 35455L, "1", 25.45, "EUR"));

        startDataRepository.saveAll(expectedPrices);
        List<PriceEntity> finAllData = startDataRepository.findAll();

        for (int i = 0; i < 5; ++i) {

            System.out.println("Test "+i+" : petición a las "+expectedPrices.get(i).getStartDate().getHour() +":"+expectedPrices.get(i).getStartDate().getMinute() + " del día "+expectedPrices.get(i).getStartDate().getDayOfMonth()+" del producto "+expectedPrices.get(i).getProductId()+" para la brand "+expectedPrices.get(i).getBrandId()+"");
            assertEquals(expectedPrices.get(i).toString(),
                         finAllData.get(i).toString(),
                 "Test "+i+" : petición a las "+expectedPrices.get(i).getStartDate().getHour() +" "+expectedPrices.get(i).getStartDate().getMinute() + " del día "+expectedPrices.get(i).getStartDate().getDayOfMonth()+" del producto "+expectedPrices.get(i).getProductId()+" para la brand "+expectedPrices.get(i).getBrandId()+"");


        }

    }

}