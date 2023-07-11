package io.github.saviomisael;

import org.springframework.web.bind.annotation.*;

import io.github.saviomisael.exceptions.NotIsPositiveNumberException;

@RestController
public class MathController {

	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public double sum(@PathVariable(value = "numberOne") double numberOne,
			@PathVariable(value = "numberTwo") double numberTwo) throws NotIsPositiveNumberException {
		if (numberOne < 0 || numberTwo < 0) {
			throw new NotIsPositiveNumberException("O número deve ser positivo.");
		}

		return numberOne + numberTwo;
	}
	
	@GetMapping(value = "/mult/{numberOne}/{numberTwo}")
	public double mult(@PathVariable(value = "numberOne") double numberOne,
			@PathVariable(value = "numberTwo") double numberTwo) throws NotIsPositiveNumberException {
		if (numberOne < 0 || numberTwo < 0) {
			throw new NotIsPositiveNumberException("O número deve ser positivo.");
		}

		return numberOne * numberTwo;
	}
	
	@GetMapping(value = "/div/{numberOne}/{numberTwo}")
	public double div(@PathVariable(value = "numberOne") double numberOne,
			@PathVariable(value = "numberTwo") double numberTwo) throws NotIsPositiveNumberException {
		if (numberOne < 0 || numberTwo < 0) {
			throw new NotIsPositiveNumberException("O número deve ser positivo.");
		}

		return numberOne / numberTwo;
	}
	
	@GetMapping(value = "/sub/{numberOne}/{numberTwo}")
	public double sub(@PathVariable(value = "numberOne") double numberOne,
			@PathVariable(value = "numberTwo") double numberTwo) throws NotIsPositiveNumberException {
		if (numberOne < 0 || numberTwo < 0) {
			throw new NotIsPositiveNumberException("O número deve ser positivo.");
		}
		
//		Essa exceção cai no 500 do CustomResponseEntityExceptionHandler
//		throw new Exception("custom exception");

		return numberOne - numberTwo;
	}
}
