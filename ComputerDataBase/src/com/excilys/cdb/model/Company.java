package com.excilys.cdb.model;

/**
 * 
 * @author ahammani Class which represents Company Entity
 */

public class Company {

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

	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long l) {
		if (l > 0) {
			this.id = l;
		}
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
