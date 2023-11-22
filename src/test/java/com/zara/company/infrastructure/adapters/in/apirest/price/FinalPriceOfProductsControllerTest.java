package com.zara.company.infrastructure.adapters.in.apirest.price;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zara.company.application.ports.in.FinalPriceOfProductsPort;
import com.zara.company.domain.entities.Price;
import com.zara.company.infrastructure.adapters.in.apirest.price.dtos.PriceDto;
import com.zara.company.infrastructure.adapters.out.h2database.price.FinalPriceOfProductsRepository;
import com.zara.company.infrastructure.adapters.out.h2database.price.PriceEntity;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import javax.naming.Binding;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinalPriceOfProductsControllerTest {
    @Mock
    private FinalPriceOfProductsPort finalPriceOfProductsPort;
    @InjectMocks
    FinalPriceOfProductsController finalPriceOfProductsController;
    @Test
    public void searchFinalPriceOfProducts() throws JsonProcessingException {

        //Given
        FinalPriceOfProductsPort.Parameter parameter = new FinalPriceOfProductsPort.Parameter("2020-06-14", 35455l, 2l);

        List<Price> PriceEntityDomain = new ArrayList<>();
        PriceEntityDomain.add(new Price(35455L, 1L, 1L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 35.50));
        PriceEntityDomain.add(new Price(35455L, 1L, 2L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 25.45));
        PriceEntityDomain.add(new Price(35455L, 1L, 3L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 30.50));
        PriceEntityDomain.add(new Price(35455L, 1L, 4L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 38.95));

        var finalPriceOfProductsExpected = PriceEntityDomain.stream()
                                       .map(price-> PriceDto.builder()
                                       .productid(price.getProductId())
                                       .brandId(price.getBrandId())
                                       .priceList(price.getPriceList())
                                       .startDate(price.getStartDate())
                                       .price(price.getPrice())
                                       .build())
                                       .collect(Collectors.toList());


        Mockito.when(finalPriceOfProductsPort.searchFinalPriceOfProducts(ArgumentMatchers.any())).thenReturn(PriceEntityDomain);

        var finalPriceOfProductsresponse = finalPriceOfProductsController.searchFinalPriceOfProducts(parameter, mock(BindingResult.class));


        assertEquals(finalPriceOfProductsExpected, finalPriceOfProductsresponse);

    }
}