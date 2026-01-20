package com.ecom.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.payment.entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long>
{

}
