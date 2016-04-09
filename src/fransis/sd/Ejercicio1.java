package fransis.sd;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by francisco on 19/03/16.
 */
public class Ejercicio1 {

    public static void main(String ...arg) throws IOException {

        Scanner sc = new Scanner(System.in);
        String urlString;
        do {
            System.out.print("Ingrese la URL: ");
            urlString = sc.nextLine();
        }while(urlString.isEmpty());

        String nombreArchivo;
        do {
            System.out.print("Ingrese el nombre del archivo: ");
            nombreArchivo = sc.nextLine();
        }while(nombreArchivo.isEmpty());

        File f=new File(nombreArchivo);
        PrintWriter pr=new PrintWriter(f);

        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        br.lines().forEach(s -> {System.out.println(s);pr.print(s);});

        pr.flush();
        pr.close();
    }


}
