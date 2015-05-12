package com.excilys.cdb.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ComputerDTO {
	@NotNull(message = "Name required")
	private String name;
	@DateTimeFormat
	private String introduced;
	@DateTimeFormat
	private String discontinued;
	@Min(0)
	private long id;
	private long companyId;
	private String companyName;

	public ComputerDTO() {
		this(null, null, null, 0, 0, null);
	}

	public ComputerDTO(String name, String introduced, String discontinued,
			long id, long companyId, String companyName) {
		super();
		this.name = name;
		this.introduced = (introduced != null) ? introduced : "";
		this.discontinued = (discontinued != null) ? discontinued : "";
		this.id = id;
		this.companyId = companyId;
		this.companyName = (companyName != null) ? companyName : "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
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
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "ComputerDTO [name=" + name + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + ", id=" + id
				+ ", companyId=" + companyId + ", companyName=" + companyName
				+ "]";
	}

}
