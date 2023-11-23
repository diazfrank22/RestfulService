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
//@ContextConfiguration(classes = {JpaRepository.class})
public class FinalPriceOfProductsRepositoryTest {

    private static List<PriceEntity> expectedPrice = new ArrayList<>();
     @Autowired
     FinalPriceOfProductsRepository startDataRepository;

    @Test
    public void findFinalPriceOfProductsTest() {

        PriceEntity entity = new PriceEntity(1L, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 2L, 35455L, "0", 25.45, "EUR");
        expectedPrice.add(entity);
        startDataRepository.save(entity);
        List<PriceEntity> finAllData = startDataRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(1l, 35455l, LocalDate.parse("2020-08-20"));

        assertEquals(expectedPrice.toString(), finAllData.toString());
    }
}