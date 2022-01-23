package requerimiento3;

import java.util.ArrayList;

public class DAOCoche {
	
	private ArrayList<Coche> lista;
	
	public DAOCoche(ArrayList<Coche> lista) {
		this.lista=lista;
	}

	/**
	 * Método que añade un Objeto Coche a la lista
	 * @param coche el objeto de la clase Coche que se quiere añadir a la lista
	 * @return Devuelve 0 en caso de que el coche se haya podido añadir a la lista 
	 * 		   o -1 en caso de que el ID coincida con la de otro elemento de la lista
	 * 		   o -2 en caso de que la matrícula coincida con la de otro elemento de la lista
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
	
	/**
	 * Método que elimina un objeto Coche de la lista 
	 * @param id el ID del coche que se quiere eliminar
	 * @return el objeto coche que se elimina de la lista o null en caso de que  no exista ningún coche con 
	 * 		   el ID que se pasa como parámetro.
	 */
	public Coche deleteCoche(int id) {
		for(Coche coche: lista) {
			if (coche.getId() == id){
				lista.remove(coche);
				return coche;
			}
		}
		
		return null;
	}
	
	/**
	 * Método para buscar un Coche que tenga el ID indicado
	 * @param id el ID del elemento que se desea buscar
	 * @return el objeto de la clase Coche con el ID que se pasa como parámetro
	 * 		   o null en caso de que no exista ningún elemento con el ID pasado.
	 */
	public Coche buscarCoche(int id) {
		for(Coche coche: lista) {
			if (coche.getId() == id){
				return coche;
			}
		}
			
		return null;
	}
	
	/**
	 * Método que devuelve un ArrayList con todos los Coches
	 * @return un objeto de tipo ArrayList&lt;Coche&gt; que contiene todos los objetos Coches añadidos a la lista
	 */
	public ArrayList<Coche> listarCoches() {
		return lista;
	}
}
