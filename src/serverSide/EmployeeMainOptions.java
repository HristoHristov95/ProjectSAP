package serverSide;

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
		for(int i=0;i<employees.size();i++)
		{
			this.getServerInfo().getOutputStream().println(employees.get(i).getName());
			this.getServerInfo().getOutputStream().println(employees.get(i).getNumber());
			this.getServerInfo().getOutputStream().println(employees.get(i).getAdress());
			this.getServerInfo().getOutputStream().println(employees.get(i).getEmail());
		}
		this.getServerInfo().getOutputStream().println("Return");
	}
	public void updateOffer()
	{
		int numberInTheList=-1;
		ReadWriteEmployeeInfo offerInfo=new ReadWriteEmployeeInfo();
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
	}
	public void updateEmployee()
	{
		ReadWriteEmployeeInfo employee=new ReadWriteEmployeeInfo();
		ListHolder holder=employee.readFile();
		ArrayList<Person> list=holder.getListPerson();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getName().equals(this.getPerson().getName()))
			{
				String choice=this.getServerInfo().getScannerInput().nextLine();
				String enteredInfo=this.getServerInfo().getScannerInput().nextLine();
				switch(choice){
				case "1": list.get(i).setName(enteredInfo); break;
				case "2": list.get(i).setNumber(enteredInfo); break;
				case "3": list.get(i).setEmail(enteredInfo); break;
				case "4": list.get(i).setAdress(enteredInfo); break;
				case "5": list.get(i).createAccHash(enteredInfo); break;
				case "6": list.get(i).createPassHash(enteredInfo); break;
				case "Invalid": return;
				}
				holder.setListPerson(list);
				employee.writeFile(holder);
				break;
			}
		}
		
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
		for(int i=0;i<employees.size();i++)
		{
			this.getServerInfo().getOutputStream().println(employees.get(i).getDestinationName());
			this.getServerInfo().getOutputStream().println(employees.get(i).getLenghtOfDestination());
			this.getServerInfo().getOutputStream().println(employees.get(i).getPrice());
			this.getServerInfo().getOutputStream().println(employees.get(i).getHotels());
			this.getServerInfo().getOutputStream().println(employees.get(i).getTravelingWithVehicle());
			this.getServerInfo().getOutputStream().println(employees.get(i).getDaysOfBeginingAndEnd());
			this.getServerInfo().getOutputStream().println(employees.get(i).getAllBonusInfo());
			this.getServerInfo().getOutputStream().println(employees.get(i).getCreatorOfOfferName());
			this.getServerInfo().getOutputStream().println(employees.get(i).getCreatorOfOfferNumber());
			this.getServerInfo().getOutputStream().println(employees.get(i).getCreatorOfOfferEmail());
			this.getServerInfo().getOutputStream().println(employees.get(i).getCustomersNames());
			this.getServerInfo().getOutputStream().println(employees.get(i).getCustomersNumbers());
			this.getServerInfo().getOutputStream().println(employees.get(i).getCustomersEmails());
		}
		this.getServerInfo().getOutputStream().println("Return");
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
			}
			userInput=this.getServerInfo().getScannerInput().nextLine();
		}
	}
}
