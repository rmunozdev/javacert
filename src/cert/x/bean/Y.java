package cert.x.bean;

import cert.base.bean.Z;

public class Y {

	private String codigo;
	private String value;
	private Z reference;
	
	public Z getReference() {
		return reference;
	}
	public void setReference(Z reference) {
		this.reference = reference;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
