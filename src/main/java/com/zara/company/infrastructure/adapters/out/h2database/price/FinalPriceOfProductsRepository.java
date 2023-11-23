package com.zara.company.infrastructure.adapters.out.h2database.price;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
//@Configuration
public interface FinalPriceOfProductsRepository extends JpaRepository<PriceEntity,Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId " +
            "                             AND p.brandId = :brandId AND CAST(p.startDate AS DATE) = :applicationDate")
    public List<PriceEntity> findFinalPriceByProductIdAndBrandIdAndAppDate(Long brandId, Long productId, LocalDate applicationDate);

}
