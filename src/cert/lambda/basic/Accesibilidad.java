package cert.lambda.basic;

/**
 * Las reglas de acceso a para expresiones Lambda
 * son similares a las de las clases internas locales:
 * 
 * Fields, EffectiveFinal local variables & method arguments.
 * Las variables Final no pueden ser reasignadas ni por
 * la misma expresion Lambda. Pero los fields y argumentos
 * de expresión Lambda si pueden reasignarse.
 * 
 * @author rmunozdev
 *
 */
public class Accesibilidad {
	
	private String field = "FieldABC";

	public static void main(String[] args) {
		Accesibilidad test = new Accesibilidad();
		Sample sample = test.method("Arg456");
		Sample sample2 = test.method("ArgYFG");
		System.out.println("**** Sample 1 ****");
		System.out.println(sample.operate(789));
		test.field = "Field was modified";
		System.out.println("\n**** Sample 2 ****");
		System.out.println(sample2.operate(789));
		System.out.println("\n**** Sample 1 ****");
		System.out.println(sample.operate(789));
		
	}
	
	public Sample method(String argument) {
		StringBuilder variable = new StringBuilder("VarCode123");
		return number -> {
			variable.append(" <Appended inside>");
			return
			"argument: " + argument +
			"\nfield: " + field +
			"\nvariable: " + variable + 
			"\nnumber: " + number;
		};
	}
}


