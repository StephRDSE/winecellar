package winecellar;

public class Wine {

	private Long wineId;
	private String name;
	private String country;
	private String county;
	private String type;
	private String color;
	private int year;
	private double price;	
	
	public Wine(Long wineId, String name ,String country , String county , String type ,String color ,  int year, double price) {
		this.wineId = wineId;
		this.name = name;
		this.country = country;
		this.county = county;
		this.type = type;
		this.color = color;
		this.year = year;
		this.price = price;
	}
public Wine(String name ,String country , String county , String type ,String color ,  int year, double price) {
		
		this.name = name;
		this.country = country;
		this.county = county;
		this.type = type;
		this.color = color;
		this.year = year;
		this.price = price;
	}

	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "wine [wineId=" + wineId + ", name=" + name + ", country=" + country + ", county=" + county + ", type="
				+ type + ", color=" + color + ", year=" + year + ", price=" + price + "]";
	}
}
