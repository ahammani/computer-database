package com.excilys.cdb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.servlet.dto.ComputerDTO;

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

}
