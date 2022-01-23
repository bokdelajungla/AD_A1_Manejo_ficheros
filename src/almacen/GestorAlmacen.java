package almacen;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class GestorAlmacen {
	
	/*Nombres de los ficheros*/
	private static String NOMBRE_FICHERO = "coches.dat";
	private static String NOMBRE_FICHERO_TEXTO = "coches.txt";
	
	/*Programa Principal*/
	public static void main(String[] args) {

		//Variables
		boolean salir = false; //Variable de control del bucle
		int opcion = 0; //Variable de control del switch
		Scanner sc = new Scanner(System.in); //Entrada de texto del usuario
		
		ArrayList<Coche> listaCoches = new ArrayList<Coche>(); //ArrayList que contendrá los objetos Coche
		
		//Verificar si fichero coches.dat existe
		File fichero = new File(NOMBRE_FICHERO);// Apuntar al fichero definido de manera relativa
		if (fichero.exists()) {// Averiguamos si existe
			System.out.println("Fichero de datos encontrado, leyendo...");
			//Leemos el objeto del fichero como un ArrayList de Coches, que es como se guardará al salir del pograma 
			try(FileInputStream fis = new FileInputStream(fichero);
					ObjectInputStream ois = new ObjectInputStream(fis)){//Autoclose
				//Leemos el Objeto y hacemos un cast a ArrayList de Coches
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
			//Si el fichero no existe
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
				
				int id; //Variable para almacenar el ID
				Coche coche; //Variable para almacenar un objeto Coche
				
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
						//Intentamos crear el objeto Coche invocando su constructor
						coche = new Coche(id, matricula, marca, modelo, color);
						int aux = 0;
						//addCoche() devuelve:
						//0 si se ha podido añadir el coche a la lista
						//1 si el ID ya existía dentro de la lista
						//2 si la matrícula ya existía dentro de la lista
						aux = gestorCoches.addCoche(coche);
						if(aux==0) {
							System.out.println("Se ha añadido el coche a la lista.");
							System.out.println(coche);
						}
						else if(aux==1){
							System.out.println("El coche no se ha añadido a la lista porque su ID estaba duplicado");
						}
						else if(aux==2) {
							System.out.println("El coche no se ha añadido a la lista porque su MATRICULA estaba duplicada");
						}
					}
					//Si la matricula no cumple el formato se lanza una excepcion MatriculaNoValida
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
					//deleteCoche devuelve el objeto coche borrado
					//o null si no existe ningún coche con el ID especificado
					coche = gestorCoches.deleteCoche(id);
					if (coche == null) {
						System.out.println("No hay ningún coche con ese ID");
					}
					else {
						System.out.println("Se ha eliminado el siguente Coche de la lista:");
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
					//buscarCoche() devuelve el objeto Coche con el ID pasado
					//o null en caso de que no exista ningún Coche con dicho ID
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
					//Devuelve la lista completa con todos los Coches
					listaCoches = gestorCoches.listarCoches();
					//Se imprimen por pantalla cada uno de ellos
					for(Coche c: listaCoches) {
						System.out.println(c);
					}
					break;

				case 5: //Escribir coches a fichero de texto
					File texto = new File(NOMBRE_FICHERO_TEXTO); //Sobreescribimos el fichero
					try(FileWriter fw = new FileWriter(texto);
							PrintWriter pw = new PrintWriter(fw)){
						//Obtenemos la lista completa de Coches
						listaCoches = gestorCoches.listarCoches();
						//Añadimos cada uno al fichero pero invocando su método toTxt()
						//que le da el formato requerido
						for(Coche c: listaCoches) {
							pw.println(c.toTxt());
						}
						System.out.println("Fichero de texto creado.");
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
					
				case 6: //Terminar el programa
					//Guardar datos en coches.dat y salir
					try(FileOutputStream fos = new FileOutputStream(fichero); //Al no poner "true" sobreescribimos el fichero
							ObjectOutputStream oos = new ObjectOutputStream(fos)){
						//Guardamos el objejo de la clase ArrayList<Coche> completo
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
					System.out.println("Opción no válida. Introduzca un núero entre 1 y 6");
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
		System.out.println("5 - Escribir coches a fichero de texto");
		System.out.println("6 - Terminar el programa");
	}
	
}
