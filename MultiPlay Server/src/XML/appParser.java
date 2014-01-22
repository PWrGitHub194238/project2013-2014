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

import org.w3c.dom.Attr;
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
			
			Attr attr;
			Element app,temp;
			for(int i=0;i<lista.size();i++)
			{
				app=doc.createElement("app");
				
				attr = doc.createAttribute("id");
				attr.setValue(Integer.toString(lista.get(i).id));
				app.setAttributeNode(attr);
								
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
					 lista.add(new elementApp(Integer.parseInt(elem.getAttribute("id")),elem.getElementsByTagName("name").item(0).getTextContent(),elem.getElementsByTagName("path").item(0).getTextContent()));
				 }
			 }		
			 }catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 }
			 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 } catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return lista;
	}
	
	public static int addApp(String name,String path)
	{
		ArrayList<elementApp> lista=loadXML();

		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).id>i)
			{
				lista.add(i,new elementApp(i,name,path));
				saveXML(lista);
				return i;
			}
		}
		
		lista.add(new elementApp(lista.size(),name,path));		
		saveXML(lista);		
		return lista.size()-1;
	}
	
	public static int deleteApp(int ID)
	{
		ArrayList<elementApp> lista=loadXML();
		
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).id==ID)
			{
				lista.remove(i);
				saveXML(lista);
				return 1;
			}
		}
		
		return 0;
	}
	
	public static String getPath(int ID)
	{
		ArrayList<elementApp> lista=loadXML();
		
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).id==ID)
			{
				return lista.get(i).path;
			}
		}
		
		return null;
	}
	
	public static String getName(int ID)
	{
		ArrayList<elementApp> lista=loadXML();
		
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).id==ID)
			{
				return lista.get(i).name;
			}
		}
		
		return null;
	}
	
}

class elementApp
{
	int id;
	String name;
	String path;
	
	elementApp(int id,String name,String path)
	{
		this.id=id;
		this.name=name;
		this.path=path;
	}
}