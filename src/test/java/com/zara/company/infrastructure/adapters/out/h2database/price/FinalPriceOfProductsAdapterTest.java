package com.zara.company.infrastructure.adapters.out.h2database.price;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FinalPriceOfProductsAdapterTest {

    @Inject
    FinalPriceOfProductsAdapter finalPriceOfProductsAdapter;

    @InjectMocks
    private FinalPriceOfProductsRepository finalPriceOfProductsRepository;



    @Test
    void getFinalPriceOfProductsTest(){



        PriceEntity entity = new PriceEntity(1L, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 2L, 35455L, "0", 25.45, "EUR");

        List<PriceEntity> expectedPrice = List.of(entity);

      //  finalPriceOfProductsMapper

      //  finalPriceOfProductsRepository.findFinalPriceByProductIdAndBrandIdAndAppDate();


    }
}
