package clientSide;

import java.util.Scanner;

public class Administrator {
	public Administrator(String name, String adress, String number,String eMail) {
		this.name=name;
		this.adress=adress;
		this.number=number;
		this.eMail=eMail;
	}
	private String name;
	private String adress;
	private String number;
	private String eMail;
	public String getName()
	{
		return this.name;
	}
	public String getAdress()
	{
		return this.adress;
	}
	public String getNumber()
	{
		return this.number;
	}
	public String getEmail()
	{
		return this.eMail;
	}
	public Operations operations;
	public void AdminMenu()
	{
		this.operations=new Operations();
		Scanner input=new Scanner(System.in);
		String choice="";
		System.out.println("---------------");
		System.out.println("Here is your menu with options : ");
		System.out.println("(Press 1) Creating new employee.");
		System.out.println("(Press 2) Deleteing employee.");
		System.out.println("(Press 3) Delete customer account");
		System.out.println("(Press 4) Create offer for new destination.");
		System.out.println("(Press 5) Show list with all the employees. ");
		System.out.println("(Press 6) Update information for an offer. ");
		System.out.println("(Press 7) Update employees personal info. ");
		System.out.println("(Press 8) Delete an offer ");
		System.out.println("(Press 9) Show list with all the offers. ");
		System.out.println("(Press 10) Show list with all the customers.");
		System.out.println("(Press 0) Exit program.");
		System.out.println("---------------");
		System.out.println("Please enter option : ");
		while(!choice.equals("0"))
		{
			choice=input.nextLine();
			switch(choice){
			case"1":
				ClientSideInfo.printOut.println("CreateEmployee"); this.operations.createAndUpdateEmployeeOrCustomer();  break; 
			case"2":
				ClientSideInfo.printOut.println("DeleteEmployee"); this.operations.deleteEmployeeOrCustomersAccount(); break;
			case"3":
				ClientSideInfo.printOut.println("DeleteCustomer"); this.operations.deleteEmployeeOrCustomersAccount(); break;
			case"4":
				ClientSideInfo.printOut.println("CreateOffer"); this.operations.createOffer(); break;
			case"5":
				ClientSideInfo.printOut.println("ViewEmployees"); this.operations.viewOffersOrViewEmployees(); break;
			case"6":
				ClientSideInfo.printOut.println("UpdateOffer"); this.operations.updateOffer(getName(), getNumber(), getEmail());; break;
			case"7":
				ClientSideInfo.printOut.println("UpdateEmployee"); this.operations.updateEmployee(); break;
			case"8":
				ClientSideInfo.printOut.println("DeleteOffer"); this.operations.deleteOffer(); break;
			case"9":
				ClientSideInfo.printOut.println("ViewOffers");this.operations.viewOffersOrViewEmployees(); break;
			case "10": 
				ClientSideInfo.printOut.println("ViewAllCustomers"); this.operations.viewOffersOrViewEmployees(); break;
			case"0":ClientSideInfo.printOut.println("0"); System.exit(1);
				default: System.out.println("Please enter valid option !"); break;
			}
			System.out.println("(Press 1) Creating new employee.");
			System.out.println("(Press 2) Deleteing employee.");
			System.out.println("(Press 3) Delete customer account");
			System.out.println("(Press 4) Create offer for new destination.");
			System.out.println("(Press 5) Show list with all the employees. ");
			System.out.println("(Press 6) Update information for an offer. ");
			System.out.println("(Press 7) Update employees personal info. ");
			System.out.println("(Press 8) Delete an offer ");
			System.out.println("(Press 9) Show list with all the offers. ");
			System.out.println("(Press 10) Show list with all the customers.");
			System.out.println("(Press 0) Exit program.");
			System.out.println("---------------");
			System.out.println("Please enter option : ");
		}
		input.close();
	}
}
