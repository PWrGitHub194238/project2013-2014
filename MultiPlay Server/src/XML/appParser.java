package XML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class appParser {

	public static void saveXML(ArrayList<elementApp> lista)
	{
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("apps");
			
			doc.appendChild(root);
			
			Element app,temp;
			for(int i=0;i<lista.size();i++)
			{
				app=doc.createElement("app");
												
				temp=doc.createElement("name");
				temp.appendChild(doc.createTextNode(lista.get(i).name));
				app.appendChild(temp);
				
				temp=doc.createElement("path");
				temp.appendChild(doc.createTextNode(lista.get(i).path));
				app.appendChild(temp);
				
				root.appendChild(app);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("list.xml"));
			
			transformer.transform(source, result);
						
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<elementApp> loadXML()
	{
		File fileList = new File("list.xml");
		ArrayList<elementApp> lista=new ArrayList<elementApp>();
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse(fileList);
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getElementsByTagName("app");
			Node node;
			Element elem;
			
			 for (int i = 0; i < nodes.getLength(); i++)
			 {
				 node = nodes.item(i);
				 if(node.getNodeType() == Node.ELEMENT_NODE) {
					 elem = (Element) node;
					 lista.add(new elementApp(elem.getElementsByTagName("name").item(0).getTextContent(),elem.getElementsByTagName("path").item(0).getTextContent()));
				 }
			 }		
			 }catch (SAXException e) {
			e.printStackTrace();
			 }
			 catch (IOException e) {
			e.printStackTrace();
			 } catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		
		return lista;
	}
	
	public static int addApp(String name,String path)
	{
		ArrayList<elementApp> lista=loadXML();

				lista.add(new elementApp(name,path));
				saveXML(lista);
				return lista.size()-1;
	}
	
	public static int deleteApp(int ID)
	{
		ArrayList<elementApp> lista=loadXML();
		
				lista.remove(ID);
				saveXML(lista);
				return lista.size();
	}
	
	public static String getPath(int ID)
	{
		ArrayList<elementApp> lista=loadXML();
		
		return lista.get(ID).path;
	}
	
	public static String getName(int ID)
	{
		ArrayList<elementApp> lista=loadXML();
		
		return lista.get(ID).name;
	}
	
	public static int getSize()
	{
		ArrayList<elementApp> lista=loadXML();
		
		return lista.size();
	}
	
}

class elementApp
{
	String name;
	String path;
	
	elementApp(String name,String path)
	{
		this.name=name;
		this.path=path;
	}
}