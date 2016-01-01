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

public class ReadWriteOfferInfo implements ReadWrite {
	public ListHolder readFile()
	{
		ArrayList<OfferInfo> list=new ArrayList<OfferInfo>();
		  try{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse("Offers.xml");
				doc.getDocumentElement().normalize();
				
				 NodeList nList = doc.getElementsByTagName("Offers");
				  for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			            	OfferInfo temporary=new OfferInfo();
			               Element eElement = (Element) nNode;
			               temporary.setDestinationName(eElement.getElementsByTagName("Destination").item(0).getTextContent());
			               temporary.setLenghtOfDestination(eElement.getElementsByTagName("Lenght").item(0).getTextContent());
			               temporary.setPrice(eElement.getElementsByTagName("Price").item(0).getTextContent());
			               temporary.setHotels(eElement.getElementsByTagName("Hotels").item(0).getTextContent());
			               temporary.setTravelingWithVehicle(eElement.getElementsByTagName("Vehicles").item(0).getTextContent());
			               temporary.setDaysOfBeginingAndEnd(eElement.getElementsByTagName("DateOfBeginingAndEnd").item(0).getTextContent());
			               temporary.setAllBonusInfo(eElement.getElementsByTagName("BonusInfo").item(0).getTextContent());
			               temporary.setCreatorOfOfferName(eElement.getElementsByTagName("CreatorOfOfferName").item(0).getTextContent());
			               temporary.setCreatorOfOfferNumber(eElement.getElementsByTagName("CreatorOfOfferNumber").item(0).getTextContent());
			               temporary.setCreatorOfOfferEmail(eElement.getElementsByTagName("CreatorOfOfferEmail").item(0).getTextContent());
			               temporary.setCustomersNames(eElement.getElementsByTagName("CustomersNames").item(0).getTextContent());
			               temporary.setCustomersNumbers(eElement.getElementsByTagName("CustomersNumbers").item(0).getTextContent());
			               temporary.setCustomersEmails(eElement.getElementsByTagName("CustomersEmails").item(0).getTextContent());
			               list.add(temporary);
			            }
			         }
		  }catch(Exception e){
			  System.out.println(e.getMessage());
		  }
		  ListHolder holder=new ListHolder();
		  holder.setListOfferInfo(list);
		  return holder;
	}
	public void writeFile(ListHolder holder) // prosto zapisva infoto koeto e predostaveno ot ReadEmployees() !!!
	{
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		 DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder icBuilder;
	        try {
	            icBuilder = icFactory.newDocumentBuilder();
	            Document doc = icBuilder.newDocument();
	            Element mainRootElement = doc.createElementNS("XMLDOM", "Offers");
	            doc.appendChild(mainRootElement);    
	            
	            for(int i=1;i<list.size();i++)
	            {
	            	mainRootElement.appendChild(getEmployee(doc,OfferInfo.AttributeIDOffer, list.get(i).getDestinationName(),
	            			list.get(i).getLenghtOfDestination(), list.get(i).getPrice(),list.get(i).getHotels(),list.get(i).getTravelingWithVehicle(),
	            			list.get(i).getDaysOfBeginingAndEnd(),list.get(i).getAllBonusInfo(),list.get(i).getCreatorOfOfferName(),list.get(i).getCreatorOfOfferNumber(),
	            			list.get(i).getCreatorOfOfferEmail(),list.get(i).getCustomersNames(),list.get(i).getCustomersNumbers(),list.get(i).getCustomersEmails()));
	            }
	            Transformer transformer = TransformerFactory.newInstance().newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
	            DOMSource source = new DOMSource(doc);
	            StreamResult file = new StreamResult(new File("Offers.xml"));
	            transformer.transform(source, file);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	            }
	        }
	public Node getEmployee(Document doc, String id, String destination, String lenght, String price,String hotels,String vehicles,String daysOfBeginingAndEnd,String allbonusInfo,
			String creatorName,String creatorNumber,String creatorEmail,String customerName,String customerNumber,String customerEmail) {
	        Element element = doc.createElement("Offers");
	        element.setAttribute("id", id);
	        element.appendChild(getEmployeeElements(doc, element, "Destination", destination));
	        element.appendChild(getEmployeeElements(doc, element, "Lenght", lenght));
	        element.appendChild(getEmployeeElements(doc, element, "Price", price));
	        element.appendChild(getEmployeeElements(doc,element,"Hotels",hotels));
	        element.appendChild(getEmployeeElements(doc, element, "Vehicles", vehicles));
	        element.appendChild(getEmployeeElements(doc, element, "DateOfBeginingAndEnd",daysOfBeginingAndEnd));
	        element.appendChild(getEmployeeElements(doc, element, "BonusInfo",allbonusInfo));
	        element.appendChild(getEmployeeElements(doc, element, "CreatorOfOfferName",creatorName));
	        element.appendChild(getEmployeeElements(doc, element, "CreatorOfOfferNumber",creatorNumber));
	        element.appendChild(getEmployeeElements(doc, element, "CreatorOfOfferEmail",creatorEmail));
	        element.appendChild(getEmployeeElements(doc, element, "CustomersNames",customerName));
	        element.appendChild(getEmployeeElements(doc, element, "CustomersNumbers",customerNumber));
	        element.appendChild(getEmployeeElements(doc, element, "CustomersEmails",customerEmail));
	        return element;
	    }
	 public Node getEmployeeElements(Document doc, Element element, String name, String value) {
	        Element node = doc.createElement(name);
	        node.appendChild(doc.createTextNode(value));
	        return node;
	    }
}
