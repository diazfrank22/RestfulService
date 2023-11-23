package com.zara.company.application.ports.out;

import com.zara.company.application.ports.in.FinalPriceOfProductsInPort;
import com.zara.company.domain.entities.Price;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public interface FinalPriceOfProductsOutPort {
    public List<Price> searchFinalPriceOfProducts(FinalPriceOfProductsOutPort.Parameter inputParameters);

    @Getter
    @Setter
    class Parameter {

        private LocalDate applicationDate;

        private Long productId;

        private Long brandId;

        public Parameter(FinalPriceOfProductsInPort.Parameters inpputParameter) {

            this.applicationDate = LocalDate.parse(inpputParameter.getApplicationDate());
            this.productId = inpputParameter.getProductId();
            this.brandId = inpputParameter.getBrandId();

        }
    }
}
