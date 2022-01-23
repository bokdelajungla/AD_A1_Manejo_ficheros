package ficheros;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Concesionario {

	public static final String nombreFichero = "coches.dat";
	
	public static void main(String[] args) {
		boolean salir = false;
		int opc;
		int id;
		String matricula;
		List<Coche> listaCoches = new ArrayList<Coche>();
		Scanner sc = new Scanner(System.in);
		
		File file = new File(nombreFichero);
		System.out.println("Leemos el fichero");
		
		try(FileInputStream fis = new FileInputStream(nombreFichero); 
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			listaCoches = (List<Coche>) ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("No hay fichero, se procede a crear uno");
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		while(!salir) {
			menu();
			opc = sc.nextInt();
			switch(opc) {
				case 1:
					System.out.println("** Introduzca los datos del coche: **");
					do {
						System.out.print("Id: ");
						id = sc.nextInt();
						for(Coche c: listaCoches) {
							if(id == c.getId()) {
								id = -1;
								System.out.println("Ya existe un coche con este id: ");
								System.out.println(c.toString());
							}
						}
					} while(id == -1);
					// Limpiamos el buffer
					sc.nextLine();
					do {
						System.out.print("Matricula: ");
						matricula = sc.nextLine(); 
						if(!Coche.matriculaValida(matricula)) {
							matricula = "-2";
							System.out.println("Debe introducir una matricula valida");
						} else { 
							for(Coche c: listaCoches) {
								if(matricula.equals(c.getMatricula())) {
									matricula = "-1";
									System.out.println("Ya existe un coche con esta matricula: ");
									System.out.println(c.toString());
								} 
							}
						}
					}while(matricula.equals("-1") || matricula.equals("-2"));
					System.out.print("Marca: ");
					String marca = sc.nextLine();
					System.out.print("Modelo: ");
					String modelo = sc.nextLine();
					System.out.print("Color: ");
					String color = sc.nextLine();
					if(listaCoches.add(new Coche(id, matricula, marca, modelo, color))) {
						System.out.println("Coche añadido con éxito");
					} else {
						System.out.println("No se ha podido añadir el coche");
					}
					break;
				case 2:
					System.out.println("** Introduzca el id del coche a borrar: **");
					System.out.print("Id: ");
					id = sc.nextInt(); 
					Coche aux1 = null;
					for(Coche c: listaCoches) {
						if(c.getId() == id) {
							aux1 = c;
						}
					}
					if(aux1 == null) {
						System.out.println("No se ha encontrado nigún coche con dicho con id = " + id);
					} else {
						listaCoches.remove(aux1);
						System.out.println("Coche con id = " + id + " eliminado con exito");
					}
					break;
				case 3:
					System.out.println("** Introduzca el id del coche a consultar: **");
					System.out.print("Id: ");
					id = sc.nextInt(); 
					Coche aux2 = null;
					for(Coche c: listaCoches) {
						if(c.getId() == id) {
							aux2 = c;
						}
					}
					if(aux2 == null) {
						System.out.println("No se ha encontrado nigún coche con id = " + id);
					} else {
						System.out.println(aux2.toString());
					}
					break;
				case 4:
					System.out.println("** Listado de coches: **");
					for(Coche c: listaCoches) {
						System.out.println(c.toString());
					}
					break;
				case 5:
					try(FileWriter fw = new FileWriter("coches.txt"); 
						BufferedWriter bf = new BufferedWriter(fw)) {
						for(Coche c: listaCoches) {
							bf.write(c.toTxt());
							bf.newLine();
							bf.flush();
						}
					} catch(IOException e) {
						e.printStackTrace();
					}
					System.out.println("Fichero exportado");
					break;
				case 6:
					try(FileOutputStream fos = new FileOutputStream(file); 
						ObjectOutputStream oos = new ObjectOutputStream(fos)) {
						oos.writeObject(listaCoches);
						System.out.println("Se han guardado los datos en el fichero");
					} catch(IOException e) {
						e.printStackTrace();
					}
					System.out.println("Programa finalizado");
					salir = true;
					break;
				default:
					System.out.println("Opción no válida");
					break;
			}
		}
		sc.close();

	}
	
	public static void menu() {
		System.out.println("\n********** Menú **********\n" + 
				"1 - Añadir coche\n" +
				"2 - Borrar coche por id\n" +
				"3 - Consultar coche por id\n" +
				"4 - Listado de coches\n" +
				"5 - Exportar fichero de texto\n" +
				"6 - Salir\n" +
				"**************************\n\n" +
				"Introduzca una opción:");
	}
	
}
