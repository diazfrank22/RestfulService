package com.zara.company.infrastructure.adapters.in.apirest.price.dtos;


import com.zara.company.domain.entities.Price;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PriceDto {

    private Long productid;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private Double price;
   private Map<String, String> message;

   public PriceDto(Price price){
       this.productid=price.getProductId();
       this.brandId=price.getBrandId();
       this.priceList=price.getPriceList();
       this.startDate=price.getStartDate();
       this.price=price.getPrice();
   }
}
