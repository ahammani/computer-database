package com.excilys.cdb.servlet;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.excilys.cdb.mapper.TimeMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
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

	public static Computer postComputer(HttpServletRequest request,
			boolean update) {
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		System.out.println("UTILS name " + computerName + " intro "
				+ introduced + " dis " + discontinued + " compID " + companyId);
		if (checkName(computerName) && checkDate(introduced)
				&& checkDate(discontinued) && checkNumber(companyId)) {
			long cid = Long.parseLong(companyId);
			Company company;
			if (cid == 0) {
				company = new Company();
				company.setId(0);
			} else {
				company = CompanyService.INSTANCE.getCompany(cid);
			}
			LocalDate intro = TimeMapper.StringToLocalDate(introduced);
			LocalDate dis = TimeMapper.StringToLocalDate(discontinued);

			System.out.println("UTILS2 name " + computerName + " intro "
					+ intro + " dis " + dis + " compID " + companyId);
			if (update) {
				long computerId = Long.parseLong(request.getParameter("id"));
				return new Computer(computerName, intro, dis, company,
						computerId);
			} else
				return new Computer(computerName, intro, dis, company);

		} else {
			return null;
		}
	}
}
