/*Algoritmos y Estructura de Datos
* Hoja de trabajo 10
*Seccion 30
*Autor: Hansel Lopez
*Clase Controller.java
*Fecha: 19/05/2020*/

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Controller {

    //Metodo que inicializa el programa
    public void runProgram() throws IOException{
        //Se declara variables necesarias para abrir el archivo de texto
        ArrayList<GraphModel> graphArrayList = new ArrayList<>();
        File fileTXT = new File ("guategrafo.txt");
        FileReader reader = new FileReader(fileTXT);
        BufferedReader bfReader = new BufferedReader(reader);
        Scanner scanner = new Scanner(reader);
        String line = "";

        //Se declara variables del modelo y se crea una instancia
        GraphModel graph = new GraphModel(); 
        String origin;
        String destination;
        int distancia;
        int cont = 0;

        //Instancia del algoritmo de floyd
        Floyd floyd = new Floyd();

        //Se declara variables usadas en el menu
        ArrayList<String> citiesArrayList = new ArrayList<>();
        long matrix[][]; //matriz 
        Scanner sc = new Scanner(System.in);
        int opcion;
        String enter;

        //Se lee el archivo de texto y se guarda en el arraylist graphArrayList
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            
            //Capturando origen, destino y distancia que contiene el archivo de texto
            origin = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1, line.length());
            destination = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1, line.length());  
            distancia = Integer.parseInt(line.substring(0, line.length()));
            cont++;
            
            //Agregando a ArrayList y cerrando BufferedReader
            graphArrayList.add(new GraphModel(origin, destination, distancia));
            reader.close();
            bfReader.close();
        }

        //Menu Principal
        while (true) {
            System.out.println("_______________________________________________________________________________________--");
    	    System.out.println("    Lucha conta el Covid-19 / Rutas mas cortas hacia hospitales             ");
            System.out.println("_______________________________________________________________________________________--\n");
            System.out.println(" Por favor presione el numero segun la opcion elegida: \n");
            System.out.println(" (1) Mostrar el valor de la ruta más corta entre ciudades por donde pasa esa ruta.");
            System.out.println(" (2) Mostrar la ciudad que queda en el centro del grafo.");
            System.out.println(" (3) Modificar grafo.");
            System.out.println(" (4) Mostrar matriz de adyacencia.");
            System.out.println("_______________________________________________________________________________________--");
            System.out.println(" (5) Salir del programa");
            System.out.println("_______________________________________________________________________________________--\n");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: //Opcion1
                    //Se solicita parametros al usuario
                    System.out.println("\t -Ingresar ciudad de origen:");
                    String orgCity = sc.nextLine();
                    orgCity = sc.nextLine();
                    System.out.println("\t -Ingrese ciudad de destino:");
                    String desCity = sc.nextLine();

                    //Se crea la matriz
                    citiesArrayList.clear();
                    citiesArrayList = graph.generateGraphArray(graphArrayList);
                    matrix = floyd.createMatrix(citiesArrayList, graphArrayList);

                    //Se busca la existencia de una ruta por medio del metodo check si no existe no mostrara nada
                    if(floyd.check(citiesArrayList,orgCity,desCity)){    
                        System.out.println(floyd.FloydAlgorithm(matrix, citiesArrayList, orgCity, desCity));
                        System.out.println("");
                        System.out.println("Presione (0) para continuar... ");
                        enter = sc.next();
                        
                    }else{
                        System.out.println("Dato no existente \n");
                        System.out.println("Presione (0) para continuar...");
                        enter = sc.next();
                    }
                    break;
                case 2: //Opcion2
                    citiesArrayList.clear();
                    citiesArrayList = graph.generateGraphArray(graphArrayList);
                    matrix = floyd.createMatrix(citiesArrayList, graphArrayList); //Se genera matriz
                    floyd.graphCenter(matrix); //Se usa el metodo para mostrar el centro del grafo
                    System.out.println(" La ciudad que queda en el centro del grafo es: (" + citiesArrayList.get(floyd.graphCenter(matrix)) + ") \n");
                    System.out.println("Presione (0) para continuar...");
                    enter = sc.next();
                break;
                case 3:
                    //Opcion3
                break;
                case 4:
                    //Opcion4
                break;
                case 5:
                    //Opcion5
                    break;
                default:
                    //Opcion default
                    break;
            }
        }
    }
}