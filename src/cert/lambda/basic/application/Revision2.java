package cert.lambda.basic.application;

public class Revision2 {

	public static void main(String[] args) {
		V2 v2 = new V2();
		
		v2
			.focusA().methodA1().methodA2()
			.focusB().methodB1().methodB2()
			.focusA().methodA1()
		;
		
	}
	
	
}

class V2 {
	
	private Ta ta;
	private Tb tb;
	
	public V2() {
		this.ta = new Ta();
		this.tb = new Tb();
	}
	
	public class Ta {
		public Ta methodA1() {
			return this;
		}
		public Ta methodA2() {
			return this;
		}
		public Tb focusB() {
			return V2.this.tb;
		}
	}
	
	public class Tb extends V2 {
		public Tb methodB1() {
			return this;
		}
		public Tb methodB2() {
			return this;
		}
		public Ta focusA() {
			return V2.this.ta;
		}
	}
	
	public Ta focusA() {
		return this.ta;
	}
	
	public Tb focusB() {
		return this.tb;
	}
}
