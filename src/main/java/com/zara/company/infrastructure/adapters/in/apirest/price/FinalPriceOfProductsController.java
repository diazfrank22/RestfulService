package com.zara.company.infrastructure.adapters.in.apirest.price;

import com.zara.company.application.ports.in.FinalPriceOfProductsPort;
import com.zara.company.application.services.dtos.PriceDto;
import com.zara.company.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAdapter
@RestController
@RequestMapping("api/prices")
public class FinalPriceOfProductsController {

    private final FinalPriceOfProductsPort finalPriceOfProductsPort;

    @Autowired
    public FinalPriceOfProductsController(FinalPriceOfProductsPort finalPriceOfProductsPort) {
        this.finalPriceOfProductsPort = finalPriceOfProductsPort;
    }

    @GetMapping
    public List<ResponseEntity> searchFinalPriceOfProducts(@Valid @RequestBody FinalPriceOfProductsPort.Parameters inputParameters, BindingResult bindingResultValidation){

        FinalPriceOfProductsPort.Parameters InputParameters = new FinalPriceOfProductsPort.Parameters(inputParameters);

        List<PriceDto> finalProductPriceResponse = finalPriceOfProductsPort.searchFinalPriceOfProducts(InputParameters);

        //input parameter validation
        if (bindingResultValidation.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResultValidation.getFieldErrors().forEach(error ->errores.put(error.getField (),error.getDefaultMessage()));
            return Collections.singletonList(new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST));
        }
        //response validation
        if (finalProductPriceResponse.size() < 1) {
            return Collections.singletonList(new ResponseEntity<>(finalProductPriceResponse, HttpStatus.NO_CONTENT));
        }
        if (finalProductPriceResponse.equals(null) || finalProductPriceResponse.isEmpty()){
            return Collections.singletonList(new ResponseEntity<>(finalProductPriceResponse, HttpStatus.NOT_FOUND));
        }
        if (finalProductPriceResponse != null || !finalProductPriceResponse.isEmpty()){
            return Collections.singletonList(new ResponseEntity<>(finalProductPriceResponse, HttpStatus.OK));
        }

        return null;
    }
}

