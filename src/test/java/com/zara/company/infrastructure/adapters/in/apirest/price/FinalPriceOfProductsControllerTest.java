package com.zara.company.infrastructure.adapters.in.apirest.price;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zara.company.application.ports.in.FinalPriceOfProductsInPort;
import com.zara.company.application.ports.in.dtos.PriceDto;
import com.zara.company.application.ports.out.FinalPriceOfProductsOutPort;
import com.zara.company.domain.entities.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.CoreMatchers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinalPriceOfProductsControllerTest {

    private static FinalPriceOfProductsInPort.Parameters parameters;
    private static List<PriceDto> finalPriceOfProductsExpected;
    private static ResponseEntity responseEntityExpected;
    private static List<Price> priceEntityDomain = new ArrayList<>();
    private static List<String> finalPriceProductsResEntityExpected = new ArrayList<>();
    @Mock
    private FinalPriceOfProductsInPort finalPriceOfProductsPort;
    @InjectMocks
    FinalPriceOfProductsController finalPriceOfProductsController;
    @BeforeAll
    public static void setUp(){

        //input parameters
        parameters = new FinalPriceOfProductsInPort.Parameters("202-6-4", 0l, 0l);

        // Expected ResponseEntity list
        finalPriceProductsResEntityExpected.add("Optional[[PriceDto(productid=35455, brandId=1, priceList=1, startDate=2020-06-14T00:00, price=35.5), PriceDto(productid=35455, brandId=1, priceList=2, startDate=2020-06-14T00:00, price=25.45), PriceDto(productid=35455, brandId=1, priceList=3, startDate=2020-06-14T00:00, price=30.5), PriceDto(productid=35455, brandId=1, priceList=4, startDate=2020-06-14T00:00, price=38.95)]]");
        responseEntityExpected = ResponseEntity.ok(finalPriceProductsResEntityExpected);

        //Domain Entity List
        priceEntityDomain.add(new Price(35455L, 1L, 1L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 35.50));
        priceEntityDomain.add(new Price(35455L, 1L, 2L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 25.45));
        priceEntityDomain.add(new Price(35455L, 1L, 3L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 30.50));
        priceEntityDomain.add(new Price(35455L, 1L, 4L, LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_DATE_TIME), 38.95));

        //DTOS mapping
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
        assertEquals(responseEntityExpected.getBody().toString(), "["+finalPriceOfProductsresponse.get(0).getBody().toString()+"]");
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

    @Test
    public void ValidationParameter() throws JsonProcessingException, NoSuchFieldException {

        // Given - When - Then
        //{ "applicationDate": "2020-06-14", "productId": "35455", "brandId": "1" }
         given().pathParam("applicationDate", 64l)
                .pathParam("productId", " ")
                .pathParam("brandId", " ")
                .when()
                .get("api/prices")
                .then()
                .statusCode(400)
                .body("errors[0].errorMessage", CoreMatchers.equalTo("El código bcra del banco ingresado es inválido."));

    }
}