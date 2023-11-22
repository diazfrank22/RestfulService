package com.zara.company.application.services;

import com.zara.company.application.port.in.FinalPriceOfProductsPort;
import com.zara.company.common.UseCase;
import com.zara.company.domain.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class FinalPriceOfProductsService implements FinalPriceOfProductsPort {
    @Autowired
    private final com.zara.company.application.port.out.FinalPriceOfProductsPort finalPriceOfProductsPort;

    public FinalPriceOfProductsService(com.zara.company.application.port.out.FinalPriceOfProductsPort finalPriceOfProductsPort) {
        this.finalPriceOfProductsPort = finalPriceOfProductsPort;
    }

    @Override
    public List<Price> searchFinalPriceOfProducts(Parameter inputParameter) {

        var inputParameterport = new com.zara.company.application.port.out.FinalPriceOfProductsPort.Parameter(inputParameter);

        return finalPriceOfProductsPort.searchFinalPriceOfProducts(inputParameterport);
    }
}
