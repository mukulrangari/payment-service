package com.payment.paymentservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.paymentservice.Entity.UserBalance;

@Repository
public interface UserBalanceRepository extends MongoRepository<UserBalance, String> {
}