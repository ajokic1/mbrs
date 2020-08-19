package springplugin.generator.fmmodel;


public class FMProperty extends FMElement  {
	private FMType type;
	private String visibility;

	//Multiplicity
	private Integer lower;
	private Integer upper;

	public FMProperty(String name, FMType type, String visibility, Integer lower, Integer upper) {
		super(name);
		this.type = type;
		this.visibility = visibility;
		this.lower = lower;
		this.upper = upper;
	}

	public FMProperty() {
		super();
	}

	public FMType getType() {
		return type;
	}

	public void setType(FMType type) {
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}
}
