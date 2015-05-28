package com.excilys.cdb.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 * 
 * @author ahammani Class which represents Computer Entity
 */
@XmlRootElement
@SuppressWarnings("serial")
@Entity
@Table(name = "computer")
public class Computer implements Serializable {
	private String name = "";
	@Type(type = "com.excilys.cdb.mapper.LocalDateType")
	private LocalDate introduced = null;
	@Type(type = "com.excilys.cdb.mapper.LocalDateType")
	private LocalDate discontinued = null;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company = null;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
		String cid = (company != null && company.getId() != 0) ? "COMPANY ID :"
				+ company.getId() : "";
		return aid + cname + intro + dis + cid;
	}

	public Computer(String name) {
		this.name = name;
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

	public static class ComputerBuilder {
		private Computer computer;

		public ComputerBuilder(String name) {
			computer = new Computer(name);
		}

		public ComputerBuilder id(long id) {
			computer.id = id;
			return this;
		}

		public ComputerBuilder name(String name) {
			computer.name = name;
			return this;
		}

		public ComputerBuilder introduced(LocalDate date) {
			computer.introduced = date;
			return this;
		}

		public ComputerBuilder discontinued(LocalDate date) {
			computer.discontinued = date;
			return this;
		}

		public ComputerBuilder company(Company company) {
			computer.company = company;
			return this;
		}

		public Computer build() {
			return computer;
		}
	}

}
