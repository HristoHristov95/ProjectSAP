package serverSide;

import java.util.ArrayList;

public class Validator {
	public int searchForInfo(ArrayList<Person> list,String userName,String password){
		int thePersonsNumberInTheList=-1;
		for(int i=0;i<list.size();i++)
		{
			boolean accCheck=false;
			boolean passCheck=false;
			accCheck=list.get(i).checkAcc(userName);
			passCheck=list.get(i).checkPassword(password);
			if(accCheck==true && passCheck==true)
			{
				thePersonsNumberInTheList=i;
				break;
			}
		}
		return thePersonsNumberInTheList;
		
	}
	public void checkEnteredInfo(ServerInfo newServer){
		boolean validate=false;
		int personsNumberInTheList=-1;
		int switcher=0;
		Person person=null;
		ListHolder holderAdmin;
		ListHolder holderCustomer;
		ListHolder holderEmployee;
		ReadWriteAdminInfo admin=new ReadWriteAdminInfo();
		ReadWriteCustomerInfo customer=new ReadWriteCustomerInfo();
		ReadWriteEmployeeInfo employee=new ReadWriteEmployeeInfo();
		holderAdmin=admin.readFile();
		holderCustomer=customer.readFile();
		holderEmployee=employee.readFile();
		ArrayList<Person> listWithAdmins=holderAdmin.getListPerson();
		ArrayList<Person> listWithCustomers=holderCustomer.getListPerson();
		ArrayList<Person> listWithEmployees=holderEmployee.getListPerson();
		while(!validate)
		{
			String userName= newServer.getScannerInput().nextLine();
			String password=newServer.getScannerInput().nextLine();
			switch(switcher){
			case 0: personsNumberInTheList=searchForInfo(listWithAdmins,userName,password); 
				if(personsNumberInTheList != -1)
				{
					person=listWithAdmins.get(personsNumberInTheList);
					validate=true;
				}
			case 1: personsNumberInTheList=searchForInfo(listWithCustomers,userName,password); 
			if(personsNumberInTheList != -1)
			{
				person=listWithCustomers.get(personsNumberInTheList);
				validate=true;
			}
			case 2: personsNumberInTheList=searchForInfo(listWithEmployees,userName,password);
			if(personsNumberInTheList != -1)
			{
				person=listWithEmployees.get(personsNumberInTheList);
				validate=true;
			}
			break;
			case 3: System.exit(0); break;
			}
			switcher++;
		}
		newServer.getOutputStream().println("You have been successfully logged in!");
		switch(person.getAttributeID()){
		case "1": AdminMainOptions adminOptions=new AdminMainOptions(); adminOptions.adminOptions(person,newServer); break;
		case "2": EmployeeMainOptions employeeOptions=new EmployeeMainOptions(); employeeOptions.employeeOptions(person,newServer); break;
		case "3": CustomerMainOptions customersOptions=new CustomerMainOptions(); customersOptions.customersOptions(person,newServer); break;
		}
	}
}
