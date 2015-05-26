package com.excilys.cdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAO implements IDAO<Company> {
	private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	@Autowired
	private SessionFactory sf;

	@Override
	public Company find(long id) {
		Session session = sf.getCurrentSession();
		Company c = (Company) session.get(Company.class, id);
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAll() {
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Company.class, "company");
		return (List<Company>) criteria.addOrder(Order.asc("id")).list();

	}

	@Override
	public void delete(long company_id) {
		Session session = sf.getCurrentSession();
		Company c = (Company) session.get(Company.class, company_id);
		if (c != null) {
			session.delete(c);
		} else {
			logger.debug("Computer is null");
		}
	}

}
