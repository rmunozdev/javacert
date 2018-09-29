package cert.lambda.basic.application;

import java.util.Set;

public class Revision {

	public static void main(String[] args) {
		V instance = new V();
		instance
			.getTipoA().specificA1().specificA2()
			.getTipoB().specificB1().specificB2();
	}
}

class V {
	V autoref;
	Set<String> focused;
	TipoA tipoA;
	TipoB tipoB;
	
	public V() {
		this.tipoA = new TipoA(this);
		this.tipoB = new TipoB(this);
	}
	
	public V(V autoref) {
		this.autoref = autoref;
	}

	public TipoA getTipoA() {
		return (autoref != null)? autoref.tipoA: this.tipoA; 
	}
	
	public TipoB getTipoB() {
		return (autoref != null)? autoref.tipoB: this.tipoB; 
	}
	
	protected Set<String> getFocused() {
		return (autoref != null)?autoref.focused: this.focused;
	}
	
}

class TipoA extends V {
	public TipoA(V v) {
		super(v);
	}
	
	public TipoA specificA1() {
		System.out.println("specificA1");
		this.getFocused();
		return this;
	}
	
	public TipoA specificA2() {
		System.out.println("specificA2");
		this.getFocused();
		return this;
	}
	
	
}

class TipoB extends V {
	public TipoB(V v) {
		super(v);
	}
	
	public TipoB specificB1() {
		System.out.println("specificB1");
		return this;
	}
	
	public TipoB specificB2() {
		System.out.println("specificB2");
		return this;
	}
	

}
