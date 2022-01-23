package requerimiento1;

import java.util.ArrayList;

public class DAOCoche {
	
	private ArrayList<Coche> lista;
	
	public DAOCoche(ArrayList<Coche> lista) {
		this.lista=lista;
	}

	public void addCoche(Coche coche) {
		lista.add(coche);
	}
	
	public Coche deleteCoche(int id) {
		for(Coche coche: lista) {
			if (coche.getId() == id){
				lista.remove(coche);
				return coche;
			}
		}
		
		return null;
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
