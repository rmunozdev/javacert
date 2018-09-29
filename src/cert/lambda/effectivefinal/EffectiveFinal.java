package cert.lambda.effectivefinal;

/**
 * Una clase interna local, es construida en un archivo
 * por el compilador. Para que pueda tener acceso a contenido
 * del método que la contiene, se hace una copia.
 * Dicha copia de variable no puede modificarse (De otro modo
 * será inconsistente) por lo cual en Java 7 o anteriores
 * se exigia que se declare dicha variable como Final.
 * A partir de java 8, ya no es necesario dicha declaración,
 * bastará que no se modifique la variable para que
 * se considere "Efectivamente Final".
 * 
 * Lambdas utilizan las mismas reglas de acceso que
 * los Local Inner Class.
 * @author rmunozdev
 *
 */
public class EffectiveFinal {

	public static void main(String[] args) {
		EffectiveFinal test = new EffectiveFinal();
		test.method();
	}
	
	public void method() {
		String code = "123";
		class LocalInnerClass {
			public String method() {
				return "Inner behavior done, outer code: " + code;
			}
		}
		LocalInnerClass localInnerClass = new LocalInnerClass();
		System.out.println(localInnerClass.method());
	}
	
	
}
