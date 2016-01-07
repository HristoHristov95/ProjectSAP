package serverSide;


public class Person {
	public static final String attributeIDAdmin="1";
	public static final String attributeIDEmployee="2";
	public static final String attributeIDCustomer="3";
	private String currentAttributeID;
	private String name;
	private String adress;
	private String number;
	private String eMail;
	private String accName;
	private String password;
	private String MasterKey;
/*	public static final int numberForAccName=350;
	public static final int numberForPassword=127;*/
	public Person(String name,String adress,String number,String eMail,String accName,String password,String masterKey){
		this.name=name;
		this.adress=adress;
		this.number=number;
		this.eMail=eMail;
		this.accName=accName;
		this.password=password;
		this.MasterKey=masterKey;
	}
	public Person()
	{
		this.name="Unknown";
		this.adress="Unknown";
		this.number="Unknown";
		this.eMail="Unknown";
		this.accName="Unknown";
		this.password="Unknown";
		this.MasterKey="Unknown";
	}
	public void setMasterKey(String key)
	{
		this.MasterKey=key;
	}
	public String getMasterKey()
	{
		return this.MasterKey;
	}
	public void setAttributeID(String s){
		this.currentAttributeID=s;
	}
	public String getAttributeID(){
		return this.currentAttributeID;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public void setAdress(String adress)
	{
		this.adress=adress;
	}
	public String getAdress()
	{
		return this.adress;
	}
	public void setNumber(String s)
	{
		this.number=s;
	}
	public String getNumber()
	{
		return this.number;
	}
	public void setEmail(String email)
	{
		this.eMail=email;
	}
	public String getEmail()
	{
		return this.eMail;
	}
	public void createAccHash(String s)
	{
		Integer result=this.MasterKey.hashCode()+s.hashCode(); 
		result=result+this.name.hashCode();
		result=result+this.adress.hashCode();
		result=result+this.number.hashCode();
		result=result+this.eMail.hashCode();
		this.accName=Integer.toString(result);
	}
	public void setAccName(String s){
		this.accName=s;
	}
	public String getAccName()
	{
		return this.accName;
	}
	public void createPassHash(String s)
	{
		Integer result=this.MasterKey.hashCode()+s.hashCode();
		result=result+this.name.hashCode();
		result=result+this.adress.hashCode();
		result=result+this.number.hashCode();
		result=result+this.eMail.hashCode();
		this.password=Integer.toString(result);
	}
	public void setPassword(String s){
		this.password=s;
	}
	public String getPassword()
	{
		return this.password;
	}
	public boolean checkAcc(String accName){
		Integer result=this.MasterKey.hashCode()+accName.hashCode(); 
		result=result+this.name.hashCode();
		result=result+this.adress.hashCode();
		result=result+this.number.hashCode();
		result=result+this.eMail.hashCode();
		String temp=Integer.toString(result);
		if(temp.equals(this.getAccName()))
		{
			return true;
		}
		else{
			return false;
		}
	}
	public boolean checkPassword(String password){
		Integer result=this.MasterKey.hashCode()+password.hashCode(); 
		result=result+this.name.hashCode();
		result=result+this.adress.hashCode();
		result=result+this.number.hashCode();
		result=result+this.eMail.hashCode();
		String temp=Integer.toString(result);
		if(temp.equals(this.getPassword()))
		{
			return true;
		}
		else{
			return false;
		}
	}
}
