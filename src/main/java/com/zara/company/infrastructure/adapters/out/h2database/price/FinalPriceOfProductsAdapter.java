package com.zara.company.infrastructure.adapters.out.h2database.price;

import com.zara.company.application.ports.out.FinalPriceOfProductsOutPort;
import com.zara.company.common.PersistenceAdapter;
import com.zara.company.domain.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
public class FinalPriceOfProductsAdapter implements FinalPriceOfProductsOutPort {

    @Autowired
    private final FinalPriceOfProductsRepository finalPriceOfProductsRepository;

    public FinalPriceOfProductsAdapter(FinalPriceOfProductsRepository finalPriceOfProductsRepository) {
        this.finalPriceOfProductsRepository = finalPriceOfProductsRepository;
    }


    @Override
    public List<Price> searchFinalPriceOfProducts(Parameter inputParameter) {
        return finalPriceOfProductsRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(inputParameter.getBrandId(),
                                                                                            inputParameter.getProductId(),
                                                                                            inputParameter.getApplicationDate())
                .stream()
                .map(FinalPriceOfProductsMapper::entityToDomain)
                .collect(Collectors.toList());

    }
}