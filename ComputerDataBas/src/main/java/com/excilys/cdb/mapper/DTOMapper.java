package com.excilys.cdb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.*;
import com.excilys.cdb.service.ComputerDAOService;
import com.excilys.cdb.servlet.dto.ComputerDTO;

public class DTOMapper {

	public static ComputerDTO toDTO(Computer c) {
		String name = c.getName();
		String intro_date = TimeMapper.LocalDateTimeToString(c.getIntro_date());
		String dis_date = TimeMapper.LocalDateTimeToString(c.getDis_date());
		long id = c.getId();
		long company_id = c.getCompany().getId();
		String company_name = c.getCompany().getName();
		return new ComputerDTO(name, intro_date, dis_date, id, company_id,
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
