package com.zara.company.infrastructure.adapters.out.h2database.price;

import com.zara.company.application.ports.in.FinalPriceOfProductsInPort;
import com.zara.company.application.ports.out.FinalPriceOfProductsOutPort;
import com.zara.company.domain.entities.Price;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        var parametersInPort = new FinalPriceOfProductsInPort.Parameters("2020-06-14", 35455l, 2l);
        //out port parameters
        var parametersOutPort = new FinalPriceOfProductsOutPort.Parameter(parametersInPort);

        var priceEntity = new PriceEntity(8l, 1L, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 2L, 35455L, "0", 25.45, "EUR");

        List<PriceEntity> pricesEntityList = new ArrayList<>();

        pricesEntityList.add(priceEntity);

        var expectedPricesEntity = finalPriceOfProductsMapper.entityToDomain(priceEntity);

        Mockito.when(finalPriceOfProductsRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(anyLong(),anyLong(),any(LocalDate.class))).thenReturn(pricesEntityList);

         //When
        List<Price> responseExpectedPricesEntity = finalPriceOfProductsAdapter.searchFinalPriceOfProducts(parametersOutPort);

         //Then;
        assertEquals(expectedPricesEntity.toString(), responseExpectedPricesEntity.get(0).toString());
    }


    @Test
    public void validateResponseOfTheDateParameter() {

        //Given //input port parameters In  //Case 1
        var parameterInPort = new FinalPriceOfProductsInPort.Parameters("2020-06-1", 35455l, 2l);
        //When //out port parameters //Case 1
        try{var parametersOutPort = new FinalPriceOfProductsOutPort.Parameter(parameterInPort);}
        catch (DateTimeParseException e){
            //Then
            System.out.println(e.getMessage());
            assertEquals("Text '2020-06-1' could not be parsed at index 8", e.getMessage());
        }
    }
}
