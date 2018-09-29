package cert.lambda.basic.application;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class StringValidator<T> extends ValidateService<T> {
	
	private Set<String> email;
	private Set<String> maxLength;

	public StringValidator(ValidateService<T> context) {
		super(context);
		this.email = new HashSet<>();
		this.maxLength = new HashSet<>();
	}
	
	public StringValidator<T> email() {
		System.out.println("Email validation");
		this.email.addAll(this.getFocused());
		return this;
	}
	
	public StringValidator<T> maxLength() {
		this.maxLength.addAll(this.getFocused());
		return this;
	}
	
	public boolean test(T source) {
		System.out.print("Email validation on: ");
		this.email.forEach(System.out::print);
		this.email.forEach(fieldName -> {
			try {
				Field field = source.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				String value = (String) field.get(source);
				System.out.println("Email value: " + value);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		
		System.out.print("\nmaxLength validation on: ");
		this.maxLength.forEach(System.out::println);
		
		
		
		return false;
	}
}
