package com.goods.shop.repository;

import com.goods.shop.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select p FROM Product p WHERE p.category.name = :categoryName")
    List<Product> findAllByCategoryName(@Param("categoryName") String categoryName);
}
