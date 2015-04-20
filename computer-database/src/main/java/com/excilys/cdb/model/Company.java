package com.excilys.cdb.model;

/**
 * 
 * @author ahammani Class which represents Company Entity
 */

public class Company {

	private long id = 0;
	private String name;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public long getId() {
		if (id > 0)
			return id;
		else
			return 0;
	}

	public void setId(long l) {
		if (l > 0) {
			this.id = l;
		} else
			this.id = 0;
	}

	@Override
	public String toString() {
		return "COMPANY NAME : " + name;
	}

	public Company() {
	}

	public Company(long id, String s) {
		this.id = id;
		this.name = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
