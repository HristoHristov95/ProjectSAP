package serverSide;
import java.util.ArrayList;

public class AdminMainOptions {
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
	public void createEmployee(){
		Person person=new Person();
		person.setName(this.getServerInfo().getScannerInput().nextLine());
		person.setAdress(this.getServerInfo().getScannerInput().nextLine());
		person.setNumber(this.getServerInfo().getScannerInput().nextLine());
		person.setEmail(this.getServerInfo().getScannerInput().nextLine());
		person.createAccHash(this.getServerInfo().getScannerInput().nextLine());
		person.createPassHash(this.getServerInfo().getScannerInput().nextLine());
		ReadWriteEmployeeInfo newEmployee=new ReadWriteEmployeeInfo();
		ListHolder temp=newEmployee.readFile();
		ArrayList<Person> allPeople=temp.getListPerson();
		allPeople.add(person);
		temp.setListPerson(allPeople);
		newEmployee.writeFile(temp);
		this.getServerInfo().getOutputStream().println("Successfully created new employee !");
	}
	public void deleteEmployee(){
		boolean checker=false;
		Person person=new Person();
		person.setName(this.getServerInfo().getScannerInput().nextLine());
		person.setNumber(this.getServerInfo().getScannerInput().nextLine());
		ReadWriteEmployeeInfo newEmployee=new ReadWriteEmployeeInfo();
		ListHolder temp=newEmployee.readFile();
		ArrayList<Person> list=temp.getListPerson();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getName().equals(person.getName()) && list.get(i).getNumber().equals(person.getNumber()))
			{
				checker=true;
				list.remove(i);
			}
		}
		if(checker==false)
		{
			this.getServerInfo().getOutputStream().println("Employee was not found and was not deleted !");
			return ;
		}
		temp.setListPerson(list);
		newEmployee.writeFile(temp);
		this.getServerInfo().getOutputStream().println("Successfully deleted employee !");
	}
	public void deleteCustomer(){ // trqbva da trie informaciqta ot ofertite za toq 4ovek
		
		boolean checker=false;
		Person person=new Person();
		person.setName(this.getServerInfo().getScannerInput().nextLine());
		if(person.getName().equals("Return"))
		{
			return;
		}
		person.setNumber(this.getServerInfo().getScannerInput().nextLine());
		ReadWriteCustomerInfo newCustomer=new ReadWriteCustomerInfo();
		ListHolder temp=newCustomer.readFile();
		ArrayList<Person> list=temp.getListPerson();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getName().equals(person.getName()) && list.get(i).getNumber().equals(person.getNumber()))
			{
				checker=true;
				list.remove(i);
			}
		}
		if(checker==false){
			this.getServerInfo().getOutputStream().println("Customer was not found and was not deleted !");
			return ;
		}
		temp.setListPerson(list);
		newCustomer.writeFile(temp);
		this.getServerInfo().getOutputStream().println("Successfully deleted customer !");
	}
	public void createOffer(){
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
	public void viewEmployee(){
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
	public void updateOffer(){
		int updateNumber=-1;
		String enteredOfferName=this.getServerInfo().getScannerInput().nextLine();
		ReadWriteOfferInfo offerinfo=new ReadWriteOfferInfo();
		ListHolder holder=offerinfo.readFile();
		ArrayList<OfferInfo> offer=holder.getListOfferInfo();
		for(int i=0;i<offer.size();i++)
		{
			if(offer.get(i).getDestinationName().equals(enteredOfferName))
			{
				this.getServerInfo().getOutputStream().println("found");
				updateNumber=i;
				break;
			}
		}
		if(updateNumber != -1)
		{
			String lenghtOfDestination=this.getServerInfo().getScannerInput().nextLine();
			String price=this.getServerInfo().getScannerInput().nextLine();
			String hotels=this.getServerInfo().getScannerInput().nextLine();
			String travelingWithVehicles=this.getServerInfo().getScannerInput().nextLine();
			String daysOfBeginingAndEnd=this.getServerInfo().getScannerInput().nextLine();
			String allbonusInfo=this.getServerInfo().getScannerInput().nextLine();
			offer.get(updateNumber).setLenghtOfDestination(lenghtOfDestination);
			offer.get(updateNumber).setPrice(price);
			offer.get(updateNumber).setHotels(hotels);
			offer.get(updateNumber).setTravelingWithVehicle(travelingWithVehicles);
			offer.get(updateNumber).setAllBonusInfo(allbonusInfo);
			offer.get(updateNumber).setDaysOfBeginingAndEnd(daysOfBeginingAndEnd);
			holder.setListOfferInfo(offer);
			offerinfo.writeFile(holder);
		}
		else{
			return ;
		}
		this.getServerInfo().getOutputStream().println("Successfully updated destination .");
	}
	public void updateEmployee(){
		String name=this.getServerInfo().getScannerInput().nextLine();
		String number=this.getServerInfo().getScannerInput().nextLine();
		ReadWriteEmployeeInfo employee=new ReadWriteEmployeeInfo();
		ListHolder holder=employee.readFile();
		ArrayList<Person> person=holder.getListPerson();
		for(int i=0;i<person.size();i++)
		{
			if(person.get(i).getName().equals(name) && person.get(i).getNumber().equals(number))
			{
				this.getServerInfo().getOutputStream().println("found");
				String name1=this.getServerInfo().getScannerInput().nextLine();
				if(name1.equals("Return"))
				{
					return;
				}
				String adress=this.getServerInfo().getScannerInput().nextLine();
				String number1=this.getServerInfo().getScannerInput().nextLine();
				String email=this.getServerInfo().getScannerInput().nextLine();
				String account=this.getServerInfo().getScannerInput().nextLine();
				String password=this.getServerInfo().getScannerInput().nextLine();
				person.get(i).setName(name1);
				person.get(i).setAdress(adress);
				person.get(i).setNumber(number1);
				person.get(i).setEmail(email);
				person.get(i).createAccHash(account);
				person.get(i).createPassHash(password);
				this.getServerInfo().getOutputStream().println("Successfully updated employee information ! ");
				return ;
			}
		}
		this.getServerInfo().getOutputStream().println("Not found");
	}
	public void deleteOffer(){
		String destinationName=this.getServerInfo().getScannerInput().nextLine();
		ReadWriteOfferInfo offer=new ReadWriteOfferInfo();
		ListHolder holder=offer.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getDestinationName().equals(destinationName))
			{
				list.remove(i);
				this.getServerInfo().getOutputStream().println("found");
				this.getServerInfo().getOutputStream().println("Destination successfully deleted !");
				return;
			}
		}
		this.getServerInfo().getOutputStream().println("Not found");
	}
	public void viewOffers(){
		ReadWriteOfferInfo allOffers=new ReadWriteOfferInfo();
		ListHolder holder=allOffers.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		for(int i=0;i<list.size();i++)
		{
			this.getServerInfo().getOutputStream().println(list.get(i).getDestinationName());
			this.getServerInfo().getOutputStream().println(list.get(i).getLenghtOfDestination());
			this.getServerInfo().getOutputStream().println(list.get(i).getPrice());
			this.getServerInfo().getOutputStream().println(list.get(i).getHotels());
			this.getServerInfo().getOutputStream().println(list.get(i).getTravelingWithVehicle());
			this.getServerInfo().getOutputStream().println(list.get(i).getDaysOfBeginingAndEnd());
			this.getServerInfo().getOutputStream().println(list.get(i).getAllBonusInfo());
			this.getServerInfo().getOutputStream().println(list.get(i).getCreatorOfOfferName());
			this.getServerInfo().getOutputStream().println(list.get(i).getCreatorOfOfferNumber());
			this.getServerInfo().getOutputStream().println(list.get(i).getCreatorOfOfferEmail());
			this.getServerInfo().getOutputStream().println(list.get(i).getCustomersNames());
			this.getServerInfo().getOutputStream().println(list.get(i).getCustomersNumbers());
			this.getServerInfo().getOutputStream().println(list.get(i).getCustomersEmails());
		}
		this.getServerInfo().getOutputStream().println("------------------");
	}
	public void adminOptions(Person person,ServerInfo info){
		this.setServerInfo(info);
		this.getServerInfo().getOutputStream().println("Admin");
		this.getServerInfo().getOutputStream().println(person.getName());
		this.getServerInfo().getOutputStream().println(person.getAdress());
		this.getServerInfo().getOutputStream().println(person.getNumber());
		this.getServerInfo().getOutputStream().println(person.getEmail());
		this.setPerson(person);
		String userInput=this.getServerInfo().getScannerInput().nextLine();
		while(!userInput.equals("0")){
			switch(userInput){
			case "CreateEmployee": this.createEmployee(); break;
			case "DeleteEmployee": this.deleteEmployee(); break;
			case "DeleteCustomer": this.deleteCustomer(); break;
			case "CreateOffer": this.createOffer(); break;
			case "ViewEmployees": this.viewEmployee(); break;
			case "UpdateOffer": this.updateOffer(); break;
			case "UpdateEmployee": this.updateEmployee(); break;
			case "DeleteOffer": this.deleteOffer(); break;
			case "ViewOffers": this.viewOffers(); break;
			}
			userInput=this.getServerInfo().getScannerInput().nextLine();
		}
	}
}
