package com.excilys.cdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;

@Repository
public class ComputerDAO implements IDAO<Computer> {

	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Autowired
	private SessionFactory sf;

	public int count() {
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Computer.class);
		return ((Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
	}

	// search is checked in Page
	public int count(String search) {
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		String searc = new StringBuilder("%").append(search).append("%")
				.toString();
		Criterion likeComputer = Restrictions.like("computer.name", searc);
		Criterion likeCompany = Restrictions.like("company.name", searc);
		criteria.add(Restrictions.or(likeCompany, likeComputer));
		return ((Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
	}

	@Override
	public void create(Computer obj) {
		Session session = sf.getCurrentSession();
		if (obj.getCompany() != null && obj.getCompany().getId() == 0) {
			obj.setCompany(null);
		}
		long id = (long) session.save(obj);
		obj.setId(id);
	}

	@Override
	public void delete(long id) {
		Session session = sf.getCurrentSession();
		Computer c = (Computer) session.get(Computer.class, id);
		if (c != null) {
			session.delete(c);
		} else {
			logger.debug("Computer is null");
		}
	}

	@Override
	public void update(Computer obj) {
		Session session = sf.getCurrentSession();
		if (obj.getCompany() != null && obj.getCompany().getId() == 0) {
			obj.setCompany(null);
		}
		session.update(obj);
	}

	@Override
	public Computer find(long id) {
		Session session = sf.getCurrentSession();
		Computer c = (Computer) session.get(Computer.class, id);
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAll() {
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Computer.class, "computer");
		return (List<Computer>) criteria.addOrder(Order.asc("id")).list();
	}

	// field_order and order are checked in Page
	@SuppressWarnings("unchecked")
	public List<Computer> findAll(int offset, int limit, String field_order,
			String order) {

		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		if (order.toLowerCase().contains("desc")) {
			criteria.addOrder(Order.desc(field_order));
		} else {
			criteria.addOrder(Order.asc(field_order));
		}
		criteria.setFirstResult(offset);
		criteria.setMaxResults(limit);
		return (List<Computer>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(String search, int offset, int limit,
			String field_order, String order) {
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		String searc = new StringBuilder("%").append(search).append("%")
				.toString();
		Criterion likeComputer = Restrictions.like("computer.name", searc);
		Criterion likeCompany = Restrictions.like("company.name", searc);
		criteria.add(Restrictions.or(likeCompany, likeComputer));
		if (order.toLowerCase().contains("desc")) {
			criteria.addOrder(Order.desc(field_order));
		} else {
			criteria.addOrder(Order.asc(field_order));
		}
		criteria.setFirstResult(offset);
		criteria.setMaxResults(limit);
		return (List<Computer>) criteria.list();
	}

	/**
	 * Find all computers with company_id equal id
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Computer> findAllCompany(long company_id) {
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("company_id", company_id));
		return criteria.list();
	}

	public void deleteByCompany(long company_id) {
		Session session = sf.getCurrentSession();
		session.createQuery("delete from Computer where id=:id")
				.setLong("id", company_id).executeUpdate();
	}
}
