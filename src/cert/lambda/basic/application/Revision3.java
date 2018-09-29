package cert.lambda.basic.application;

public class Revision3 {

	public static void main(String[] args) {
		ValidateService<BeanX> service = new ValidateService<>(BeanX.class);
		
		service.focusString("name").email().maxLength().focusInteger("code").max().min();
		
		BeanX source = new BeanX();
		source.setName("Arturo");
		source.setCode(1);
		boolean result = service.valid(source);
		System.out.println("Result of validation: " + result);
		
		
		BeanX otherSource = new BeanX();
		otherSource.setCode(2);
		otherSource.setName("Roberto");
		boolean result2 = service.valid(otherSource);
		System.out.println("Result 2: " + result2);
		
	}
}


