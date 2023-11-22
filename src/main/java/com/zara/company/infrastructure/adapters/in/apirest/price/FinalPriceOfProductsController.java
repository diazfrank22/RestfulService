package com.zara.apirest.infrastructure.adapters.in.apirest.price;

import com.zara.apirest.application.ports.in.FinalPriceOfProductsPort;
import com.zara.apirest.common.WebAdapter;
import com.zara.apirest.infrastructure.adapters.in.apirest.price.dtos.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("api/prices")
public class FinalPriceOfProductsController {

    @Autowired
    private FinalPriceOfProductsPort finalPriceOfProductsPort;

    @GetMapping
    public List<PriceDto> searchFinalPriceOfProducts(@RequestBody FinalPriceOfProductsPort.Parameter inputParameter){

        FinalPriceOfProductsPort.Parameter InputParameter = new FinalPriceOfProductsPort.Parameter(inputParameter);


        return finalPriceOfProductsPort.searchFinalPriceOfProducts(InputParameter)
                .stream()
                .map(price -> PriceDto.builder()
                        .productid(price.getProductId())
                        .brandId(price.getBrandId())
                        .priceList(price.getPriceList())
                        .startDate(price.getStartDate())
                        .price(price.getPrice())
                        .build())
                .collect(Collectors.toList());

    }
}

