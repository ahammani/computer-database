package model;

/**
 * 
 * @author ahammani Class which represents Company Entity
 */

public class Company {

	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long l) {
		if (l < 1) {
			System.out.println("Impossible set to negative value");
		} else {
			this.id = l;
		}
	}

	@Override
	public String toString() {
		return "COMPANY NAME : " + name;
	}

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
