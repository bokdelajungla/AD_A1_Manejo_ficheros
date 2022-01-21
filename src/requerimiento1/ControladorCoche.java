package requerimiento1;

import java.util.ArrayList;

public class ControladorCoche {
	
	private ArrayList<Coche> lista;
	
	public ControladorCoche() {
		
	}

	public void addCoche() {
		
	}
	
	public void deleteCoche(String id) {
		
	}
	
	public Coche buscarCoche(int id) {
		for(Coche coche: lista) {
			if (coche.getId() == id){
				return coche;
			}
		}
			
		return null;
	}
	
	public ArrayList<Coche> listarCoches() {
		return lista;
	}
}
