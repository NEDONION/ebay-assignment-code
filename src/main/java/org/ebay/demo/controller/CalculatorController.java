package org.ebay.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.model.CalculateRequest;
import org.ebay.demo.model.ChainRequest;
import org.ebay.demo.model.Response;
import org.ebay.demo.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/calculator")
public class CalculatorController {

	private final CalculatorService calculatorService;

	@Autowired
	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@PostMapping("/calculate")
	public Response calculate(@RequestBody CalculateRequest calculateRequest) {
		log.info("CalculatorController: received calculate request={}", calculateRequest);
		Number result = calculatorService.calculate(calculateRequest);
		return Response.builder().code("200").message("Calculation successful").data(result).build();
	}

	@PostMapping("/chain")
	public Response chainOperations(@RequestBody ChainRequest chainRequest) {
		log.info("CalculatorController: received chain request={}", chainRequest);

		log.info("CalculatorController: starting chained calculator");
		Number result = calculatorService.calculateChain(chainRequest);
		return Response.builder().code("200").message("Chained calculation successful").data(result).build();
	}
}