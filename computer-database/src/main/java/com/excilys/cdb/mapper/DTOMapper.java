package com.excilys.cdb.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.servlet.dto.ComputerDTO;

/**
 * Convertion in DTO
 * 
 * @author ahammani
 *
 */
public class DTOMapper {

	public static ComputerDTO toDTO(Computer c) {
		if (c == null)
			return null;
		String name = c.getName();
		String introduced = TimeMapper.LocalDateToString(c.getIntroduced());
		String discontinued = TimeMapper.LocalDateToString(c.getDiscontinued());
		long id = c.getId();
		long company_id = c.getCompany().getId();
		String company_name = c.getCompany().getName();
		return new ComputerDTO(name, introduced, discontinued, id, company_id,
				company_name);
	}

	public static List<ComputerDTO> toDTOList(List<Computer> computers) {
		return computers.stream().map(x -> toDTO(x))
				.collect(Collectors.toList());
	}

	public static Computer toComputer(ComputerDTO computerDTO,
			CompanyService companyService) {
		String computerName = computerDTO.getName();
		String introduced = computerDTO.getIntroduced();
		String discontinued = computerDTO.getDiscontinued();
		long computerId = computerDTO.getId();
		long companyId = computerDTO.getCompanyId();

		Company company;
		if (companyId == 0) {
			company = new Company();
			company.setId(0);
		} else {
			company = companyService.getCompany(companyId);
		}
		LocalDate intro = TimeMapper.StringToLocalDate(introduced);
		LocalDate dis = TimeMapper.StringToLocalDate(discontinued);
		return new ComputerBuilder(computerName).id(computerId)
				.introduced(intro).discontinued(dis).company(company).build();
	}

}
