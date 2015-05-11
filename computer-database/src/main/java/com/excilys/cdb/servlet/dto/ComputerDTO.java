package com.excilys.cdb.servlet.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ComputerDTO {
	@NotNull(message = "Name required")
	private String name = "";
	private String introduced = "";
	private String discontinued = "";
	@Min(0)
	private long id = 0;
	private long companyId = 0;
	private String companyName = "";

	public ComputerDTO() {

	}

	public ComputerDTO(String name, String introduced, String discontinued,
			long id, long company_id, String company_name) {
		super();
		this.name = name;
		this.introduced = (introduced != null) ? introduced : "";
		this.discontinued = (discontinued != null) ? discontinued : "";
		this.id = id;
		this.companyId = company_id;
		this.companyName = (company_name != null) ? company_name : "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public void setcomputerName(String name) {
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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long company_id) {
		if (company_id > 0)
			this.companyId = company_id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String company_name) {
		this.companyName = company_name;
	}

}
