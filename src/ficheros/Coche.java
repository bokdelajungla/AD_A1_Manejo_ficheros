package ficheros;
import java.io.Serializable;

public class Coche implements Serializable{

	private static final long serialVersionUID = -2620676614923941482L;
	
	private final static String regExp = "^[0-9]{4}[A-Z&&[^AEIOUÑNQ]]{3}$";
	
	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	
	public Coche(int id, String matricula, String marca, String modelo, String color) {
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public static boolean matriculaValida(String matricula) {
		if(matricula.toUpperCase().matches(regExp)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + "]";
	}

	public String toTxt() {
		return  id + "-" + matricula + "-" + marca + "-" + modelo + "-" + color;
	}
		
}
