package com.excilys.cdb.servlet.dto;

public class ComputerDTO {
	private String name = "";
	private String intro_date = "";
	private String dis_date = "";
	private long id = 0;
	private long company_id = 0;
	private String company_name = "";

	public ComputerDTO(String name, String intro_date, String dis_date,
			long id, long company_id, String company_name) {
		super();
		this.name = name;
		this.intro_date = (intro_date != null) ? intro_date : "";
		this.dis_date = (dis_date != null) ? dis_date : "";
		this.id = id;
		this.company_id = company_id;
		this.company_name = (company_name != null) ? company_name : "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public String getIntro_date() {
		return intro_date;
	}

	public void setIntro_date(String intro_date) {
		this.intro_date = intro_date;
	}

	public String getDis_date() {
		return dis_date;
	}

	public void setDis_date(String dis_date) {
		this.dis_date = dis_date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id > 0)
			this.id = id;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		if (company_id > 0)
			this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

}