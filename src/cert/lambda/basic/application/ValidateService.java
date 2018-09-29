package cert.lambda.basic.application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidateService<T> {

	private Class<T> target;
	private ValidateService<T> autoref;
	private Set<String> focused;
	private Set<String> required;
	private StringValidator<T> stringValidator;
	private IntegerValidator<T> integerValidator;
	
	public ValidateService(Class<T> target) {
		this.target = target;
		this.focused = new HashSet<>();
		this.required = new HashSet<>();
		this.stringValidator = new StringValidator<>(this);
		this.integerValidator = new IntegerValidator<>(this);
	}

	protected Set<String> getFocused() {
		return (autoref == null) ? this.focused: this.autoref.focused;
	}
	
	protected ValidateService(ValidateService<T> autoref) {
		this.autoref = autoref;
	}
	
	public StringValidator<T> focusString(String... fields) {
		this.focus(fields);
		return (autoref == null) ? this.stringValidator: this.autoref.stringValidator;
	}
	
	public IntegerValidator<T> focusInteger(String... fields) {
		this.focus(fields);
		return (autoref == null) ? this.integerValidator: this.autoref.integerValidator;
	}
	
	public ValidateService<T> focus(String... fields) {
		this.getFocused().clear();
		this.getFocused().addAll(Arrays.asList(fields));
		return (autoref == null) ? this: this.autoref;
	}
	
	public ValidateService<T> required(){
		ValidateService<T> validate = (autoref == null) ? this: this.autoref;
		validate.required.addAll(this.getFocused());
		return validate;
	}
	
	public boolean valid(T source) {
		return this.stringValidator.test(source) & 
				this.integerValidator.test(source);
	}
}
