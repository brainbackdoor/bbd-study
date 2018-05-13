
public class GuitarSpec {
	private Builder builder;
	private Type type;
	private Wood backWood, topWood;
	private String model;
	private String numString;

	public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood, String numString) {
		this.builder = builder;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
		this.model = model;
		this.numString = numString;
	}

	public boolean matches(GuitarSpec searchSpec) {
		if (searchSpec.getBuilder() != this.getBuilder())
			return false;
		if (searchSpec.getModel() != this.getModel())
			return false;
		if (searchSpec.getType() != this.getType())
			return false;
		if (searchSpec.getBackWood() != this.getBackWood())
			return false;
		if (searchSpec.getTopWood() != this.getTopWood())
			return false;
		return true;
	}

	/**
	 * @return the numString
	 */
	public String getNumString() {
		return numString;
	}

	/**
	 * @return the builder
	 */
	public Builder getBuilder() {
		return builder;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the backWood
	 */
	public Wood getBackWood() {
		return backWood;
	}

	/**
	 * @return the topWood
	 */
	public Wood getTopWood() {
		return topWood;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

}
