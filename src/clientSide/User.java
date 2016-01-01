package clientSide;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
public class User extends ClientSideInfo {
	public static void Login(){
		Scanner input=new Scanner(System.in);
		String str,name,adress,number,eMail;
		int count=0;
		String checker="false";
		while(!checker.equals("true"))
		{
			if(count>=1)
			{
				System.out.println(checker);
				System.out.println("You still have "+(3-count)+" attempts left !");
			}
			if(count==3)
			{
				System.out.println("We are sorry but you have entered youre Username or Password incorrect too many times !");
				System.exit(0);
			}
			System.out.println("Please enter your Username: ");
			str=input.nextLine();
			ClientSideInfo.printOut.println(str);
			System.out.println("Please enter your Password: ");
			str=input.nextLine();
			ClientSideInfo.printOut.println(str);
			checker=ClientSideInfo.scan1.nextLine();
			count++;
		}
		System.out.println(ClientSideInfo.scan1.nextLine());
		str=scan1.nextLine(); // tova opredelq dali e ADMIN EMPLOYEE ili CUSTOMER i fakti4eski servera vrushta info kakuv dostup mu razreshava da ima !!!
		name=scan1.nextLine();
		adress=scan1.nextLine();
		number=scan1.nextLine();
		eMail=scan1.nextLine();
		switch(str){
		case"Admin": Administrator admin=new Administrator(name,adress,number,eMail); admin.AdminMenu(); break;
		case"Employee": Employee employee=new Employee(name,adress,number,eMail); employee.EmployeeMenu(); break;
		case"Customer": RegisteredCustomer cust=new RegisteredCustomer(name,adress,number,eMail); cust.CustomerMenu(); break;
		}
		input.close();
	}
	public static void CreateRegistration()
	{
		Boolean checker=false;
		Scanner input=new Scanner(System.in);
		System.out.println("Please enter your personal data .");
		BasicInfo temp1=new BasicInfo();
		while(checker != true)
		{
			String s;
			Boolean check1=false,check2=false,check3=false,checker4=false,checker5=false;
			System.out.println("Please enter your full name: ");
			s=input.nextLine();
			check1=temp1.checkName(s);
			System.out.println("Please enter your adress: ");
			s=input.nextLine();
			temp1.setAdress(s);
			System.out.println("Please enter your mobile number: ");
			s=input.nextLine();
			check2=temp1.checkNumber(s);
			System.out.println("Please enter your E-mail: ");
			s=input.nextLine();
			check3=temp1.checkEmail(s);
			System.out.println("Please enter your Account name: ");
			s=input.nextLine();
			checker4=temp1.checkAccName(s);
			System.out.println("Please enter your Password: ");
			s=input.nextLine();
			checker5=temp1.checkPass(s);
			if(check1==true && check2==true && check3==true && checker4==true && checker5==true)
			{
				checker=true;
			}
			else{
				check1=check2=check3=checker4=checker5=false;
				System.out.println("You have entered Invalid information!");
				System.out.println("Please enter Valid information !");
			}
		}
		ClientSideInfo.printOut.println(temp1.getName());
		ClientSideInfo.printOut.println(temp1.getAdress());
		ClientSideInfo.printOut.println(temp1.getNumber());
		ClientSideInfo.printOut.println(temp1.getEmail());
		ClientSideInfo.printOut.println(temp1.getAccName());
		ClientSideInfo.printOut.println(temp1.getPass());
		System.out.println(ClientSideInfo.scan1.nextLine());
		System.out.println(ClientSideInfo.scan1.nextLine());
		temp1.setName(ClientSideInfo.scan1.nextLine());
		temp1.setAdress(ClientSideInfo.scan1.nextLine());
		temp1.setNumber(ClientSideInfo.scan1.nextLine());
		temp1.setEmail(ClientSideInfo.scan1.nextLine());
		RegisteredCustomer newCust=new RegisteredCustomer(temp1.getName(),temp1.getAdress(),temp1.getNumber(),temp1.getEmail());
		newCust.CustomerMenu();
		input.close();
	}
	public static void ShowOffers()
	{
		String temp=ClientSideInfo.scan1.nextLine();
		while(!temp.equals("Return")) 
		{
			System.out.println(temp);
			temp=ClientSideInfo.scan1.nextLine();
		}
		System.out.println(ClientSideInfo.scan1.nextLine());
	}
	public static void main(String[] args) {
		Scanner inputCons;
		String enteredOption="";
		try{
			ClientSideInfo.sock=new Socket("localhost",1913);
			ClientSideInfo.scan1=new Scanner(sock.getInputStream());
			ClientSideInfo.printOut=new PrintStream(sock.getOutputStream());
			inputCons=new Scanner(System.in);
			System.out.println("---------------------");
			System.out.println("Here are all available options:");
			System.out.println("(Press 1) Login");
			System.out.println("(Press 2) Register for offer");
			System.out.println("(Press 3) Show list with offers");
			System.out.println("(Press 0) Exit program");
			System.out.println("---------------------");
			System.out.println("Please select an option : ");
			while(!enteredOption.equals("0"))
			{
				enteredOption=inputCons.nextLine();
				switch(enteredOption){
				case"1":
					printOut.println("1"); Login();  break;
				case"2":
					printOut.println("2"); CreateRegistration(); break;
				case"3":
					printOut.println("3"); ShowOffers(); break;
				case"0": printOut.print("0"); System.exit(4);
					
				default: System.out.println("Please enter valid option !"); break;
				}
				System.out.println("Please select an option : ");
			}
		}catch(IOException e){
			System.out.print(e.getMessage());
		}
		
	}
}
