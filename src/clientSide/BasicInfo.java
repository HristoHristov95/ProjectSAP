package clientSide;
import java.util.regex.*;
public class BasicInfo extends ClientSideInfo {
	private String name;
	private String adress;
	private String number;
	private String eMail;
	private String accName;
	private String password;
	public Pattern pattern;
	public Matcher matcher;
	public static final String PASS_WORD="^[A-Za-z0-9-_]{5,}$";
	public static final String ACC_NAME="^[A-Za-z0-9-_]{3,}$";
	public static final String MOBILENUMBER_PATTERN="^[0-9]{10}{12}$";
	public static final String NAME_PATTERN="^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
	public static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public Boolean checkAccName(String AccName)
	{
		Boolean checker=false;
		pattern = Pattern.compile(ACC_NAME);
		matcher = pattern.matcher(AccName);
		checker=matcher.matches();
		if(checker)
		{
			setAccName(AccName);
		}
		else{
			return false;
		}
		return true;
	}
	public void setAccName(String s)
	{
		this.accName=s;
	}
	public String getAccName()
	{
		return this.accName;
	}
	public Boolean checkPass(String pass)
	{
		Boolean checker=false;
		pattern = Pattern.compile(PASS_WORD);
		matcher = pattern.matcher(pass);
		checker=matcher.matches();
		if(checker)
		{
			setPass(pass);
		}
		else{
			return false;
		}
		return true;
	}
	public void setPass(String s)
	{
		this.password=s;
	}
	public String getPass()
	{
		return this.password;
	}
	public BasicInfo()
	{
		this.name="Unknown";
		this.adress="Unknown";
		this.number="Unknown";
		this.eMail="Unknown";
	}
	public BasicInfo(String name,String adress,String number,String eMail)
	{
		this.name=name;
		this.adress=adress;
		this.number=number;
		this.eMail=eMail;
	}
	public Boolean checkName(String name)
	{
		Boolean checker=false;
		pattern = Pattern.compile(NAME_PATTERN);
		matcher = pattern.matcher(name);
		checker=matcher.matches();
		if(checker)
		{
			setName(name);
		}
		else{
			return false;
		}
		return true;
	}
	public Boolean checkNumber(String number)
	{
		Boolean checker=false;
		pattern = Pattern.compile(MOBILENUMBER_PATTERN);
		matcher = pattern.matcher(number);
		checker=matcher.matches();
		if(checker)
		{
			setNumber(number);
		}
		else{
			return false;
		}
		return true;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
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
	public void setNumber(String number)
	{
		this.number=number;
	}
	public String getNumber()
	{
		return this.number;
	}
	public boolean checkEmail(String eMail)
	{
		Boolean checker=false;
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(eMail);
		checker=matcher.matches();
		if(checker)
		{
			setEmail(eMail);
		}
		else{
			return false;
		}
		return true;
	}
	public void setEmail(String email)
	{
		this.eMail=email;
	}
	public String getEmail()
	{
		return this.eMail;
	}
}
