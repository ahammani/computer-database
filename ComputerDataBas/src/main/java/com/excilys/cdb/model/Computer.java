package com.excilys.cdb.model;

/**
 * 
 * @author ahammani
 * Class which represents Computer Entity
 */
import java.time.LocalDateTime;

public class Computer {
	private String name = "";
	private LocalDateTime intro_date;
	private LocalDateTime dis_date;
	private Company company;
	private long id = 0;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((dis_date == null) ? 0 : dis_date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((intro_date == null) ? 0 : intro_date.hashCode());
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
		if (dis_date == null) {
			if (other.dis_date != null)
				return false;
		} else if (!dis_date.equals(other.dis_date))
			return false;
		if (id != other.id)
			return false;
		if (intro_date == null) {
			if (other.intro_date != null)
				return false;
		} else if (!intro_date.equals(other.intro_date))
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
		String intro = (intro_date != null) ? "INTRODUCED : "
				+ intro_date.toString() + "\t " : "";
		String dis = (dis_date != null) ? "DISCONTINUED : "
				+ dis_date.toString() + "\t " : "";
		String aid = "ID : " + id + "\t ";
		String cname = "COMPUTER NAME : " + name + "\t ";
		String cid = (company.getId() != 0) ? "COMPANY ID :" + company.getId()
				: "";
		return aid + cname + intro + dis + cid;
	}

	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, LocalDateTime intro, LocalDateTime dis,
			Company comp, long c_id) {
		this.name = name;
		this.intro_date = intro;
		this.dis_date = dis;
		this.company = comp;
		this.id = c_id;
	}

	public Computer(String name, LocalDateTime intro, LocalDateTime dis,
			Company comp) {
		this.name = name;
		this.intro_date = intro;
		this.dis_date = dis;
		this.company = comp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntro_date() {
		return intro_date;
	}

	public void setIntro_date(LocalDateTime intro_date) {
		this.intro_date = intro_date;
	}

	public LocalDateTime getDis_date() {
		return dis_date;
	}

	public void setDis_date(LocalDateTime dis_date) {
		this.dis_date = dis_date;
	}

}
