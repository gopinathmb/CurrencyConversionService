package com.gopi.microservices.currencyconversionservice.controller;

import com.gopi.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.gopi.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {


        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(1001L, from, to, quantity, currencyConversion.getConversionMultiple(), currencyConversion.getConversionMultiple().multiply(quantity), currencyConversion.getEnvironment() + " " + "From Feign");
    }
}
