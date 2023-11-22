package com.zara.company.application.ports.out;

import com.zara.company.domain.entities.Price;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public interface FinalPriceOfProductsPort {
    public List<Price> searchFinalPriceOfProducts(Parameter inputParameter);

    @Getter
    @Setter
    class Parameter {

        private LocalDate applicationDate;

        private Long productId;

        private Long brandId;

        public Parameter(com.zara.company.application.ports.in.FinalPriceOfProductsPort.Parameter inpputParameter) {

            this.applicationDate = LocalDate.parse(inpputParameter.getApplicationDate());
            this.productId = inpputParameter.getProductId();
            this.brandId = inpputParameter.getBrandId();

        }
    }
}
