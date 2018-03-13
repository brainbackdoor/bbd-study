public class Guitar {

	private String serialNumber;

	private GuitarSpec spec;
	private double price;

	public Guitar(String serialNumber, double price, GuitarSpec spec) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.spec = spec;
	}

	public void setPrice(float newPrice) {
		this.price = newPrice;
	}

	/**
	 * @return the spec
	 */
	public GuitarSpec getSpec() {
		return spec;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

}
