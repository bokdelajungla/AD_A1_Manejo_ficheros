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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class PantallaPrincipal {
	
	public static final String nombreFichero = "coches.dat";
	public static final String NOMBRE_FICHERO = "coche.txt";
	static ArrayList cocheArray = new ArrayList<Coche>();

	public static void main(String[] args) throws IOException {
		
		File fn = new File(nombreFichero);
		if (fn.exists()) {
			try (FileInputStream fis = new FileInputStream(fn);
					 ObjectInputStream ois = new ObjectInputStream(fis);) {
					
					List<Coche> listaCoches = (List<Coche>)ois.readObject();
					
					System.out.println("Objeto leido");
					
					for(Coche c : listaCoches){
						cocheArray.add(c);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		} else {
			System.out.println("NO EXISTE EL FICHERO");
		}

		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int numero = 0;

		while(!salir){
			
			System.out.println("MENU: Escriba el numero con la opción deseada");
			System.out.println("1 - Añadir nuevo coche");
			System.out.println("2 - Borrar coche por id");
			System.out.println("3 - Consulta de coche por id");
			System.out.println("4 - Listado de coches");
			System.out.println("5 - Terminar el programa");
			System.out.println("6 - Exportar coches a archivo de texto");
			
			try {
				numero =sc.nextInt();
			}
			catch(InputMismatchException e) {
				numero = 0; 
				sc.next();
			}
			finally {
				String[] cocheDatos = new String[5];
				Coche c = new Coche();
				int id =0;
				boolean mismoID = false;
				boolean mismaMatricula = false;
				switch(numero) {
					case 1: 
						sc.nextLine();
						System.out.println("Introduzca los datos del Coche:");
						System.out.println("Id:");
						cocheDatos[0] = sc.nextLine();
						System.out.println("Matricula:");
						cocheDatos[1] = sc.nextLine();
						System.out.println("Marca:");
						cocheDatos[2] = sc.nextLine();
						System.out.println("Modelo:");
						cocheDatos[3] = sc.nextLine();
						System.out.println("Color:");
						cocheDatos[4] = sc.nextLine();
						int idCoche = Integer.parseInt(cocheDatos[0]);
						String matricula = cocheDatos[1];
						System.out.println("Matricula: " + matricula);
						for (int i=0;i<cocheArray.size();i++) {
							System.out.println(((Coche) cocheArray.get(i)).getMatricula());
							if(((Coche) cocheArray.get(i)).getId() == idCoche) {
						    	  mismoID = true;
						      }

						      if(((Coche) cocheArray.get(i)).getMatricula().equals(matricula)) {
						    	  mismaMatricula = true;
						      }
						}  
						if (mismaMatricula == false && mismoID == false) {
							try {
								c.setId(Integer.parseInt(cocheDatos[0]));
								c.setMatricula(cocheDatos[1]);
								c.setMarca(cocheDatos[2]);
								c.setModelo(cocheDatos[3]);
								c.setColor(cocheDatos[4]);
								cocheArray.add(c);
								System.out.println(c.toString());
							}catch(NumberFormatException e) {
								System.out.println("Ha introducido algún campo no válido");
								break;
							}
						}
						if (mismaMatricula == true) {
							System.out.println("Ya existe un coche con esa matricula");
						}
						if (mismoID == true) {
							System.out.println("Ya existe un coche con ese ID");
						}
						break;
					case 2:
						Coche coc = null;
						System.out.println("Introduzca ID");
						try {
							id = sc.nextInt();
						}
						catch(InputMismatchException e) {
							System.out.println("Introduzca un ID válido");
						}
						for (int i=0;i<cocheArray.size();i++) {
						      if(((Coche) cocheArray.get(i)).getId() == id) {
						    	  coc = (Coche) cocheArray.get(i);
						    	  cocheArray.remove(coc);
						      }
						    }
						if (coc == null) {
							System.out.println("No hay ningun coche con este ID");
						} else {
					    	System.out.println("El coche: "+ coc.toString() + "ha sido elmininado");
						}
						break;
					case 3:
						Coche co = null;
						System.out.println("Introduzca ID");
						try {
							id = sc.nextInt();
						}
						catch(InputMismatchException e) {
							System.out.println("Introduzca un ID válido");
						}
						for (int i=0;i<cocheArray.size();i++) {
						      if(((Coche) cocheArray.get(i)).getId() == id) {
						    	co = (Coche) cocheArray.get(i);
						      }
						    }
						if (co == null) {
							System.out.println("No hay ningun coche con este ID");
						} else {
					    	System.out.println(co.toString());
						}
						break;
					case 4:
						if (cocheArray.size() == 0) {
							System.out.println("No existen datos en este array");
						} else {
							System.out.println(cocheArray);

						}
						break;
					case 5:
						if (!fn.exists()) {// Averiguamos si existe
							//Creamos el fichero
							fn.createNewFile();

							System.out.println("Creado el archivo " + fn.getName());
						}	
						try (FileOutputStream fos = new FileOutputStream(fn);
								 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
								//oos = new ObjectOutputStream(new FileOutputStream(new File(nombreFichero)));
							oos.writeObject(cocheArray);
							System.out.println("Objeto introducido");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						salir = true;
						break;
					case 6: 
						System.out.println("Escritura de fichero " + NOMBRE_FICHERO);

						try(FileWriter fw = new FileWriter(NOMBRE_FICHERO);
								BufferedWriter pw = new BufferedWriter(fw);) {
							for (int i=0;i<cocheArray.size();i++) {
								pw.write(((Coche) cocheArray.get(i)).getId() + "-" 
								+ ((Coche) cocheArray.get(i)).getMatricula() + "-" 
								+ ((Coche) cocheArray.get(i)).getMarca() + "-" 
								+ ((Coche) cocheArray.get(i)).getModelo() + "-" 
								+ ((Coche) cocheArray.get(i)).getColor());
								pw.newLine();
							    } 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						System.out.println("Fichero creado y rellenado");
					default:
						System.out.println("Opción no válida");
				}
			}
		}
		sc.close();

	}

}
