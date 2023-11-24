package com.zara.company.application.services;

import com.zara.company.application.ports.in.FinalPriceOfProductsInPort;
import com.zara.company.application.ports.in.dtos.PriceDto;
import com.zara.company.application.ports.out.FinalPriceOfProductsOutPort;
import com.zara.company.common.UseCase;
import com.zara.company.domain.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

        return applyBusinessRuleForProductPrices(inputParametersport).entrySet()
                        .stream()
                        .map(entry -> new PriceDto(entry.getValue()))
                        .collect(Collectors.toList());
    }

    //PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico).
    public Map<LocalDate, Price> applyBusinessRuleForProductPrices(FinalPriceOfProductsOutPort.Parameter inputParametersport) {

        return finalPriceOfProductsPort.searchFinalPriceOfProducts(inputParametersport)
                .stream()
                .collect(Collectors.toMap(
                        price-> price.getStartDate().toLocalDate(),
                        price -> price,
                        (existente, nuevo) -> Integer.parseInt(existente.getPriority()) > Integer.parseInt(nuevo.getPriority()) ? existente : nuevo));

    }

}
