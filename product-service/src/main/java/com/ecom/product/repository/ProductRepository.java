package com.ecom.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.product.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>
{

}
