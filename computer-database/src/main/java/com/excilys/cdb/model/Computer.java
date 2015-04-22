package com.excilys.cdb.model;

import java.time.LocalDate;

/**
 * 
 * @author ahammani Class which represents Computer Entity
 */

public class Computer {
	private String name = "";
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;
	private long id = 0;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
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
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany_id(Company company) {
		if (company.getId() > 0) {
			this.company = company;
		}
	}

	public Computer() {
	}

	@Override
	public String toString() {
		String intro = (introduced != null) ? "INTRODUCED : "
				+ introduced.toString() + "\t " : "";
		String dis = (discontinued != null) ? "DISCONTINUED : "
				+ discontinued.toString() + "\t " : "";
		String aid = "ID : " + id + "\t ";
		String cname = "COMPUTER NAME : " + name + "\t ";
		String cid = (company.getId() != 0) ? "COMPANY ID :" + company.getId()
				: "";
		return aid + cname + intro + dis + cid;
	}

	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, LocalDate intro, LocalDate dis, Company comp,
			long c_id) {
		this.name = name;
		this.introduced = intro;
		this.discontinued = dis;
		this.company = comp;
		this.id = c_id;
	}

	public Computer(String name, LocalDate intro, LocalDate dis, Company comp) {
		this.name = name;
		this.introduced = intro;
		this.discontinued = dis;
		this.company = comp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

}
