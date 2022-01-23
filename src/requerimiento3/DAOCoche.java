package requerimiento3;

import java.util.ArrayList;

public class DAOCoche {
	
	private ArrayList<Coche> lista;
	
	public DAOCoche(ArrayList<Coche> lista) {
		this.lista=lista;
	}

	/**
	 * Método que añade un Objeto Coche a la lista
	 * @param coche
	 * @return Devuelve 0 en caso de que el coche se haya podido añadir a la lista 
	 * 		   o -1 en caso de que el ID o la matrícula coincidan con la de otro elemento de la lista
	 */
	public int addCoche(Coche coche) {
		for(Coche c:lista) {
			if(c.getId()==coche.getId()) {
				return 1;
			}
			if(c.getMatricula().equals(coche.getMatricula())){
				return 2;
			}
		}
		lista.add(coche);
		return 0;
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
