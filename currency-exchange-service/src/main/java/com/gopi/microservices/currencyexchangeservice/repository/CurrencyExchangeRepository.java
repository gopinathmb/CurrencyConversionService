package com.gopi.microservices.currencyexchangeservice.repository;

import com.gopi.microservices.currencyexchangeservice.bean.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    public CurrencyExchange findByFromAndTo(String from,String to);

}
