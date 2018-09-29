package cert.lambda.basic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Interfaces funcionales prefabricadas, listas
 * para utilizarse (y que sirven de modelo para crear
 * formas más complejas)
 * @author rmunozdev
 *
 */
public class BuiltIn {

	public static void main(String[] args) {
		BuiltIn test = new BuiltIn();
		String param = "AMD";
		Predicate<String> predicate = test.getPredicate(param);
		
		System.out.println(predicate.test(param));
		System.out.println(predicate.test("02"));
	
		
		Predicate<Integer> integerPredicate = test.getPredicate(1);
		
		System.out.println(integerPredicate.test(1));
		System.out.println(integerPredicate.test(2));
		
		
		Supplier<List<Integer>> supplier = test.getSupplier(Arrays.asList(1,2,4));
		List<Integer> fromSupplier = supplier.get();
		List<Integer> fromSupplierToo = supplier.get();
		
		System.out.println("Supplier brings the same? " + (fromSupplier == fromSupplierToo));
		
		Consumer<int[]> consumer = test.getConsumer(new int[] {1,2,3});
		consumer.accept(new int[]{5,6,7});
	}
	
	public <T> Predicate<T> getPredicate(T t) {
		return input -> t.equals(input);
	}
	
	public <T> Supplier<T> getSupplier(T t) {
		return () -> t;
	}
	
	public <T> Consumer<T> getConsumer(T t) {
		return (x) -> System.out.println(">>" + t + " : " + x);
	}
}
