package clientSide;

import java.util.Scanner;

public class RegisteredCustomer{

	public RegisteredCustomer(String name, String adress, String number,String eMail) {
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
	public Operations operation;
	public void CustomerMenu()
	{
		this.operation=new Operations();
		Scanner input=new Scanner(System.in);
		String choice="";
		System.out.println("---------------");
		System.out.println("Here is your menu with options : ");
		System.out.println("(Press 1) Update personal info. ");
		System.out.println("(Press 2) Show list with all the offers. ");
		System.out.println("(Press 3) Enter information about what offer you are interested in.");
		System.out.println("(Press 0) Exit program.");
		System.out.println("---------------");
		while(!(choice=input.nextLine()).equals("0"))
		{
			switch(choice){
			case"1":ClientSideInfo.printOut.println("updatePersonalInfo");this.operation.updateEmployeeOrCustomerPersonalInfo();  break; // funkciq ot servera trqa ima
			case"2":ClientSideInfo.printOut.println("showO");this.operation.viewOffersOrViewEmployees(); break;
			case"3":ClientSideInfo.printOut.println("enterInfoForOffer");this.operation.enterInfoForOffer(); break;
			case"0":System.exit(3);
				default: System.out.println("Please enter valid option"); break;
			}
		}
		input.close();
	}
	
}
