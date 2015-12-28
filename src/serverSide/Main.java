package serverSide;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void createCustomerRegistration(ServerInfo newServer)
	{
		String name=newServer.getScannerInput().nextLine();
		String adress=newServer.getScannerInput().nextLine();
		String number=newServer.getScannerInput().nextLine();
		String email=newServer.getScannerInput().nextLine();
		String accountName=newServer.getScannerInput().nextLine();
		String password=newServer.getScannerInput().nextLine();
		Person person=new Person();
		person.setName(name);
		person.setAdress(adress);
		person.setNumber(number);
		person.setEmail(email);
		person.createAccHash(accountName);
		person.createPassHash(password);
		ReadWriteCustomerInfo customer=new ReadWriteCustomerInfo();
		ListHolder holder=customer.readFile();
		ArrayList<Person> list=holder.getListPerson();
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
		for(int i=0;i<list.size();i++)
		{
			newServer.getOutputStream().println(list.get(i).getDestinationName());
			newServer.getOutputStream().println(list.get(i).getLenghtOfDestination());
			newServer.getOutputStream().println(list.get(i).getPrice());
			newServer.getOutputStream().println(list.get(i).getHotels());
			newServer.getOutputStream().println(list.get(i).getTravelingWithVehicle());
			newServer.getOutputStream().println(list.get(i).getDaysOfBeginingAndEnd());
			newServer.getOutputStream().println(list.get(i).getAllBonusInfo());
			newServer.getOutputStream().println(list.get(i).getCreatorOfOfferName());
			newServer.getOutputStream().println(list.get(i).getCreatorOfOfferNumber());
			newServer.getOutputStream().println(list.get(i).getCreatorOfOfferEmail());
		}
	}
	public static void main(String[] args) {
		try{
			ServerInfo newServer=new ServerInfo();
			newServer.createServerLink();
		/*	ServerInfo.server=new ServerSocket(1212);
			ServerInfo.socket=ServerInfo.server.accept();
			newServer.getScannerInput()=new Scanner(ServerInfo.socket.getInputStream());
			newServer.getOutputStream()=new PrintStream(ServerInfo.socket.getOutputStream());*/
			String temp=newServer.getScannerInput().nextLine();
			while(!temp.equals("0")){
				switch(temp){
				case"1": Validator validate= new Validator(); validate.checkEnteredInfo(newServer); break;
				case"2": createCustomerRegistration(newServer); break;
				case"3": showOffers(newServer); break;
				}
				temp=newServer.getScannerInput().nextLine();
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
