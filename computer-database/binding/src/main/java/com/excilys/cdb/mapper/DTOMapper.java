package com.excilys.cdb.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.dto.ComputerDTO;

/**
 * Convertion in DTO
 * 
 * @author ahammani
 *
 */
@Component
public class DTOMapper {
	@Autowired
	private TimeMapper timeMapper;

	public ComputerDTO toDTO(Computer c) {
		if (c == null)
			return null;
		String name = c.getName();
		String introduced = timeMapper.LocalDateToString(c.getIntroduced());
		String discontinued = timeMapper.LocalDateToString(c.getDiscontinued());
		long id = c.getId();
		Company company = c.getCompany();
		long company_id;
		if (company != null) {
			company_id = company.getId();
		} else {
			company_id = 0;
		}
		String company_name;
		if (company != null)
			company_name = company.getName();
		else
			company_name = null;
		return new ComputerDTO(name, introduced, discontinued, id, company_id,
				company_name);
	}

	public List<ComputerDTO> toDTOList(List<Computer> computers) {
		return computers.stream().map(x -> toDTO(x))
				.collect(Collectors.toList());
	}

	public List<Computer> toComputerList(List<ComputerDTO> computers) {
		return computers.stream().map(x -> toComputer(x))
				.collect(Collectors.toList());
	}

	public Computer toComputer(ComputerDTO computerDTO) {
		String computerName = computerDTO.getName();
		String introduced = computerDTO.getIntroduced();
		String discontinued = computerDTO.getDiscontinued();
		long computerId = computerDTO.getId();
		long companyId = computerDTO.getCompanyId();
		String companyName = computerDTO.getCompanyName();

		Company company;
		if (companyId == 0) {
			company = new Company();
			company.setId(0);
		} else {
			company = new Company(companyId, companyName);
		}
		LocalDate intro = timeMapper.StringToLocalDate(introduced);
		LocalDate dis = timeMapper.StringToLocalDate(discontinued);
		return new ComputerBuilder(computerName).id(computerId)
				.introduced(intro).discontinued(dis).company(company).build();
	}

}
