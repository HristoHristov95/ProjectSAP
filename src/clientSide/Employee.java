package clientSide;

import java.util.Scanner;

public class Employee {
	public Employee(String name, String adress, String number,String eMail) {
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
	public void EmployeeMenu()
	{
		this.operations=new Operations();
		Scanner input=new Scanner(System.in);
		String choice="";
		System.out.println("---------------");
		System.out.println("Here is your menu with options : ");
		System.out.println("(Press 1) Create offer for new destination.");
		System.out.println("(Press 2) Show list with all the employees. ");
		System.out.println("(Press 3) Update information for personal offer. ");
		System.out.println("(Press 4) Update personal info. ");
		System.out.println("(Press 5) Delete personal offer ");
		System.out.println("(Press 6) Show list with all the offers. ");
		System.out.println("(Press 7) Show list with all the customers.");
		System.out.println("(Press 0) Exit program.");
		System.out.println("---------------");
		System.out.println("Please select an option : ");
		while(!choice.equals("0"))
		{
			choice=input.nextLine();
			switch(choice){
			case"1":ClientSideInfo.printOut.println("createOffer"); this.operations.createOffer(); break; // funciq koqto ot servera prashta vuprosi za kakvo da suzdava
			case"2":ClientSideInfo.printOut.println("showEmployees");this.operations.viewOffersOrViewEmployees(); break;
			case"3":ClientSideInfo.printOut.println("updatePersonalOffer"); this.operations.updateOffer(getName(),getNumber(),getEmail()); break;
			case"4":ClientSideInfo.printOut.println("updatePersonalInfo");this.operations.updateEmployeeOrCustomerPersonalInfo(); break;
			case"5":ClientSideInfo.printOut.println("deletePersonalOffer");this.operations.deleteOffer(); break;
			case"6":ClientSideInfo.printOut.println("showOffers");this.operations.viewOffersOrViewEmployees(); break;
			case"7":ClientSideInfo.printOut.println("ViewAllCustomers"); this.operations.viewOffersOrViewEmployees(); break;
			case"0":ClientSideInfo.printOut.println("0"); System.exit(2);
				default: System.out.println("Please enter valid option"); break;
			}
			System.out.println("(Press 1) Create offer for new destination.");
			System.out.println("(Press 2) Show list with all the employees. ");
			System.out.println("(Press 3) Update information for personal offer. ");
			System.out.println("(Press 4) Update personal info. ");
			System.out.println("(Press 5) Delete personal offer ");
			System.out.println("(Press 6) Show list with all the offers. ");
			System.out.println("(Press 7) Show list with all the customers.");
			System.out.println("(Press 0) Exit program.");
			System.out.println("---------------");
			System.out.println("Please select an option : ");
		}
		input.close();
	}
}
