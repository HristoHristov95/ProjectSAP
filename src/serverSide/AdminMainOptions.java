package serverSide;
import java.io.IOException;
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
		String name=this.getServerInfo().getScannerInput().nextLine();
		String adress=this.getServerInfo().getScannerInput().nextLine();
		String number=this.getServerInfo().getScannerInput().nextLine();
		String email=this.getServerInfo().getScannerInput().nextLine();
		String accName=this.getServerInfo().getScannerInput().nextLine();
		String password=this.getServerInfo().getScannerInput().nextLine();
		String masterKey=this.getServerInfo().getScannerInput().nextLine();
		person.setMasterKey(masterKey);
		person.setName(name);
		person.setAdress(adress);
		person.setNumber(number);
		person.setEmail(email);
		person.createAccHash(accName);
		person.createPassHash(password);
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
	public void deletingFromOffers(String[] name,String[] numbers,String[] emails,int getListElement)
	{
		ReadWriteOfferInfo info=new ReadWriteOfferInfo();
		ListHolder holder=info.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		String tempName=" ",tempNumbers=" ",tempEmails=" ";
		for(int i=0;i<name.length;i++)
		{
			if(name[i].equals("delete") || name[i].equals(" "))
			{
				continue;
			}
			tempName= tempName+"*"+name[i];
			tempNumbers=tempNumbers+"*"+numbers[i];
			tempEmails=tempEmails+"*"+emails[i];
		}
		list.get(getListElement).setCustomersNames(tempName);
		list.get(getListElement).setCustomersNumbers(tempNumbers);
		list.get(getListElement).setCustomersEmails(tempEmails);
		holder.setListOfferInfo(list);
		info.writeFile(holder);
	}
	public void deleteCustomerFromTheOffersList(ArrayList<Person> listWithPersonToDelete,int elementToDelete)
	{
		ReadWriteOfferInfo info=new ReadWriteOfferInfo();
		ListHolder holder=info.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		int getListElement=-1;
		String[] name=null;
		String[] numbers=null;
		String[] emails=null;
		for(int i=1;i<list.size();i++)
		{
			name=list.get(i).getCustomersNames().split("\\*");
			numbers=list.get(i).getCustomersNumbers().split("\\*");
			emails=list.get(i).getCustomersEmails().split("\\*");
			for(int temp=1;temp<name.length;temp++)
			{
				if(name[temp].equals(listWithPersonToDelete.get(elementToDelete).getName()))
				{
					name[temp]="delete";
					numbers[temp]="delete";
					emails[temp]="delete";
					getListElement=i;
					deletingFromOffers(name,numbers,emails,getListElement);
					break;
				}
			}
		}
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
				deleteCustomerFromTheOffersList(list,i);
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
		for(int i=1;i<employees.size();i++)
		{
			this.getServerInfo().getOutputStream().println("Employee name : "+employees.get(i).getName()+" .Employee mobile number : "+employees.get(i).getNumber()+" .Employee adress : "+employees.get(i).getAdress()+" .Employee E-mail adress : "+employees.get(i).getEmail());
		}
		this.getServerInfo().getOutputStream().println("Return");
		this.getServerInfo().getOutputStream().println("---------------");
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
				String temporary=name1;
				if(temporary.equals("Return"))
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
				holder.setListPerson(person);
				employee.writeFile(holder);
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
				holder.setListOfferInfo(list);
				offer.writeFile(holder);
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
		for(int i=1;i<list.size();i++)
		{
			this.getServerInfo().getOutputStream().println("Destination : "+ list.get(i).getDestinationName());
			this.getServerInfo().getOutputStream().println("Days that this excursion will last : "+list.get(i).getLenghtOfDestination());
			this.getServerInfo().getOutputStream().println("Price : "+list.get(i).getPrice());
			this.getServerInfo().getOutputStream().println("Hotels that will be used : "+list.get(i).getHotels());
			this.getServerInfo().getOutputStream().println("Vehicles that will be used by this agency for this destination : "+list.get(i).getTravelingWithVehicle());
			this.getServerInfo().getOutputStream().println("Date of begining and ending of the excursion : "+list.get(i).getDaysOfBeginingAndEnd());
			this.getServerInfo().getOutputStream().println("Bonus information about this excursion : "+list.get(i).getAllBonusInfo());
			this.getServerInfo().getOutputStream().println("Creator of this offer name : "+list.get(i).getCreatorOfOfferName());
			this.getServerInfo().getOutputStream().println("Creator of this offer mobile number : "+list.get(i).getCreatorOfOfferNumber());
			this.getServerInfo().getOutputStream().println("Creator of this offer E-mail adress : "+list.get(i).getCreatorOfOfferEmail());
			String[] names=list.get(i).getCustomersNames().split("\\*");
			String[] numbers=list.get(i).getCustomersNumbers().split("\\*");
			String[] emails=list.get(i).getCustomersEmails().split("\\*");
			for(int temp=1;temp<names.length;temp++)
			{
				this.getServerInfo().getOutputStream().println("Customers name : "+names[temp]+" .Customers mobile number : "+numbers[temp]+" .Customers E-Mail : "+emails[temp]);
			}
			this.getServerInfo().getOutputStream().println("--------------------------");
		}
		this.getServerInfo().getOutputStream().println("Return");
		this.getServerInfo().getOutputStream().println("---------------");
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
	public void importDataFromXML()
	{
		boolean check=false;
		ImportExportAllTheInformation info=new ImportExportAllTheInformation();
		String fileName=this.getServerInfo().getScannerInput().nextLine();
		check=info.importTheInformation(fileName);
		if(check==true)
		{
			this.getServerInfo().getOutputStream().println("Successfully imported the information from the file into the current XML files !");
		}
		if(check==false)
		{
			this.getServerInfo().getOutputStream().println("Error with importing data");
		}
	}
	public void exportDataFromXML()
	{
		boolean check=false;
		ImportExportAllTheInformation info=new ImportExportAllTheInformation();
		String fileName=this.getServerInfo().getScannerInput().nextLine();
		check=info.exportTheInformation(fileName);
		if(check==true)
		{
			this.getServerInfo().getOutputStream().println("Successfully exported the information from the files into the current XML file !");
		}
		if(check==false)
		{
			this.getServerInfo().getOutputStream().println("Error with exporting data");
		}
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
			case "ViewAllCustomers": this.viewCustomers(); break;
			case "ImportData": this.importDataFromXML(); break;
			case "ExportData": this.exportDataFromXML(); break;
			case "ShutDown": System.exit(11);
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
