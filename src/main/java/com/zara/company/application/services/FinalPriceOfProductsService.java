package com.zara.company.application.services;

import com.zara.company.application.ports.in.FinalPriceOfProductsPort;
import com.zara.company.application.services.dtos.PriceDto;
import com.zara.company.common.UseCase;
import com.zara.company.domain.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
public class FinalPriceOfProductsService implements FinalPriceOfProductsPort {

    @Autowired
    private final com.zara.company.application.ports.out.FinalPriceOfProductsPort finalPriceOfProductsPort;

    public FinalPriceOfProductsService(com.zara.company.application.ports.out.FinalPriceOfProductsPort finalPriceOfProductsPort) {
        this.finalPriceOfProductsPort = finalPriceOfProductsPort;
    }

    @Override
    public List<PriceDto> searchFinalPriceOfProducts(Parameters inputParameters) {

        var inputParametersport = new com.zara.company.application.ports.out.FinalPriceOfProductsPort.Parameter(inputParameters);

        return finalPriceOfProductsPort.searchFinalPriceOfProducts(inputParametersport)
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
