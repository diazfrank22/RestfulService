package com.zara.company.infrastructure.adapters.out.h2database.price;

import com.zara.company.application.ports.in.FinalPriceOfProductsInPort;
import com.zara.company.application.ports.out.FinalPriceOfProductsOutPort;
import com.zara.company.domain.entities.Price;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class FinalPriceOfProductsAdapterTest {

    @InjectMocks
    FinalPriceOfProductsAdapter finalPriceOfProductsAdapter;

    @InjectMocks
    FinalPriceOfProductsMapper finalPriceOfProductsMapper;

    @Mock
    private FinalPriceOfProductsRepository finalPriceOfProductsRepository;

    @Test
    void getFinalPriceOfProductsTest(){

        //Given
        //input port parameters In
        FinalPriceOfProductsInPort.Parameters parametersInPort = new FinalPriceOfProductsInPort.Parameters("2020-06-14", 35455l, 2l);
        //out port parameters
        FinalPriceOfProductsOutPort.Parameter parametersOutPort = new FinalPriceOfProductsOutPort.Parameter(parametersInPort);

        PriceEntity priceEntity = new PriceEntity(1L, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 2L, 35455L, "0", 25.45, "EUR");

        List<PriceEntity> pricesEntityList = new ArrayList<>();

        pricesEntityList.add(priceEntity);

        Price expectedPricesEntity = finalPriceOfProductsMapper.entityToDomain(priceEntity);

        Mockito.when(finalPriceOfProductsRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(anyLong(),anyLong(),any(LocalDate.class))).thenReturn(pricesEntityList);

         //When
        List<Price> responseExpectedPricesEntity = finalPriceOfProductsAdapter.searchFinalPriceOfProducts(parametersOutPort);

         //Then;
        assertEquals(expectedPricesEntity.toString(), responseExpectedPricesEntity.get(0).toString());
    }
}
