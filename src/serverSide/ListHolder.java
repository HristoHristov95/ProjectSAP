package serverSide;

import java.util.ArrayList;

public class ListHolder {
	private ArrayList<Person> listPerson;
	private ArrayList<OfferInfo> listOfferInfo;
	public ListHolder(){
		this.listOfferInfo=null;
		this.listPerson=null;
	}
	public ListHolder(ArrayList<Person> a,ArrayList<OfferInfo> b){
		this.listPerson=a;
		this.listOfferInfo=b;
	}
	public void setListPerson(ArrayList<Person> list){
		this.listPerson=list;
	}
	public ArrayList<Person> getListPerson(){
		return this.listPerson;
	}
	public void setListOfferInfo(ArrayList<OfferInfo> list){
		this.listOfferInfo=list;
	}
	public ArrayList<OfferInfo> getListOfferInfo(){
		return this.listOfferInfo;
	}
}
