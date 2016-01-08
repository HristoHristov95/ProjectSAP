package serverSide;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadWriteCustomerInfo implements ReadWrite {
	public synchronized ListHolder readFile()
	{
		ArrayList<Person> list=new ArrayList<Person>();
		  try{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse("Customers.xml");
				doc.getDocumentElement().normalize();
		
				 NodeList nList = doc.getElementsByTagName("Customers");
				  for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			            	Person temporary=new Person();
			               Element eElement = (Element) nNode;
			               temporary.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
			               temporary.setAdress(eElement.getElementsByTagName("Adress").item(0).getTextContent());
			               temporary.setNumber(eElement.getElementsByTagName("Tel").item(0).getTextContent());
			               temporary.setEmail(eElement.getElementsByTagName("Email").item(0).getTextContent());
			               temporary.setAccName(eElement.getElementsByTagName("AccName:").item(0).getTextContent());
			               temporary.setPassword(eElement.getElementsByTagName("Password:").item(0).getTextContent());
			               temporary.setAttributeID(Person.attributeIDCustomer);
			               list.add(temporary);
			            }
			         }
		  }catch(Exception e){
			  throw new RuntimeException(e);
		  }
		ListHolder holder=new ListHolder();
		holder.setListPerson(list);
		return holder;
	}
	public synchronized void writeFile(ListHolder holder) // prosto zapisva infoto koeto e predostaveno ot ReadEmployees() !!!
	{
		ArrayList<Person> list=holder.getListPerson();
		 DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder icBuilder;
	        try {
	            icBuilder = icFactory.newDocumentBuilder();
	            Document doc = icBuilder.newDocument();
	            Element mainRootElement = doc.createElementNS("XMLDOM", "Customers");
	            doc.appendChild(mainRootElement);    
	            
	            for(int i=1;i<list.size();i++)
	            {
	            	mainRootElement.appendChild(getEmployee(doc,Person.attributeIDCustomer, list.get(i).getName(), list.get(i).getAdress(), list.get(i).getNumber(),list.get(i).getEmail(),list.get(i).getAccName(),list.get(i).getPassword()));
	            }
	            Transformer transformer = TransformerFactory.newInstance().newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
	            DOMSource source = new DOMSource(doc);
	            StreamResult file = new StreamResult(new File("Customers.xml"));
	            transformer.transform(source, file);
	 
	        } catch (Exception e) {
	        	throw new RuntimeException(e);
	            }
	        }
	public synchronized Node getEmployee(Document doc, String id, String name, String adress, String number,String email,String accountName,String passWord) {
	        Element element = doc.createElement("Customers");
	        element.setAttribute("id", id);
	        element.appendChild(getEmployeeElements(doc, element, "Name", name));
	        element.appendChild(getEmployeeElements(doc, element, "Adress", adress));
	        element.appendChild(getEmployeeElements(doc, element, "Tel", number));
	        element.appendChild(getEmployeeElements(doc,element,"Email",email));
	        element.appendChild(getEmployeeElements(doc, element, "AccName:", accountName));
	        element.appendChild(getEmployeeElements(doc, element, "Password:", passWord));
	        return element;
	    }
	 public synchronized Node getEmployeeElements(Document doc, Element element, String name, String value) {
	        Element node = doc.createElement(name);
	        node.appendChild(doc.createTextNode(value));
	        return node;
	    }
}
