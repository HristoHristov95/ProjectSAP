package serverSide;

public class OfferInfo {
	public static final String AttributeIDOffer="11";
	private String destination;
	private String lenghtOfDestination;
	private String price;
	private String hotels;
	private String travelingWithVehicle;
	private String daysOfBeginingAndEnd;
	private String allBonusInfo;
	private String creatorOfOfferName;
	private String creatorOfOfferNumber;
	private String creatorOfOfferEmail;
	private String interestedCustomersNames;
	private String interestedCustomersNumbers;
	private String interestedCustomersEmails;
	public OfferInfo(String destination,String lenghtOfDestination,String price,String hotels,String travelingWithVehicle,String daysOfBeginingAndEnd,String allBonusInfo,
			String creatorOfOfferName,String creatorOfOfferNumber,String creatorOfOfferEmail,String customersNames,String customersNumbers,String customersEmails){
		this.destination=destination;
		this.lenghtOfDestination=lenghtOfDestination;
		this.price=price;
		this.hotels=hotels;
		this.travelingWithVehicle=travelingWithVehicle;
		this.daysOfBeginingAndEnd=daysOfBeginingAndEnd;
		this.allBonusInfo=allBonusInfo;
		this.creatorOfOfferName=creatorOfOfferName;
		this.creatorOfOfferNumber=creatorOfOfferNumber;
		this.creatorOfOfferEmail=creatorOfOfferEmail;
		this.interestedCustomersNames=customersNames;
		this.interestedCustomersNumbers=customersNumbers;
		this.interestedCustomersEmails=customersEmails;
	}
	public OfferInfo()
	{
		this.destination=" ";
		this.lenghtOfDestination=" ";
		this.price=" ";
		this.hotels=" ";
		this.travelingWithVehicle=" ";
		this.daysOfBeginingAndEnd=" ";
		this.allBonusInfo=" ";
		this.creatorOfOfferName=" ";
		this.creatorOfOfferNumber=" ";
		this.creatorOfOfferEmail=" ";
		this.interestedCustomersNames=" ";
		this.interestedCustomersNumbers=" ";
		this.interestedCustomersEmails=" ";
	}
	public void setDestinationName(String s){
		this.destination=s;
	}
	public String getDestinationName(){
		return this.destination;
	}
	public void setLenghtOfDestination(String s){
		this.lenghtOfDestination=s;
	}
	public String getLenghtOfDestination(){
		return this.lenghtOfDestination;
	}
	public void setPrice(String s){
		this.price=s;
	}
	public String getPrice(){
		return this.price;
	}
	public void setHotels(String s){
		this.hotels=s;
	}
	public String getHotels(){
		return this.hotels;
	}
	public void setTravelingWithVehicle(String s){
		this.travelingWithVehicle=s;
	}
	public String getTravelingWithVehicle(){
		return this.travelingWithVehicle;
	}
	public void setDaysOfBeginingAndEnd(String s){
		this.daysOfBeginingAndEnd=s;
	}
	public String getDaysOfBeginingAndEnd(){
		return this.daysOfBeginingAndEnd;
	}
	public void setAllBonusInfo(String s){
		this.allBonusInfo=s;
	}
	public String getAllBonusInfo(){
		return this.allBonusInfo;
	}
	public void setCreatorOfOfferName(String s){
		this.creatorOfOfferName=s;
	}
	public String getCreatorOfOfferName(){
		return this.creatorOfOfferName;
	}
	public void setCreatorOfOfferNumber(String s){
		this.creatorOfOfferNumber=s;
	}
	public String getCreatorOfOfferNumber(){
		return this.creatorOfOfferNumber;
	}
	public void setCreatorOfOfferEmail(String s){
		this.creatorOfOfferEmail=s;
	}
	public String getCreatorOfOfferEmail(){
		return this.creatorOfOfferEmail;
	}
	public void setCustomersNames(String s){
		this.interestedCustomersNames=s;
	}
	public String getCustomersNames(){
		return this.interestedCustomersNames;
	}
	public void setCustomersNumbers(String s){
		this.interestedCustomersNumbers=s;
	}
	public String getCustomersNumbers(){
		return this.interestedCustomersNumbers;
	}
	public void setCustomersEmails(String s){
		this.interestedCustomersEmails=s;
	}
	public String getCustomersEmails(){
		return this.interestedCustomersEmails;
	}
}
