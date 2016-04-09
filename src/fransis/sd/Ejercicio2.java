package fransis.sd;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by francisco on 19/03/16.
 */
public class Ejercicio2 {

    public static void main(String ...arg) throws IOException, URISyntaxException {

        Scanner sc = new Scanner(System.in);
        String search;
        do {
            System.out.print("Ingrese la la cadena a buscar: ");
            search = sc.nextLine();
        }while(search.isEmpty());

        File f=File.createTempFile(new String(String.valueOf((int)(Math.random()*1000))),".html");
        PrintWriter pr=new PrintWriter(f);
        System.out.println("Nombre del archivo temporal: "+f.getAbsoluteFile());

        URI uri = new URI("http","www.bing.com","/search?q="+search,null);

        URL url = uri.toURL();
        URLConnection urlConnection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        br.lines().forEach(s -> {//System.out.println(s);
            pr.print(s);
        });

        pr.flush();
        pr.close();

        Runtime.getRuntime().exec("/home/francisco/firefox/firefox "+f.getAbsoluteFile());
    }


}
