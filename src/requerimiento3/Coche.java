package requerimiento3;

import java.io.Serializable;

public class Coche implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	public final static String MATRICULA_VALIDA = "^[0-9]{4}[A-Z]{3}$";
	
	public Coche (int id, String matricula, String marca, String modelo, String color) throws MatriculaNoValida{
		if(validarMatricula(matricula)) {
			this.id = id;
			this.matricula = matricula;
			this.marca = marca;
			this.modelo = modelo;
			this.color = color;
		}
		else {
			throw new MatriculaNoValida("Matrícula No válida. Debe cumplir el formato 0000XXX");
		}
	}

	public static boolean validarMatricula (String matricula) {
		if(matricula.toUpperCase().matches(MATRICULA_VALIDA)) {
            return true;
        } else {
            return false;
        }
	}
	
	@Override
	public String toString() {
		return id +"-"+ matricula +"-"+ marca +"-"+ modelo +"-"+ color;
	}
	
	/* GETTERS & SETTERS */
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
	
}
