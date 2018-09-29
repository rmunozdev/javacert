package cert.lambda.basic.application;

import java.util.HashSet;
import java.util.Set;

public class IntegerValidator<T> extends ValidateService<T> {

	private Set<String> uplimit;
	private Set<String> downlimit;
	
	public IntegerValidator(ValidateService<T> context) {
		super(context);
		this.uplimit = new HashSet<>();
		this.downlimit = new HashSet<>();
	}
	
	public IntegerValidator<T> max() {
		uplimit.addAll(this.getFocused());
		return this;
	}
	
	public IntegerValidator<T> min() {
		downlimit.addAll(this.getFocused());
		return this;
	}

	public boolean test(T source) {
		System.out.print("Max validation on: ");
		this.uplimit.forEach(System.out::print);
		System.out.print("\nMin validation on: ");
		this.downlimit.forEach(System.out::println);
		return false;
	}
	
	
}
