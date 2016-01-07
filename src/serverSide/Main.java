package serverSide;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void createCustomerRegistration(ServerInfo newServer)
	{
		ReadWriteCustomerInfo customer=new ReadWriteCustomerInfo();
		ListHolder holder=customer.readFile();
		ArrayList<Person> list=holder.getListPerson();
		String name=newServer.getScannerInput().nextLine();
		String adress=newServer.getScannerInput().nextLine();
		String number=newServer.getScannerInput().nextLine();
		String email=newServer.getScannerInput().nextLine();
		String accountName=newServer.getScannerInput().nextLine();
		String password=newServer.getScannerInput().nextLine();
		String masterKey=newServer.getScannerInput().nextLine();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getName().equals(name) && list.get(i).getEmail().equals(email) && list.get(i).getNumber().equals(number))
			{
				newServer.getOutputStream().println("Invalid");
				return;
			}
		}
		Person person=new Person();
		person.setName(name);
		person.setAdress(adress);
		person.setNumber(number);
		person.setEmail(email);
		person.setMasterKey(masterKey);
		person.createAccHash(accountName);
		person.createPassHash(password);
		list.add(person);
		holder.setListPerson(list);
		customer.writeFile(holder);
		newServer.getOutputStream().println("Successfully created registration !");
		CustomerMainOptions newCustomer=new CustomerMainOptions();
		newCustomer.customersOptions(person,newServer);
	}
	public static void showOffers(ServerInfo newServer)
	{
		ReadWriteOfferInfo offers=new ReadWriteOfferInfo();
		ListHolder holder=offers.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		for(int i=1;i<list.size();i++)
		{
			newServer.getOutputStream().println("Destination : "+list.get(i).getDestinationName());
			newServer.getOutputStream().println("Days that this excursion will last : "+list.get(i).getLenghtOfDestination());
			newServer.getOutputStream().println("Price : "+list.get(i).getPrice());
			newServer.getOutputStream().println("Hotels that will be used : "+list.get(i).getHotels());
			newServer.getOutputStream().println("Vehicles that will be used by this agency for this destination : "+list.get(i).getTravelingWithVehicle());
			newServer.getOutputStream().println("Date of begining and ending of the excursion : "+list.get(i).getDaysOfBeginingAndEnd());
			newServer.getOutputStream().println("Bonus information about this excursion : "+list.get(i).getAllBonusInfo());
			newServer.getOutputStream().println("Creator of this offer name : "+list.get(i).getCreatorOfOfferName());
			newServer.getOutputStream().println("Creator of this offer mobile number : "+list.get(i).getCreatorOfOfferNumber());
			newServer.getOutputStream().println("Creator of this offer E-mail adress : "+list.get(i).getCreatorOfOfferEmail());
			newServer.getOutputStream().println("--------------------------");
		}
		newServer.getOutputStream().println("Return");
		newServer.getOutputStream().println("---------------------");
	}
	public static void main(String[] args) {
		try{
			ServerInfo newServer=new ServerInfo();
			newServer.createServerLink();
			String temp=newServer.getScannerInput().nextLine();
			while(!temp.equals("True")){
				switch(temp){
				case "1": Validator validate= new Validator(); validate.checkEnteredInfo(newServer); break;
				case "2": createCustomerRegistration(newServer); break;
				case "3": showOffers(newServer); break;
				case "0": newServer.getNextClientIfAvailable(); break;
				}
				temp=newServer.getScannerInput().nextLine();
			}
		}catch(IOException e){
			//System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}catch(Exception e){
			//System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
