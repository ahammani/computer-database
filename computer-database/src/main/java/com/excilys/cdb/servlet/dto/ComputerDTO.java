package com.excilys.cdb.servlet.dto;

public class ComputerDTO {
	private String name = "";
	private String introduced = "";
	private String discontinued = "";
	private long id = 0;
	private long company_id = 0;
	private String company_name = "";

	public ComputerDTO(String name, String introduced, String discontinued,
			long id, long company_id, String company_name) {
		super();
		this.name = name;
		this.introduced = (introduced != null) ? introduced : "";
		this.discontinued = (discontinued != null) ? discontinued : "";
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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
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
