package serverSide;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerMainOptions {
	private ServerInfo info;
	private Person theCurrentPerson;
	public void setPerson(Person p){
		this.theCurrentPerson=p;
	}
	public Person getPerson(){
		return this.theCurrentPerson;
	}
	public void setServerInfo(ServerInfo temp)
	{
		this.info=temp;
	}
	public ServerInfo getServerInfo()
	{
		return this.info;
	}
	public void updatePersonalInfo()
	{
		ReadWriteCustomerInfo customer=new ReadWriteCustomerInfo();
		ListHolder holder=customer.readFile();
		ArrayList<Person> list=holder.getListPerson();
		String newAcc,newPassword,enteredInfo;
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getName().equals(this.getPerson().getName()))
			{
				String choice=this.getServerInfo().getScannerInput().nextLine();
				switch(choice){
				case "1":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setName(enteredInfo); 
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "2": 
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setNumber(enteredInfo); 
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "3":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setEmail(enteredInfo);
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "4":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setAdress(enteredInfo);
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "5":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).createAccHash(enteredInfo); 
					list.get(i).createPassHash(newPassword);
					break;
				case "6": 
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).createPassHash(enteredInfo);
					list.get(i).createAccHash(newAcc);
					break;
				case "Invalid": return;
				}
				holder.setListPerson(list);
				customer.writeFile(holder);
				break;
			}
		}
		this.getServerInfo().getOutputStream().println("Successfully updated your personal information !");
	}
	public void viewOffers()
	{
		ReadWriteOfferInfo allEmployees=new ReadWriteOfferInfo();
		ListHolder holder=allEmployees.readFile();
		ArrayList<OfferInfo> employees=holder.getListOfferInfo();
		for(int i=1;i<employees.size();i++)
		{
			this.getServerInfo().getOutputStream().println("Destination : "+employees.get(i).getDestinationName());
			this.getServerInfo().getOutputStream().println("Days that this excursion will last : "+employees.get(i).getLenghtOfDestination());
			this.getServerInfo().getOutputStream().println("Price : "+employees.get(i).getPrice());
			this.getServerInfo().getOutputStream().println("Hotels that will be used : "+employees.get(i).getHotels());
			this.getServerInfo().getOutputStream().println("Vehicles that will be used by this agency for this destination : "+employees.get(i).getTravelingWithVehicle());
			this.getServerInfo().getOutputStream().println("Date of begining and ending of the excursion : "+employees.get(i).getDaysOfBeginingAndEnd());
			this.getServerInfo().getOutputStream().println("Bonus information about this excursion : "+employees.get(i).getAllBonusInfo());
			this.getServerInfo().getOutputStream().println("Creator of this offer name : "+employees.get(i).getCreatorOfOfferName());
			this.getServerInfo().getOutputStream().println("Creator of this offer mobile number : "+employees.get(i).getCreatorOfOfferNumber());
			this.getServerInfo().getOutputStream().println("Creator of this offer E-mail adress : "+employees.get(i).getCreatorOfOfferEmail());
			this.getServerInfo().getOutputStream().println("--------------------------");
		}
		this.getServerInfo().getOutputStream().println("Return");
		this.getServerInfo().getOutputStream().println("-----------------");
	}
	public void enterInfoForOffer()
	{
		ReadWriteOfferInfo offerInfo=new ReadWriteOfferInfo();
		ListHolder holder=offerInfo.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		String enteredInfo=this.getServerInfo().getScannerInput().nextLine();
		for(int i=1;i<list.size();i++)
		{
			String[] names=list.get(i).getCustomersNames().split("\\*");
			String[] numbers=list.get(i).getCustomersNumbers().split("\\*");
			String[] emails=list.get(i).getCustomersEmails().split("\\*");
			for(int temp=0;temp<names.length;temp++)
			{
				if(names[temp].equals(this.getPerson().getName()) && numbers[temp].equals(this.getPerson().getNumber()) && emails[temp].equals(this.getPerson().getEmail()))
				{
					this.getServerInfo().getOutputStream().println("You have already been registered for this destination !");
					return;
				}
			}
		}
		for(int i=1;i<list.size();i++)
		{
			if(list.get(i).getDestinationName().equals(enteredInfo))
			{
				String names=list.get(i).getCustomersNames();
				names= names+ "*"+this.getPerson().getName();
				String numbers=list.get(i).getCustomersNumbers();
				numbers=numbers+"*"+this.getPerson().getNumber();
				String emails=list.get(i).getCustomersEmails();
				emails=emails+"*"+this.getPerson().getEmail();
				list.get(i).setCustomersNames(names);
				list.get(i).setCustomersNumbers(numbers);
				list.get(i).setCustomersEmails(emails);
				holder.setListOfferInfo(list);
				offerInfo.writeFile(holder);
				this.getServerInfo().getOutputStream().println("Successfully applied for destination !");
				return;
			}
		}
		this.getServerInfo().getOutputStream().println("Could not write information !");
	}
public void customersOptions(Person person,ServerInfo newServer){
	this.setServerInfo(newServer);
	    this.getServerInfo().getOutputStream().println("Customer");
		this.getServerInfo().getOutputStream().println(person.getName());
		this.getServerInfo().getOutputStream().println(person.getAdress());
		this.getServerInfo().getOutputStream().println(person.getNumber());
		this.getServerInfo().getOutputStream().println(person.getEmail());
		this.setPerson(person);
		String userInput=this.getServerInfo().getScannerInput().nextLine();
		while(!userInput.equals("0")){
			switch(userInput){
			case "updatePersonalInfo": this.updatePersonalInfo(); break;
			case "showO": this.viewOffers(); break;
			case "enterInfoForOffer": this.enterInfoForOffer(); break;
			}
			userInput=this.getServerInfo().getScannerInput().nextLine();
		}
		try{
			this.getServerInfo().getNextClientIfAvailable();
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
	}
}
