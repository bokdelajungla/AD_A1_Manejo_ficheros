package requerimiento1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestorAlmacen {
	
	private static String NOMBRE_FICHERO = "coches.dat";
	
	public static void main(String[] args) {

		//Variables
		boolean salir = false;
		int opcion = 0;
		Scanner sc = new Scanner(System.in); 
		
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		//Verificar si fichero coches.dat existe
		//Podemos poner rutas absolutas tambien "C:/file.txt" o "C:\\file.txt"
		//Tambien podemos crear un directorio, normalmente le quitamos la 
		//extension al fichero // fichero.mkdir();
		File fichero = new File(NOMBRE_FICHERO);// Apuntar al fichero definido de manera relativa
		if (fichero.exists()) {// Averiguamos si existe
			System.out.println("Fichero de datos encontrado, leyendo...");
			try(FileInputStream fis = new FileInputStream(fichero);
					ObjectInputStream ois = new ObjectInputStream(fis)){
				//Hacemos un cast a ArrayList de Coche
				listaCoches = (ArrayList<Coche>)ois.readObject();
				System.out.println("Fichero de datos leído.");
				System.out.println("Obtenida lista de coches.");
			}
			catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("Fichero de datos no encontrado. No hay datos anteriores.");
		}
		
		//Creamos el DAOCoches con la listaCoches del fichero o vacía
		DAOCoche gestorCoches = new DAOCoche(listaCoches);
		
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
				
				int id;
				Coche coche;
				
				switch(opcion) {
				case 1: //Añadir nuevo coche
					System.out.println("Añadir nuevo coche:");
					System.out.println("Intoduzca ID:");
					try {
						id=sc.nextInt();
					}
					catch(InputMismatchException e) {
						//Tratamos el error
						id = 0; //Inicializamos la opción
					}
					sc.nextLine();//Limpiamos el buffer
					System.out.println("Intoduzca Matricula:");
					String matricula = sc.nextLine();
					System.out.println("Intoduzca Marca:");
					String marca = sc.nextLine();
					System.out.println("Intoduzca Modelo:");
					String modelo = sc.nextLine();
					System.out.println("Intoduzca Color:");
					String color = sc.nextLine();
					try {
						coche = new Coche(id, matricula, marca, modelo, color);
						gestorCoches.addCoche(coche);
						System.out.println("Se ha añadido el coche a la lista.");
						System.out.println(coche);
					}
					catch(MatriculaNoValida e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 2: //Borrar coche por id
					System.out.println("Eliminar coche:");
					System.out.println("Intoduzca ID:");
					try {
						id=sc.nextInt();
					}
					catch(InputMismatchException e) {
						//Tratamos el error
						id = 0; //Inicializamos la opción
					}
					sc.nextLine();//Limpiamos el buffer
					coche = gestorCoches.deleteCoche(id);
					if (coche == null) {
						System.out.println("No hay ningún coche con ese ID");
					}
					else {
						System.out.println("Se ha eliminado el coche de la lista:");
						System.out.println(coche);
					}
					break;
					
				case 3: //Consulta coche por id
					System.out.println("Consultar coche:");
					System.out.println("Intoduzca ID:");
					try {
						id=sc.nextInt();
					}
					catch(InputMismatchException e) {
						//Tratamos el error
						id = 0; //Inicializamos la opción
					}
					sc.nextLine();//Limpiamos el buffer
					coche = gestorCoches.buscarCoche(id);
					if (coche == null) {
						System.out.println("No hay ningún coche con ese ID");
					}
					else {
						System.out.println("El Coche solicitado:");
						System.out.println(coche);
					}
					break;
					
				case 4: //Listado de coches
					System.out.println("Listar todos los coches:");
					listaCoches = gestorCoches.listarCoches();
					for(Coche c: listaCoches) {
						System.out.println(c);
					}
					break;

					
				case 5: //Terminar el programa
					//Guardar datos en coches.dat y salir
					try(FileOutputStream fos = new FileOutputStream(fichero);
							ObjectOutputStream oos = new ObjectOutputStream(fos)){
						oos.writeObject(gestorCoches.listarCoches());
						System.out.println("Datos guardados en el fichero coches.dat");
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					finally {
						salir = true;
					}
					break;
					
				default: //Opción no válida
					System.out.println("Opción no válida. Introduzca un núero entre 1 y 5");
					break;
				}
				
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
	
}
