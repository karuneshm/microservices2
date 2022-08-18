package com.karunesh.microservices.currencyexchangeservice;



import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment environment;
	
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	
	//currency-exchange/from/USD/to/INR
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangedValues(@PathVariable(value = "from") String from, 
			@PathVariable(value = "to") String to) {
		//CurrencyExchange currencyExchange = new CurrencyExchange(1000l,from,to, BigDecimal.valueOf(65));
		
		logger.info(" retriveExchangedValues called with {} to {}", from,to);
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(null == currencyExchange) {
			throw new RuntimeException(" Unable to find the data for from"+ from + "to"+to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnviornment(port);
		return currencyExchange;
		
	}

}
