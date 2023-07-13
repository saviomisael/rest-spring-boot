package io.github.saviomisael;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
	private static final String template = "Hello, %s!";
	private static final AtomicLong counter = new AtomicLong();

	// /greeting?name=sometext
	@RequestMapping("/greeting")
	public Greeting gretting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}