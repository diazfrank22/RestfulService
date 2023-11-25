package com.zara.company.infrastructure.adapters.out.h2database.price;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Configuration
public interface FinalPriceOfProductsRepository extends JpaRepository<PriceEntity,Long> {

    @Query("SELECT p FROM PriceEntity p, PriceEntity p1 WHERE p.productId = :productId " +
            "                             AND p.brandId = :brandId " +
            "                             AND CAST(p.startDate AS DATE) = :applicationDate" +
            "                             AND p.startDate <= p1.endDate" +
            "                             AND p.endDate >= p1.startDate")
    public Optional<List<PriceEntity>> findFinalPriceByProductIdAndBrandIdAndAppDate(Long brandId, Long productId, LocalDate applicationDate);

}
