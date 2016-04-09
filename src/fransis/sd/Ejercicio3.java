package fransis.sd;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by francisco on 19/03/16.
 */
public class Ejercicio3 {

    public static void main(String ...arg) throws IOException, URISyntaxException, ParserConfigurationException, SAXException {

        URL url = new URL("http://www.cotizacion-dolar.com.ar");
        URLConnection urlConnection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        br.lines().forEach(s -> {
            /*String s1 = "<span class=\"cotizacion-num\">";
            if(s.contains(s1)) {
                String dolar = s.substring(s.indexOf('>')+1, s.indexOf('<',s1.length()-1));
                System.out.println(dolar);
            }*/
            sb.append(s);
        });

        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            TagNode tagNode = new HtmlCleaner().clean(sb.toString());

            CleanerProperties cleanerProperties = new CleanerProperties();
            Document doc = new org.htmlcleaner.DomSerializer(cleanerProperties).createDOM(tagNode);

            ////*[@id="article"]/div[2]/div/div[5]/span
            NodeList nodes = (NodeList) xpath.evaluate("//*[@id=\"article\"]/div[2]/div/div[5]/span", doc,
                    XPathConstants.NODESET);
            System.out.println("Dolar compra: " + nodes.item(0).getTextContent());

            nodes = (NodeList) xpath.evaluate("//*[@id=\"article\"]/div[2]/div/div[6]/span", doc,
                    XPathConstants.NODESET);
            System.out.println("Dolar venta: " + nodes.item(0).getTextContent());

            nodes = (NodeList) xpath.evaluate("//*[@id=\"article\"]/div[2]/div/div[8]/span", doc,
                    XPathConstants.NODESET);
            System.out.println("Euro compra: " + nodes.item(0).getTextContent());

            nodes = (NodeList) xpath.evaluate("//*[@id=\"article\"]/div[2]/div/div[9]/span", doc,
                    XPathConstants.NODESET);
            System.out.println("Euro venta: " + nodes.item(0).getTextContent());

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        System.out.println("");
    }
}
