package rbs;

public class TravelAgency {
	private String name;
	private float discount;
	
	public TravelAgency(String name, float discount) {
		this.name = name;
		this.discount = discount;
	}
	
	public TravelAgency(String name) {
		this(name, 0);
	}
	
	public float getDiscount() {
		return this.discount;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return "TravelAgency [ name = " + this.getName() + ", discount = " + this.getDiscount() + " ]";
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof TravelAgency)) return false;
		TravelAgency t = (TravelAgency) obj;
		return this.getName() == t.getName();
	}

}
