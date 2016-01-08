package serverSide;

import java.io.File;
import java.io.FileNotFoundException;
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

public class ImportExportAllTheInformation {
	public synchronized boolean importTheInformation(String fileName)
	{
		fileName=fileName+".xml";
		ListHolder holderAdmin;
		ListHolder holderCustomer;
		ListHolder holderEmployee;
		ListHolder holderOffer;
		ReadWriteAdminInfo admin=new ReadWriteAdminInfo();
		ReadWriteCustomerInfo customer=new ReadWriteCustomerInfo();
		ReadWriteEmployeeInfo employee=new ReadWriteEmployeeInfo();
		ReadWriteOfferInfo offers=new ReadWriteOfferInfo();
		holderAdmin=admin.readFile();
		holderCustomer=customer.readFile();
		holderEmployee=employee.readFile();
		holderOffer=offers.readFile();
		ArrayList<Person> listWithAdmins=holderAdmin.getListPerson();
		ArrayList<Person> listWithCustomers=holderCustomer.getListPerson();
		ArrayList<Person> listWithEmployees=holderEmployee.getListPerson();
		ArrayList<OfferInfo> listWithOffers=holderOffer.getListOfferInfo();
		
		// tuka e samoto importvane 
		 try{
			 String checkForAuthority="";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(fileName);
				doc.getDocumentElement().normalize();
				 NodeList nList = doc.getElementsByTagName("AllCurrentInformation");
				  for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			            	OfferInfo temporaryOffer=new OfferInfo();
			            	Person temporary=new Person();
			               Element eElement = (Element) nNode;
			               checkForAuthority=eElement.getAttribute("id");
			               if(checkForAuthority.equals("1"))
			               {
			            	   temporary.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
				               temporary.setAdress(eElement.getElementsByTagName("Adress").item(0).getTextContent());
				               temporary.setNumber(eElement.getElementsByTagName("Tel").item(0).getTextContent());
				               temporary.setEmail(eElement.getElementsByTagName("Email").item(0).getTextContent());
				               temporary.setAccName(eElement.getElementsByTagName("AccName:").item(0).getTextContent());
				               temporary.setPassword(eElement.getElementsByTagName("Password:").item(0).getTextContent());
				               temporary.setAttributeID(Person.attributeIDAdmin);
				               listWithAdmins.add(temporary);
			               }
			               if(checkForAuthority.equals("2"))
			               {
			            	   temporary.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
				               temporary.setAdress(eElement.getElementsByTagName("Adress").item(0).getTextContent());
				               temporary.setNumber(eElement.getElementsByTagName("Tel").item(0).getTextContent());
				               temporary.setEmail(eElement.getElementsByTagName("Email").item(0).getTextContent());
				               temporary.setAccName(eElement.getElementsByTagName("AccName:").item(0).getTextContent());
				               temporary.setPassword(eElement.getElementsByTagName("Password:").item(0).getTextContent());
				               temporary.setAttributeID(Person.attributeIDEmployee);
				               listWithEmployees.add(temporary);
			               }
			               if(checkForAuthority.equals("3"))
			               {
			            	   temporary.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
				               temporary.setAdress(eElement.getElementsByTagName("Adress").item(0).getTextContent());
				               temporary.setNumber(eElement.getElementsByTagName("Tel").item(0).getTextContent());
				               temporary.setEmail(eElement.getElementsByTagName("Email").item(0).getTextContent());
				               temporary.setAccName(eElement.getElementsByTagName("AccName:").item(0).getTextContent());
				               temporary.setPassword(eElement.getElementsByTagName("Password:").item(0).getTextContent());
				               temporary.setAttributeID(Person.attributeIDCustomer);
				               listWithCustomers.add(temporary);
			               }
			               if(checkForAuthority.equals("11"))
			               {
			            	   temporaryOffer.setDestinationName(eElement.getElementsByTagName("Destination").item(0).getTextContent());
			            	   temporaryOffer.setLenghtOfDestination(eElement.getElementsByTagName("Lenght").item(0).getTextContent());
			            	   temporaryOffer.setPrice(eElement.getElementsByTagName("Price").item(0).getTextContent());
			            	   temporaryOffer.setHotels(eElement.getElementsByTagName("Hotels").item(0).getTextContent());
			            	   temporaryOffer.setTravelingWithVehicle(eElement.getElementsByTagName("Vehicles").item(0).getTextContent());
			            	   temporaryOffer.setDaysOfBeginingAndEnd(eElement.getElementsByTagName("DateOfBeginingAndEnd").item(0).getTextContent());
			            	   temporaryOffer.setAllBonusInfo(eElement.getElementsByTagName("BonusInfo").item(0).getTextContent());
			            	   temporaryOffer.setCreatorOfOfferName(eElement.getElementsByTagName("CreatorOfOfferName").item(0).getTextContent());
			            	   temporaryOffer.setCreatorOfOfferNumber(eElement.getElementsByTagName("CreatorOfOfferNumber").item(0).getTextContent());
			            	   temporaryOffer.setCreatorOfOfferEmail(eElement.getElementsByTagName("CreatorOfOfferEmail").item(0).getTextContent());
			            	   temporaryOffer.setCustomersNames(eElement.getElementsByTagName("CustomersNames").item(0).getTextContent());
			            	   temporaryOffer.setCustomersNumbers(eElement.getElementsByTagName("CustomersNumbers").item(0).getTextContent());
			            	   temporaryOffer.setCustomersEmails(eElement.getElementsByTagName("CustomersEmails").item(0).getTextContent());
				               listWithOffers.add(temporaryOffer);
			               }
			            }
			         }
				  holderAdmin.setListPerson(listWithAdmins);
				  holderCustomer.setListPerson(listWithCustomers);
				  holderEmployee.setListPerson(listWithEmployees);
				  holderOffer.setListOfferInfo(listWithOffers);
				  admin.writeFile(holderAdmin);
				  customer.writeFile(holderCustomer);
				  employee.writeFile(holderEmployee);
				  offers.writeFile(holderOffer);
		 }catch(FileNotFoundException e)
		 {
			 System.out.println("The file was not found !");
			 return false;
		  }catch(Exception e){
			  System.out.println(e.getMessage());
			 return false;
		  }
		 return true;
	}
	public synchronized boolean exportTheInformation(String fileName) // Export XML
	{
		fileName=fileName+".xml";
		ListHolder holderAdmin;
		ListHolder holderCustomer;
		ListHolder holderEmployee;
		ListHolder holderOffer;
		ReadWriteAdminInfo admin=new ReadWriteAdminInfo();
		ReadWriteCustomerInfo customer=new ReadWriteCustomerInfo();
		ReadWriteEmployeeInfo employee=new ReadWriteEmployeeInfo();
		ReadWriteOfferInfo offers=new ReadWriteOfferInfo();
		holderAdmin=admin.readFile();
		holderCustomer=customer.readFile();
		holderEmployee=employee.readFile();
		holderOffer=offers.readFile();
		ArrayList<Person> listWithAdmins=holderAdmin.getListPerson();
		ArrayList<Person> listWithCustomers=holderCustomer.getListPerson();
		ArrayList<Person> listWithEmployees=holderEmployee.getListPerson();
		ArrayList<OfferInfo> listWithOffers=holderOffer.getListOfferInfo();
		// tuka zapo4va zapisvaneto mu 
		 DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder icBuilder;
	        try {
	            icBuilder = icFactory.newDocumentBuilder();
	            Document doc = icBuilder.newDocument();
	            Element mainRootElement = doc.createElementNS("XMLDOM", "Data");
	            doc.appendChild(mainRootElement);    
	            for(int i=1;i<listWithAdmins.size();i++)
	            {
	            	mainRootElement.appendChild(getEmployee(doc, Person.attributeIDAdmin,listWithAdmins.get(i).getName(), listWithAdmins.get(i).getAdress(), listWithAdmins.get(i).getNumber(),listWithAdmins.get(i).getEmail(),listWithAdmins.get(i).getAccName(),listWithAdmins.get(i).getPassword()));
	            }
	            for(int i=1;i<listWithEmployees.size();i++)
	            {
	            	mainRootElement.appendChild(getEmployee(doc, Person.attributeIDEmployee,listWithEmployees.get(i).getName(), listWithEmployees.get(i).getAdress(), listWithEmployees.get(i).getNumber(),listWithEmployees.get(i).getEmail(),listWithEmployees.get(i).getAccName(),listWithEmployees.get(i).getPassword()));
	            }   
	            for(int i=1;i<listWithCustomers.size();i++)
	            {
	            	mainRootElement.appendChild(getEmployee(doc, Person.attributeIDCustomer,listWithCustomers.get(i).getName(), listWithCustomers.get(i).getAdress(), listWithCustomers.get(i).getNumber(),listWithCustomers.get(i).getEmail(),listWithCustomers.get(i).getAccName(),listWithCustomers.get(i).getPassword()));
	            }   
	            for(int i=1;i<listWithOffers.size();i++)
	            {
	            	mainRootElement.appendChild(getOffer(doc,OfferInfo.AttributeIDOffer, listWithOffers.get(i).getDestinationName(),
	            			listWithOffers.get(i).getLenghtOfDestination(),listWithOffers.get(i).getPrice(),listWithOffers.get(i).getHotels(),listWithOffers.get(i).getTravelingWithVehicle(),
	            			listWithOffers.get(i).getDaysOfBeginingAndEnd(),listWithOffers.get(i).getAllBonusInfo(),listWithOffers.get(i).getCreatorOfOfferName(),listWithOffers.get(i).getCreatorOfOfferNumber(),
	            			listWithOffers.get(i).getCreatorOfOfferEmail(),listWithOffers.get(i).getCustomersNames(),listWithOffers.get(i).getCustomersNumbers(),listWithOffers.get(i).getCustomersEmails()));
	            }
	            Transformer transformer = TransformerFactory.newInstance().newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
	            DOMSource source = new DOMSource(doc);
	            StreamResult file = new StreamResult(new File(fileName));
	            transformer.transform(source, file);
	        } catch (Exception e) {
	           System.out.println(e.getMessage());
	        	return false;
	            }
	        return true;
	        }
	public synchronized Node getEmployee(Document doc, String id, String name, String adress, String number,String email,String accountName,String passWord) {
		Element element=doc.createElement("AllCurrentInformation");
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
 
	public synchronized Node getOffer(Document doc, String id, String destination, String lenght, String price,String hotels,String vehicles,String daysOfBeginingAndEnd,String allbonusInfo,
			String creatorName,String creatorNumber,String creatorEmail,String customerName,String customerNumber,String customerEmail) {
	        Element element = doc.createElement("AllCurrentInformation");
	        element.setAttribute("id", id);
	        element.appendChild(getOfferElements(doc, element, "Destination", destination));
	        element.appendChild(getOfferElements(doc, element, "Lenght", lenght));
	        element.appendChild(getOfferElements(doc, element, "Price", price));
	        element.appendChild(getOfferElements(doc,element,"Hotels",hotels));
	        element.appendChild(getOfferElements(doc, element, "Vehicles", vehicles));
	        element.appendChild(getOfferElements(doc, element, "DateOfBeginingAndEnd",daysOfBeginingAndEnd));
	        element.appendChild(getOfferElements(doc, element, "BonusInfo",allbonusInfo));
	        element.appendChild(getOfferElements(doc, element, "CreatorOfOfferName",creatorName));
	        element.appendChild(getOfferElements(doc, element, "CreatorOfOfferNumber",creatorNumber));
	        element.appendChild(getOfferElements(doc, element, "CreatorOfOfferEmail",creatorEmail));
	        element.appendChild(getOfferElements(doc, element, "CustomersNames",customerName));
	        element.appendChild(getOfferElements(doc, element, "CustomersNumbers",customerNumber));
	        element.appendChild(getOfferElements(doc, element, "CustomersEmails",customerEmail));
	        return element;
	    }
	 public synchronized Node getOfferElements(Document doc, Element element, String name, String value) {
	        Element node = doc.createElement(name);
	        node.appendChild(doc.createTextNode(value));
	        return node;
	    }
}
