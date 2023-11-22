package com.zara.company.application.port.out;

import com.zara.company.domain.entities.Price;
import lombok.*;
import java.util.List;

public interface FinalPriceOfProductsPort {
    public List<Price> searchFinalPriceOfProducts(FinalPriceOfProductsPort.Parameter inputParameter);

    @Getter
    @Setter
    class Parameter {

        private String applicationDate;

        private Long productId;

        private Long brandId;

        public Parameter(com.zara.company.application.port.in.FinalPriceOfProductsPort.Parameter inpputParameter) {

            this.applicationDate = inpputParameter.getApplicationDate();
            this.productId = inpputParameter.getProductId();
            this.brandId = inpputParameter.getBrandId();

        }
    }
}
