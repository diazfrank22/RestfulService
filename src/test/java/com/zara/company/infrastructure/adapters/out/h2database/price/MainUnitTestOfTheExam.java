package com.zara.company.infrastructure.adapters.out.h2database.price;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MainUnitTestOfTheExam {

    public List<PriceEntity> expectedPrices = new ArrayList<>();

    @Autowired
    public FinalPriceOfProductsRepository startDataRepository;

    //@Tag("param")
    @ParameterizedTest(name = "Test {index}: petición a las {1} del día {2} del producto {3}   para la brand {4} (ZARA) {argumentsWithNames}{0}" )
    @MethodSource("priceEntityList")
    void testDebitoCuentaMethodSource(PriceEntity monto) {

        startDataRepository.save(monto);
        List<PriceEntity> finAllData = startDataRepository.findAll();
        assertEquals("["+monto.toString()+"]", finAllData.toString());

    }

    static Stream<PriceEntity> priceEntityList() {

        List<PriceEntity> expectedPrices = new ArrayList<>();

        return Stream.of(new PriceEntity(7L,  2l, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 0L, 35455L, "0", 25.45, "EUR"),
                         new PriceEntity(8L,  2l, LocalDateTime.parse("2020-08-21T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T19:30:30", DateTimeFormatter.ISO_DATE_TIME), 1L, 35455L, "1", 25.45, "EUR"),
                         new PriceEntity(9L,  1l, LocalDateTime.parse("2020-08-22T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T20:20:10", DateTimeFormatter.ISO_DATE_TIME), 1L, 35455L, "0", 25.45, "EUR"),
                         new PriceEntity(10L, 1l, LocalDateTime.parse("2020-08-23T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T21:30:00", DateTimeFormatter.ISO_DATE_TIME), 0L, 35455L, "1", 25.45, "EUR"),
                         new PriceEntity(11L, 1l, LocalDateTime.parse("2020-08-24T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T22:20:00", DateTimeFormatter.ISO_DATE_TIME), 1L, 35455L, "0", 25.45, "EUR"),
                         new PriceEntity(12L, 1l, LocalDateTime.parse("2020-08-25T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T23:30:00", DateTimeFormatter.ISO_DATE_TIME), 0L, 35455L, "1", 25.45, "EUR"));
    }
}
