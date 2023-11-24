package com.zara.company.application.ports.in;

import com.zara.company.application.ports.in.dtos.PriceDto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

public interface FinalPriceOfProductsInPort {

    public List<PriceDto> searchFinalPriceOfProducts (FinalPriceOfProductsInPort.Parameters  inputParameters);

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class Parameters {

        //@NotNull
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format invalid must be YYYY-MM-DD")
        private String applicationDate;

        @NotNull
        @Min(value = 1, message = "The field must be greater than 0")
        @Digits(integer = 10, fraction = 0, message = "The field must be between 1 and 10 digits")
        private Long productId;

        @NotNull
        @Min(value = 1, message = "The field must be greater than 0")
        @Digits(integer = 10, fraction = 0, message = "The field must be between 1 and 10 digits")
        private Long brandId;

        public Parameters(Parameters inputParameter) {

            this.applicationDate = inputParameter.getApplicationDate();
            this.productId = inputParameter.getProductId();
            this.brandId = inputParameter.getBrandId();

        }
    }
}