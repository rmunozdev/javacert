package cert.lambda.basic.application;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 * Se aplica programación funcional para facilitar manejo de validaciones.
 * El mecanismo de validación debería ser similar a un stream,
 * con capacidad de particionarse, etc.
 * @author rmunozdev
 *
 */
public class ValidatorLegacy<T> {

	private Class<T> target;
	private Predicate<Object> notNullObject;
	private Predicate<String> notEmptyString;
	private Predicate<Collection<?>> notEmptyCollection;
	
	private Map<String,List<Predicate<?>>> map;
	private Set<String> requiredSet;
	
	private Set<String> focused;
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.merge("a", "avion", (s1,s2)-> s1.concat(s2));
	}
	
	
	/**
	 * @TODO Replace with Validator.for (Factory o Builder)
	 * @param target
	 */
	public ValidatorLegacy(Class<T> target) {
		this.target = target;
		this.map = new HashMap<>();
		this.requiredSet = new HashSet<>();
		this.focused = new TreeSet<>();
		
		
		this.notNullObject = o -> o != null;
		this.notEmptyString = s -> !s.isEmpty();
		this.notEmptyCollection = c -> !c.isEmpty();
	}
	
	/**
	 * Enfoca los campos para ser asociados con 
	 * validaciones. Enfoques previos son anulados.
	 * @param fields
	 * @return
	 */
	public ValidatorLegacy<T> focus(String... fields) {
		this.focused.clear();
		this.focused.addAll(Arrays.asList(fields));
		return this;
	}
	
	/**
	 * Marca los campos bajo focus, como campos obligatorios.
	 * @return
	 */
	public ValidatorLegacy<T> required() {
		this.focused.forEach(field->{
			this.requiredSet.add(field);
		});
		return this;
	}
	
	public void valid(T t) {
		//Obtener campos
		Field[] fields = target.getDeclaredFields();
		
		for (Field field : fields) {
			
			//Test if null
			if(t != null) {
//				List<Predicate<? super Object>> tests = this.map.get(field.getName());
//				for (Predicate<? super Object> predicate : tests) {
//					predicate.test(t);
//				}
			} else {
				this.requiredSet.contains(field.getName());
			}
		}
	}
	
	
	/**
	 * Es una validacion opcional (Se aplica solo si hay valor presente).
	 * Se aplica unicamente a String.
	 * @return
	 */
	public ValidatorLegacy<T> number() {
		this.focused.forEach(field -> {
			this.map.merge(field, new ArrayList<Predicate<?>>(), (oldValue,newValue)->{
				oldValue.add((String value)->{
					return value.matches("\\d");
				});
				return oldValue;
			});
		});
		return this;
	}
	
	
	public ValidatorLegacy<T> withExisting(String... fields) {
		//Datasource config
		
		return this;
	}
	
	/**
	 * Revisa la conformidad acorde al tipo de campo.
	 * Implicaria asociar el campo a un predicate capaz de usar
	 * un set de validaciones estándar para:
	 * Emails
	 * Fecha Actual
	 * Fecha en Rango
	 * Numero en Rango
	 * Valor minimo
	 * Valor máximo
	 * Nombres
	 * Apellidos
	 * Descripción en caracteres ANSI ¿?
	 * Ruta de archivo (Windows y Linux)
	 * 
	 * Sin contenido repetido
	 * ...
	 * @param fields
	 * @return
	 */
	public ValidatorLegacy<T> withAdequate(String...fields) {
		return this;
	}
}
