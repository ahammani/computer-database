package com.excilys.cdb.servlet.dto;

import java.time.LocalDateTime;

import com.excilys.cdb.model.Company;

public class ComputerDTO {
	private String name = "";
	private String intro_date;
	private LocalDateTime dis_date;
	private Company company;
	private long id = 0;
	private long company_id;
	private String company_name;

	public ComputerDTO(String name, String intro_date, LocalDateTime dis_date,
			Company company, long id, long company_id, String company_name) {
		super();
		this.name = name;
		this.intro_date = intro_date;
		this.dis_date = dis_date;
		this.company = company;
		this.id = id;
		this.company_id = company_id;
		this.company_name = company_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro_date() {
		return intro_date;
	}

	public void setIntro_date(String intro_date) {
		this.intro_date = intro_date;
	}

	public LocalDateTime getDis_date() {
		return dis_date;
	}

	public void setDis_date(LocalDateTime dis_date) {
		this.dis_date = dis_date;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

}
