package com.excilys.cdb.servlet;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.mapper.TimeMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.utils.Utils;

/**
 * Utils for Servlet
 * 
 * @author ahammani
 *
 */
public class UtilsServlet {

	private static boolean checkDate(String s) {
		return Utils.checkDate(s) || (s == "");
	}

	private static boolean checkName(String computerName) {
		return computerName != "";
	}

	private static boolean checkNumber(String companyId) {
		return Utils.isNumber(companyId) || (companyId == "");
	}

	public static Computer toComputer(HttpServletRequest request,
			CompanyService companyService) {
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		if (checkName(computerName) && checkDate(introduced)
				&& checkDate(discontinued) && checkNumber(companyId)) {
			long cid = Long.parseLong(companyId);
			Company company;
			if (cid == 0) {
				company = new Company();
				company.setId(0);
			} else {
				company = companyService.getCompany(cid);
			}
			LocalDate intro = TimeMapper.StringToLocalDate(introduced);
			LocalDate dis = TimeMapper.StringToLocalDate(discontinued);
			String cId = request.getParameter("id");
			if (cId != null) {
				Long computerId = Long.parseLong(cId);
				return new ComputerBuilder(computerName).id(computerId)
						.introduced(intro).discontinued(dis).company(company)
						.build();
			} else
				return new ComputerBuilder(computerName).introduced(intro)
						.discontinued(dis).company(company).build();

		} else {
			return null;
		}
	}
}
