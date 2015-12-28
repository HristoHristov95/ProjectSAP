package clientSide;
import java.util.*;
public class Operations extends BasicInfo {
	public void createAndUpdateEmployeeOrCustomer(){
		boolean checker=false;
		String check="";
		BasicInfo temporary=new BasicInfo();
		Scanner input=new Scanner(System.in);
		while(checker != true)
		{
			String s;
			Boolean check1=false,check2=false,check3=false,checker4=false,checker5=false;
			System.out.println("Please enter the employee's full name: ");
			s=input.nextLine();
			check1=temporary.checkName(s);
			System.out.println("Please enter the employee's adress: ");
			s=input.nextLine();
			setAdress(s);
			System.out.println("Please enter the employee's mobile number: ");
			s=input.nextLine();
			check2=temporary.checkNumber(s);
			System.out.println("Please enter the employee's E-mail: ");
			s=input.nextLine();
			check3=temporary.checkEmail(s);
			System.out.println("Please enter the employee's Account name(It must be at least 3 symbols): ");
			s=input.nextLine();
			checker4=temporary.checkAccName(s);
			System.out.println("Please enter the employee's Password(It must be at least 5 symbols): ");
			s=input.nextLine();
			checker5=temporary.checkPass(s);
			if(check1==true && check2==true && check3==true && checker4==true && checker5==true)
			{
				checker=true;
			}
			else{
				check1=check2=check3=checker4=checker5=false;
				System.out.println("You have entered Invalid information!");
				System.out.println("Do you want to enter it again ?(Y/N)");
				check=input.nextLine();
				switch(check){
				case"Y": 
				case"y":checker=false; break;
				case"N":
				case"n":checker=true; ClientSideInfo.printOut.println("Return"); return;
				default: System.out.println("What you have entered is incorrect and is not an option! Please enter the information again !");break;
				}
			}
		}
			ClientSideInfo.printOut.println(temporary.getName());
			ClientSideInfo.printOut.println(temporary.getAdress());
			ClientSideInfo.printOut.println(temporary.getNumber());
			ClientSideInfo.printOut.println(temporary.getEmail());
			ClientSideInfo.printOut.println(temporary.getAccName());
			ClientSideInfo.printOut.println(temporary.getPass());
		System.out.println(ClientSideInfo.scan1.nextLine());
		input.close();
	}
	public void deleteEmployeeOrCustomersAccount(){
		boolean checker=false;
		String check="";
		String s;
		Scanner input=new Scanner(System.in);
		BasicInfo temp=new BasicInfo();
		Boolean check1=false,check2=false;
		while(checker != true)
		{
			System.out.println("Please enter the employee/customer full name: ");
			s=input.nextLine();
			check1=temp.checkName(s);
			System.out.println("Please enter the employee/customer mobile number: ");
			s=input.nextLine();
			check2=temp.checkNumber(s);
			if(check1==true && check2==true)
			{
				checker=true;
			}
			else{
				System.out.println("The information you have entered is invalid do you want to enter it again ?(Y/N)");
				check=input.nextLine();
				switch(check){
				case"Y": 
				case"y":checker=false; break;
				case"N":
				case"n":checker=true; ClientSideInfo.printOut.println("Return"); return;
				default: System.out.println("What you have entered is incorrect and is not an option! Please enter the information again !");break;
				}
			}
		}
		ClientSideInfo.printOut.println(temp.getName());
		ClientSideInfo.printOut.println(temp.getNumber());
		System.out.println(ClientSideInfo.scan1.nextLine());
		input.close();
	}
	public void createOffer()
	{
		boolean acceptedInfo=false;
		String check="";
		ArrayList<String> list=new ArrayList<String>();
		Scanner input=new Scanner(System.in);
		String destination="",lenghtOfDestination="",hotels="",travelingWithVehicles="",daysOfBeginingAndEnd="",price1="";
		double price=0;
		while(acceptedInfo != true)
		{
			System.out.println("Please enter Destination: ");
			destination=input.nextLine();
			System.out.println("Please enter information about the lenght of the excursion: ");
			lenghtOfDestination=input.nextLine();
			System.out.println("Please enter the price of the excursion: ");
			try{
				price=Double.parseDouble(input.nextLine());
				price1=String.valueOf(price);
			}catch(InputMismatchException e){
				System.out.println("You have entered invalid informatio !");
			}
			System.out.println("Please enter names of hotels and the stars that they are (Example: \"Dubai Hotel***\")");
			hotels=input.nextLine();
			System.out.println("Please enter all the vehicles that will be used by the travel agency during this excursion: ");
			travelingWithVehicles=input.nextLine();
			System.out.println("Please enter date of begining and ending of excursion: ");
			daysOfBeginingAndEnd=input.nextLine();
			System.out.println("Please enter bonus information that is not shown here");
			System.out.println("that you think is needed to inform the customer.");
			System.out.println("If you think this is not necessary simply press \"Enter\" to skip this part.(When you want to stop writing press 2 times \"Enter\")");
			String tempForBonusInformation=input.nextLine();
			if(!tempForBonusInformation.equals(""))
			{
				list.add(tempForBonusInformation);
				while(!tempForBonusInformation.equals(""))
				{
					tempForBonusInformation=input.nextLine();
					list.add(tempForBonusInformation);
				}
			}
			System.out.println("Are you sure all the information you have entered is correct ?(Y/N)");
			check=input.nextLine();
			switch(check){
			case"Y": 
			case"y":acceptedInfo=true; break;
			case"N":
			case"n":acceptedInfo=false; System.out.println("Okey then enter the information again !"); break;
			default: System.out.println("What you have entered is incorrect and is not an option! Please enter the information again !");break;
			}
		}
		ClientSideInfo.printOut.println(destination);
		ClientSideInfo.printOut.println(lenghtOfDestination);
		ClientSideInfo.printOut.println(price1);
		ClientSideInfo.printOut.println(hotels);
		ClientSideInfo.printOut.println(travelingWithVehicles);
		ClientSideInfo.printOut.println(daysOfBeginingAndEnd);
		String allbonusinfo="";
		for(int i=0;i<list.size();i++)
		{
			allbonusinfo=allbonusinfo+list.get(i);
		}
		ClientSideInfo.printOut.println(allbonusinfo);
		System.out.println(ClientSideInfo.scan1.next());
		input.close();
	}
	public void updateOffer(String name,String number,String email)
	{
		ArrayList<String> list=new ArrayList<String>();
		String lenghtOfDestination="",hotels="",travelingWithVehicles="",daysOfBeginingAndEnd="",price1="",allbonusInfo="";
		double price=0;
		Scanner input=new Scanner(System.in);
		System.out.println("Please enter destination name that you want to update: ");
		String destinationName=input.nextLine();
		ClientSideInfo.printOut.println(destinationName);
		String check=ClientSideInfo.scan1.nextLine();
		if(check.equals("found"))
		 {
						System.out.println("Please enter information about the lenght of the excursion: ");
						lenghtOfDestination=input.nextLine();
						System.out.println("Please enter the price of the excursion: ");
						try{
							price=Double.parseDouble(input.nextLine());
							price1=String.valueOf(price);
						}catch(InputMismatchException e){
							System.out.println("You have entered invalid informatio !");
						}
						System.out.println("Please enter names of hotels and the stars that they are (Example: \"Dubai Hotel***\")");
						hotels=input.nextLine();
						System.out.println("Please enter all the vehicles that will be used by the travel agency during this excursion: ");
						travelingWithVehicles=input.nextLine();
						System.out.println("Please enter date of begining and ending of excursion: ");
						daysOfBeginingAndEnd=input.nextLine();
						System.out.println("Please enter bonus information that is not shown here");
						System.out.println("that you think is needed to inform the customer.");
						System.out.println("If you think this is not necessary simply press \"Enter\" to skip this part.(When you want to stop writing press 2 times \"Enter\")");
						String tempForBonusInformation=input.nextLine();
						if(!tempForBonusInformation.equals(""))
						{
							list.add(tempForBonusInformation);
							while(!tempForBonusInformation.equals(""))
							{
								tempForBonusInformation=input.nextLine();
								list.add(tempForBonusInformation);
							}
						}
					}
		else{
			System.out.println("The element was not found or you dont have the authority to make changes. Returning to menu with options ");
			input.close();
			return;
			}
		ClientSideInfo.printOut.println(lenghtOfDestination);
		ClientSideInfo.printOut.println(price1);
		ClientSideInfo.printOut.println(hotels);
		ClientSideInfo.printOut.println(travelingWithVehicles);
		ClientSideInfo.printOut.println(daysOfBeginingAndEnd);
		for(int i=0;i<list.size();i++)
		{
			allbonusInfo=allbonusInfo+" "+list.get(i);
		}
		ClientSideInfo.printOut.println(allbonusInfo);
		System.out.println(ClientSideInfo.scan1.next());
		input.close();
}
	public void viewOffersOrViewEmployees()
	{
		System.out.println("Here is the list with the information : ");
		String temp;
		while(!(temp=ClientSideInfo.scan1.nextLine()).equals("Return"))
		{
			System.out.println(temp);
		}
	}
	public void updateEmployee()
	{
		String s;
		Scanner input=new Scanner(System.in);
		BasicInfo temp=new BasicInfo();
		Boolean check1=false,check2=false;
		System.out.println("Please enter the employee full name: ");
		s=input.nextLine();
		check1=temp.checkName(s);
		System.out.println("Please enter the employee mobile number: ");
		s=input.nextLine();
		check2=temp.checkNumber(s);
		if(check1==true && check2==true)
		{
			ClientSideInfo.printOut.println(temp.getName());
			ClientSideInfo.printOut.println(temp.getNumber());
			String temp1=ClientSideInfo.scan1.nextLine();
			if(temp1.equals("found"))
			{
				createAndUpdateEmployeeOrCustomer();
			}
			else{
				System.out.println("Employee not found !");
			}
			input.close();
		}
		else{
			System.out.println("The information you have entered is invalid returning to main menu options ...");
			input.close();
			return ;
		}
	}
	public void updateEmployeeOrCustomerPersonalInfo()
	{
		BasicInfo person=new BasicInfo();
		boolean checker=false;
		Scanner input=new Scanner(System.in);
		System.out.println("Here is the list with the options for updates : ");
		System.out.println("(Press 1) Update name.");
		System.out.println("(Press 2) Update number. ");
		System.out.println("(Press 3) Update email. ");
		System.out.println("(Press 4) Update adress.");
		System.out.println("(Press 5) Update Account Name. ");
		System.out.println("(Press 6) Update Password.");
		System.out.println("Please enter your choice : ");
		String choice=input.nextLine();
		switch(choice){
		case "1": 
			System.out.println("Please enter your full name : ");
			String name=input.nextLine();
			checker=person.checkName(name);
			if(checker)
			{
				ClientSideInfo.printOut.println("1");
				ClientSideInfo.printOut.println(name);
			}
			else{
				System.out.println("Invalid info. Returning to main menu... ");
				ClientSideInfo.printOut.println("Invalid");
				input.close();
				return;
			}
			input.close();
			break;
		case "2":
			System.out.println("Please enter your number : ");
			String number=input.nextLine();
			checker=person.checkNumber(number);
			if(checker)
			{
				ClientSideInfo.printOut.println("2");
				ClientSideInfo.printOut.println(number);
			}
			else{
				System.out.println("Invalid info. Returning to main menu... ");
				ClientSideInfo.printOut.println("Invalid");
				input.close();
				return;
			}
			input.close();
			break;
		case "3":
			System.out.println("Please enter your E-mail : ");
			String Email=input.nextLine();
			checker=person.checkEmail(Email);
			if(checker)
			{
				ClientSideInfo.printOut.println("3");
				ClientSideInfo.printOut.println(Email);
			}
			else{
				System.out.println("Invalid info. Returning to main menu... ");
				ClientSideInfo.printOut.println("Invalid");
				input.close();
				return;
			}
			input.close();
			break;
		case "4":
			System.out.println("Please enter your adress : ");
			String adress=input.nextLine();
			person.setAdress(adress);
			ClientSideInfo.printOut.println("4");
			ClientSideInfo.printOut.println(adress);
			input.close();
			break;
		case "5":
			System.out.println("Please enter your Account Name : ");
			String AccName=input.nextLine();
			checker=person.checkAccName(AccName);
			if(checker)
			{
				ClientSideInfo.printOut.println("5");
				ClientSideInfo.printOut.println(AccName);
			}
			else{
				System.out.println("Invalid info. Returning to main menu... ");
				ClientSideInfo.printOut.println("Invalid");
				input.close();
				return;
			}
			input.close();
			break;
		case "6":
			System.out.println("Please enter your Password : ");
			String password=input.nextLine();
			checker=person.checkPass(password);
			if(checker)
			{
				ClientSideInfo.printOut.println("6");
				ClientSideInfo.printOut.println(password);
			}
			else{
				System.out.println("Invalid info. Returning to main menu... ");
				ClientSideInfo.printOut.println("Invalid");
				input.close();
				return;
			}
			input.close();
			break;
		default: System.out.println("Invalid option. Returning to main menu...");ClientSideInfo.printOut.println("Invalid"); input.close(); return;
		}
	}
	public void deleteOffer()
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Please enter destination name that you want to delete: ");
		String destinationName=input.nextLine();
		ClientSideInfo.printOut.println(destinationName);
		String check=ClientSideInfo.scan1.nextLine();
		if(check.equals("found"))
		{
			System.out.println(ClientSideInfo.scan1.nextLine());
			input.close();
			return;
		}
		else{
			System.out.println("Destination you wanted to delete was not found. Returing to menu ...");
			}
		input.close();
	}
	public void enterInfoForOffer()
	{
		String name;
		Scanner input=new Scanner(System.in);
		System.out.println("Please enter the name of the destination: ");
		name=input.nextLine();
		ClientSideInfo.printOut.println(name);
		System.out.println(ClientSideInfo.scan1.nextLine());
		input.close();
	}
}
