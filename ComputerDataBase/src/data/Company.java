package data;

/**
 * 
 * @author ahammani Class which represents Company Entity
 */

public class Company {
	@Override
	public String toString() {
		return "Company name " + name;
	}

	private String name;

	public Company() {
	}

	public Company(String s) {
		this.name = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
