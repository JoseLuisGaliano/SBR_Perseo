package dsi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Lanzador {
	public static void main(String[] args) {
		
		// Directorios por defecto
		String escenarios = "escenarios";
		String soluciones = "soluciones";
		
		// En caso de que se ejecute con argumentos se cambian los directorios
		if(args.length >= 1) escenarios = args[0];
		if(args.length >= 2) soluciones = args[1];
		
		// Cogemos los paths de los directorios de entrada y salida
		Path dirEntrada = Paths.get(escenarios);
		Path dirSalida = Paths.get(soluciones);
		
		// Inicializamos la sesión
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();		
		KieSession kSession = kContainer.newKieSession("ksession-rules-dsi");
		
		// Lanzamos las reglas para cada fichero en el directorio escenarios
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirEntrada, "*.txt")) {
			for (Path entry : stream) {
				// Creamos el archivo de salida
				String nombreEntrada = entry.getFileName().toString();
				String nombreSalida = nombreEntrada.replace(".txt", ".Respuesta.txt");
				Path pathSalida = dirSalida.resolve(nombreSalida);
	            
				try (PrintStream fileOut = new PrintStream(new FileOutputStream(pathSalida.toFile()))) {
					// Redirigimos la salida del sistema al fichero de respuesta
					System.setOut(fileOut);
					
					// Poblamos la base de hechos
					BaseHechos bh = new BaseHechos(kSession);
					bh.poblar();
					
					// Llamamos al lector de fichero
		            LectorFichero lf = new LectorFichero(bh.getSeres(), bh.getObjetos(), bh.getPosesiones(), kSession);
		            try {
		            	lf.leerArchivo(entry.toString());
		            } catch (IOException e) {
		            	e.printStackTrace();
		            }
		            
		            // Lanzamos la ejecución de las reglas
		            kSession.fireAllRules();
		            
		            // Limpiamos la sesión para el próximo archivo
		            kSession.dispose();
		            kSession = kContainer.newKieSession("ksession-rules-dsi");
				}
				catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		/*
		// EJECUCIÓN PARA DEBUGGEAR
		
		// Poblamos la base de hechos
		BaseHechos bh = new BaseHechos(kSession);
		bh.poblar();
		 
		// Llamamos al lector de fichero
		LectorFichero lf = new LectorFichero(bh.getSeres(), bh.getObjetos(), bh.getPosesiones(), kSession);
		try {
			lf.leerArchivo("escenarios/F3-7.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		kSession.fireAllRules();
		*/
		
	}
}
