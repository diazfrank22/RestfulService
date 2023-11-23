package com.zara.company.application.services;

import com.zara.company.application.ports.in.FinalPriceOfProductsInPort;
import com.zara.company.application.ports.in.dtos.PriceDto;
import com.zara.company.application.ports.out.FinalPriceOfProductsOutPort;
import com.zara.company.common.UseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
public class FinalPriceOfProductsService implements FinalPriceOfProductsInPort {

    @Autowired
    private final FinalPriceOfProductsOutPort finalPriceOfProductsPort;

    public FinalPriceOfProductsService(FinalPriceOfProductsOutPort finalPriceOfProductsPort) {
        this.finalPriceOfProductsPort = finalPriceOfProductsPort;
    }

    @Override
    public List<PriceDto> searchFinalPriceOfProducts(Parameters inputParameters) {

        FinalPriceOfProductsOutPort.Parameter inputParametersport = new FinalPriceOfProductsOutPort.Parameter(inputParameters);

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
