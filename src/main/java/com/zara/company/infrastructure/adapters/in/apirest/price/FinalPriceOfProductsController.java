package com.zara.company.infrastructure.adapters.in.apirest.price;

import com.zara.company.application.ports.in.FinalPriceOfProductsPort;
import com.zara.company.common.WebAdapter;
import com.zara.company.common.WebAdapterException;
import com.zara.company.infrastructure.adapters.in.apirest.price.dtos.PriceDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("api/prices")
public class FinalPriceOfProductsController {

    @Autowired
    private FinalPriceOfProductsPort finalPriceOfProductsPort;

    @GetMapping
    public List<PriceDto> searchFinalPriceOfProducts(@Valid @RequestBody FinalPriceOfProductsPort.Parameter inputParameter, BindingResult bindingResultValidation){

        FinalPriceOfProductsPort.Parameter InputParameter = new FinalPriceOfProductsPort.Parameter(inputParameter);

        if (bindingResultValidation.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResultValidation.getFieldErrors().forEach(error ->errores.put(error.getField (),error.getDefaultMessage()));
            throw new WebAdapterException( this.getClass(), "searchFinalPriceOfProducts",
                                           Map.of("productId", inputParameter.getProductId(),
                                                  "applicationDate",inputParameter.getApplicationDate(),
                                                  "brandId", inputParameter.getBrandId()),
                                                      String.format(errores.toString())
                                          );
           }
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

