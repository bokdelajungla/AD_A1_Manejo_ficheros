package requerimiento1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorAlmacen {

	public static void main(String[] args) {

		//Variables
		boolean salir = false;
		int opcion = 0;
		Scanner sc = new Scanner(System.in); 
		ArrayList listaCoches = new ArrayList<Coche>();
		
		//TODO: Verificar si fichero Coches.dat existe
		
		//Inicio menú
		System.out.println("**** Programa de Gestión de Almacenamiento de Coches ****");
		
		//Bucle principal
		while(!salir){
			//Mostramos el Menú
			mostrarMenu();
			
			//Try-Catch para el Scanner
			try {
				opcion=sc.nextInt();
			}
			catch(InputMismatchException e) {
				//Tratamos el error
				opcion = 0; //Inicializamos la opción
			}
			finally {
				sc.nextLine();//Limpiamos el buffer
				
				//Comprobamos la opcion seleccionada
				salir = comprobarOpcion(opcion);
				
			}
		}
		sc.close(); //Cerramos el Scanner
	}

	private static void mostrarMenu () {
		System.out.println("Elija una opción:");
		System.out.println("1 - Añadir nuevo coche");
		System.out.println("2 - Borrar coche por id");
		System.out.println("3 - Consulta coche por id");
		System.out.println("4 - Listado de coches");
		System.out.println("5 - Terminar el programa");
	}
	
	private static boolean comprobarOpcion(int opcion) {
		switch(opcion) {
		case 1: //Añadir nuevo coche

			return false;
			
		case 2: //Borrar coche por id

			return false;
			
		case 3: //Consulta coche por id

			return false;
			
		case 4: //Listado de coches

			return false;

			
		case 5: //Terminar el programa
			//TODO: Crear Coches.dat y salir
			
			return true;
			
		default: //Opción no válida
			System.out.println("Opción no válida. Introduzca un núero entre 1 y 5");
			return false;
		}
	}
}
