package serverSide;

import java.io.IOException;
import java.util.ArrayList;


public class EmployeeMainOptions {
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
	public void createOffer()
	{
		OfferInfo newOffer=new OfferInfo();
		ReadWriteOfferInfo infoForOffers=new ReadWriteOfferInfo();
		ListHolder holder=infoForOffers.readFile();
		newOffer.setDestinationName(this.getServerInfo().getScannerInput().nextLine());
		newOffer.setLenghtOfDestination(this.getServerInfo().getScannerInput().nextLine());
		newOffer.setPrice(this.getServerInfo().getScannerInput().nextLine());
		newOffer.setHotels(this.getServerInfo().getScannerInput().nextLine());
		newOffer.setTravelingWithVehicle(this.getServerInfo().getScannerInput().nextLine());
		newOffer.setDaysOfBeginingAndEnd(this.getServerInfo().getScannerInput().nextLine());
		newOffer.setAllBonusInfo(this.getServerInfo().getScannerInput().nextLine());
		newOffer.setCreatorOfOfferName(this.getPerson().getName());
		newOffer.setCreatorOfOfferNumber(this.getPerson().getNumber());
		newOffer.setCreatorOfOfferEmail(this.getPerson().getEmail());
		ArrayList<OfferInfo> listWithOffers=holder.getListOfferInfo();
		listWithOffers.add(newOffer);
		holder.setListOfferInfo(listWithOffers);
		infoForOffers.writeFile(holder);
		this.getServerInfo().getOutputStream().println("The new destination was successfully created !");
	}
	public void viewEmployee()
	{
		ReadWriteEmployeeInfo allEmployees=new ReadWriteEmployeeInfo();
		ListHolder holder=allEmployees.readFile();
		ArrayList<Person> employees=holder.getListPerson();
		for(int i=1;i<employees.size();i++)
		{
			this.getServerInfo().getOutputStream().println("Employee name : "+employees.get(i).getName()+" .Employee mobile number : "+employees.get(i).getNumber()+" .Employee adress : "+employees.get(i).getAdress()+" .Employee E-mail adress : "+employees.get(i).getEmail());
		}
		this.getServerInfo().getOutputStream().println("Return");
		this.getServerInfo().getOutputStream().println("-----------------");
	}
	public void updateOffer()
	{
		int numberInTheList=-1;
		ReadWriteOfferInfo offerInfo=new ReadWriteOfferInfo();
		ListHolder holder=offerInfo.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		String enteredInfo=this.getServerInfo().getScannerInput().nextLine();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getDestinationName().equals(enteredInfo))
			{
				if(list.get(i).getCreatorOfOfferName().equals(getPerson().getName()))
				{
					this.getServerInfo().getOutputStream().println("found");
					numberInTheList=i;
					break;
				}
			}
		}
		if(numberInTheList != -1)
		{
			String lenghtOfDestination=this.getServerInfo().getScannerInput().nextLine();
			String price=this.getServerInfo().getScannerInput().nextLine();
			String hotels=this.getServerInfo().getScannerInput().nextLine();
			String travelingWithVehicles=this.getServerInfo().getScannerInput().nextLine();
			String daysOfBeginingAndEnd=this.getServerInfo().getScannerInput().nextLine();
			String allbonusInfo=this.getServerInfo().getScannerInput().nextLine();
			list.get(numberInTheList).setLenghtOfDestination(lenghtOfDestination);
			list.get(numberInTheList).setPrice(price);
			list.get(numberInTheList).setHotels(hotels);
			list.get(numberInTheList).setTravelingWithVehicle(travelingWithVehicles);
			list.get(numberInTheList).setAllBonusInfo(allbonusInfo);
			list.get(numberInTheList).setDaysOfBeginingAndEnd(daysOfBeginingAndEnd);
			holder.setListOfferInfo(list);
			offerInfo.writeFile(holder);
		}
		else{
			this.getServerInfo().getOutputStream().println("Element not found or no authority !");
			return ;
		}
		this.getServerInfo().getOutputStream().println("Successfully updated offer !");
	}
	public void updateEmployee()
	{
		ReadWriteEmployeeInfo employee=new ReadWriteEmployeeInfo();
		ListHolder holder=employee.readFile();
		ArrayList<Person> list=holder.getListPerson();
		String enteredInfo,newAcc,newPassword,masterKey;
		for(int i=1;i<list.size();i++)
		{
			if(list.get(i).getName().equals(this.getPerson().getName()))
			{
				String choice=this.getServerInfo().getScannerInput().nextLine();
				switch(choice){
				case "1":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					masterKey=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setMasterKey(masterKey);
					list.get(i).setName(enteredInfo); 
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "2": 
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					masterKey=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setMasterKey(masterKey);
					list.get(i).setNumber(enteredInfo); 
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "3":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					masterKey=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setMasterKey(masterKey);
					list.get(i).setEmail(enteredInfo);
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "4":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					masterKey=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setMasterKey(masterKey);
					list.get(i).setAdress(enteredInfo);
					list.get(i).createAccHash(newAcc);
					list.get(i).createPassHash(newPassword);
					break;
				case "5":
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newPassword=this.getServerInfo().getScannerInput().nextLine();
					masterKey=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setMasterKey(masterKey);
					list.get(i).createAccHash(enteredInfo); 
					list.get(i).createPassHash(newPassword);
					break;
				case "6": 
					enteredInfo=this.getServerInfo().getScannerInput().nextLine();
					newAcc=this.getServerInfo().getScannerInput().nextLine();
					masterKey=this.getServerInfo().getScannerInput().nextLine();
					list.get(i).setMasterKey(masterKey);
					list.get(i).createPassHash(enteredInfo);
					list.get(i).createAccHash(newAcc);
					break;
				case "Invalid": return;
				}
				holder.setListPerson(list);
				employee.writeFile(holder);
				break;
			}
		}
		this.getServerInfo().getOutputStream().println("Successfully updated information !");
	}
	public void deleteOffer()
	{
		ReadWriteOfferInfo offerInfo=new ReadWriteOfferInfo();
		ListHolder holder=offerInfo.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		String enteredInfo=this.getServerInfo().getScannerInput().nextLine();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getDestinationName().equals(enteredInfo))
			{
				if(list.get(i).getCreatorOfOfferName().equals(this.getPerson().getName()))
				{
					list.remove(i);
					holder.setListOfferInfo(list);
					offerInfo.writeFile(holder);
					this.getServerInfo().getOutputStream().println("found");
					this.getServerInfo().getOutputStream().println("Successfully deleted destination");
					return;
				}
			}
		}
		this.getServerInfo().getOutputStream().println("Not found");
	}
	public void viewOffers()
	{
		ReadWriteOfferInfo allEmployees=new ReadWriteOfferInfo();
		ListHolder holder=allEmployees.readFile();
		ArrayList<OfferInfo> employees=holder.getListOfferInfo();
		for(int i=1;i<employees.size();i++)
		{
			this.getServerInfo().getOutputStream().println("Destination :"+employees.get(i).getDestinationName());
			this.getServerInfo().getOutputStream().println("Days that this excursion will last : "+employees.get(i).getLenghtOfDestination());
			this.getServerInfo().getOutputStream().println("Price : "+employees.get(i).getPrice());
			this.getServerInfo().getOutputStream().println("Hotels that will be used : "+employees.get(i).getHotels());
			this.getServerInfo().getOutputStream().println("Vehicles that will be used by this agency for this destination : "+employees.get(i).getTravelingWithVehicle());
			this.getServerInfo().getOutputStream().println("Date of begining and ending of the excursion : "+employees.get(i).getDaysOfBeginingAndEnd());
			this.getServerInfo().getOutputStream().println("Bonus information about this excursion : "+employees.get(i).getAllBonusInfo());
			this.getServerInfo().getOutputStream().println("Creator of this offer name : "+employees.get(i).getCreatorOfOfferName());
			this.getServerInfo().getOutputStream().println("Creator of this offer mobile number : "+employees.get(i).getCreatorOfOfferNumber());
			this.getServerInfo().getOutputStream().println("Creator of this offer E-mail adress : "+employees.get(i).getCreatorOfOfferEmail());
			String[] names=employees.get(i).getCustomersNames().split("\\*");
			String[] numbers=employees.get(i).getCustomersNumbers().split("\\*");
			String[] emails=employees.get(i).getCustomersEmails().split("\\*");
			for(int temp=1;temp<names.length;temp++)
			{
				this.getServerInfo().getOutputStream().println("Customers name : "+names[temp]+" .Customers mobile number : "+numbers[temp]+" .Customers E-Mail : "+emails[temp]);
			}
			this.getServerInfo().getOutputStream().println("--------------------------");
		}
		this.getServerInfo().getOutputStream().println("Return");
		this.getServerInfo().getOutputStream().println("-----------------");
	}
	public void viewCustomers()
	{
		ReadWriteCustomerInfo customer=new ReadWriteCustomerInfo();
		ListHolder holder=customer.readFile();
		ArrayList<Person> list=holder.getListPerson();
		for(int i=1;i<list.size();i++)
		{
			this.getServerInfo().getOutputStream().println("Name of customer : "+list.get(i).getName()+" .Number of customer : "+list.get(i).getNumber()+" .The E-mail of the customer : "+list.get(i).getEmail()+" .The adress of the customer : "+list.get(i).getAdress());
		}
		this.getServerInfo().getOutputStream().println("Return");
		this.getServerInfo().getOutputStream().println("-----------------");
	}
public void employeeOptions(Person person,ServerInfo newServer){
	this.setServerInfo(newServer);
	 this.getServerInfo().getOutputStream().println("Employee");
		this.getServerInfo().getOutputStream().println(person.getName());
		this.getServerInfo().getOutputStream().println(person.getAdress());
		this.getServerInfo().getOutputStream().println(person.getNumber());
		this.getServerInfo().getOutputStream().println(person.getEmail());
		this.setPerson(person);
		String userInput=this.getServerInfo().getScannerInput().nextLine();
		while(!userInput.equals("0")){
			switch(userInput){
			case "createOffer": this.createOffer(); break;
			case "showEmployees": this.viewEmployee(); break;
			case "updatePersonalOffer": this.updateOffer(); break;
			case "updatePersonalInfo": this.updateEmployee(); break;
			case "deletePersonalOffer": this.deleteOffer(); break;
			case "showOffers": this.viewOffers(); break;
			case "ViewAllCustomers": this.viewCustomers(); break;
			}
			userInput=this.getServerInfo().getScannerInput().nextLine();
		}
		try{
			this.getServerInfo().getNextClientIfAvailable();
			}catch(IOException e){
				throw new RuntimeException(e);
			}
	}
}
