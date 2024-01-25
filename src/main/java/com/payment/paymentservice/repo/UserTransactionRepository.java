package com.payment.paymentservice.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.paymentservice.Entity.UserTransaction;

@Repository
public interface UserTransactionRepository extends MongoRepository<UserTransaction, String> {
    List<UserTransaction> findByOrderId(String orderId);
}
