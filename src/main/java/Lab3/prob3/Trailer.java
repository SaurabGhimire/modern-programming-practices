package Lab3.prob3;


public class Trailer extends Property {
	private static final double RENT = 500;
	
	public Trailer(Address address) {
		super(address);
	}
	public double computeRent(){
		return RENT;
	}

	public String toString(){
		return "Trailer at: " + getAddress() + " Rent: " + computeRent();
	}
}