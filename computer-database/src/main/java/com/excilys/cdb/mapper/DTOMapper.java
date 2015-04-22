package com.excilys.cdb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.servlet.dto.ComputerDTO;

public class DTOMapper {

	public static ComputerDTO toDTO(Computer c) {
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
		List<ComputerDTO> res = new ArrayList<>();
		for (Computer c : computers) {
			res.add(DTOMapper.toDTO(c));
		}
		return res;
	}

}
