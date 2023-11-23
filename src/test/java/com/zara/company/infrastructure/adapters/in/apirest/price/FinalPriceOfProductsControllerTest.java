package com.zara.company.infrastructure.adapters.in.apirest.price;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zara.company.application.ports.in.FinalPriceOfProductsPort;
import com.zara.company.application.services.dtos.PriceDto;
import com.zara.company.domain.entities.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
@ExtendWith(MockitoExtension.class)
class FinalPriceOfProductsControllerTest {

    private static FinalPriceOfProductsPort.Parameters parameters;
    private static List<PriceDto> finalPriceOfProductsExpected;
    private static ResponseEntity responseEntityExpected;

    private static List<Price> priceEntityDomain = new ArrayList<>();
    private static List<String> finalPriceProductsResEntityExpected = new ArrayList<>();

    @Mock
    private BindingResult bindingResult;

    @Mock
    private FinalPriceOfProductsPort finalPriceOfProductsPort;

    @InjectMocks
    FinalPriceOfProductsController finalPriceOfProductsController;

    @BeforeAll
    public static void setUp(){

        //input Parameters
        parameters = new FinalPriceOfProductsPort.Parameters("202-6-4", 0l, 0l);

        // Simular una lista de objetos
        finalPriceProductsResEntityExpected.add("PriceDto(productid=35455, brandId=1, priceList=1, startDate=2020-06-14T00:00, price=35.5), " +
                                                            "PriceDto(productid=35455, brandId=1, priceList=2, startDate=2020-06-14T00:00, price=25.45), " +
                                                            "PriceDto(productid=35455, brandId=1, priceList=3, startDate=2020-06-14T00:00, price=30.5), " +
                                                            "PriceDto(productid=35455, brandId=1, priceList=4, startDate=2020-06-14T00:00, price=38.95)");

        responseEntityExpected = ResponseEntity.ok(finalPriceProductsResEntityExpected);

        //
        priceEntityDomain.add(new Price(35455L, 1L, 1L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 35.50));
        priceEntityDomain.add(new Price(35455L, 1L, 2L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 25.45));
        priceEntityDomain.add(new Price(35455L, 1L, 3L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 30.50));
        priceEntityDomain.add(new Price(35455L, 1L, 4L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 38.95));

        //
        finalPriceOfProductsExpected = priceEntityDomain.stream()
                .map(price -> PriceDto.builder()
                        .productid(price.getProductId())
                        .brandId(price.getBrandId())
                        .priceList(price.getPriceList())
                        .startDate(price.getStartDate())
                        .price(price.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Test
    public void searchFinalPriceOfProducts() throws JsonProcessingException, NoSuchFieldException {

        //Given
        Mockito.when(finalPriceOfProductsPort.searchFinalPriceOfProducts(ArgumentMatchers.any())).thenReturn(finalPriceOfProductsExpected);
        //when
        var finalPriceOfProductsresponse = finalPriceOfProductsController.searchFinalPriceOfProducts(parameters, mock(BindingResult.class));
        //Then
        assertEquals(responseEntityExpected.toString(), finalPriceOfProductsresponse.get(0).toString());

    }

    @Test
    public void responseFinalPriceOfProductsSuccessful() throws JsonProcessingException, NoSuchFieldException {

        //Given
        Mockito.when(finalPriceOfProductsPort.searchFinalPriceOfProducts(ArgumentMatchers.any())).thenReturn(finalPriceOfProductsExpected);
        //When
        var finalPriceOfProductsresponse = finalPriceOfProductsController.searchFinalPriceOfProducts(parameters, mock(BindingResult.class));
        //Then
        assertEquals("200 OK", finalPriceOfProductsresponse.get(0).getStatusCode().toString());

    }

}