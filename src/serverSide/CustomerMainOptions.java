package serverSide;

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
		}
		this.getServerInfo().getOutputStream().println("Return");
	}
	public void enterInfoForOffer()
	{
		ReadWriteOfferInfo offerInfo=new ReadWriteOfferInfo();
		ListHolder holder=offerInfo.readFile();
		ArrayList<OfferInfo> list=holder.getListOfferInfo();
		String enteredInfo=this.getServerInfo().getScannerInput().nextLine();
		for(int i=0;i<list.size();i++)
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
				this.getServerInfo().getOutputStream().println("Successfully applyed for destination !");
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
	}
}
