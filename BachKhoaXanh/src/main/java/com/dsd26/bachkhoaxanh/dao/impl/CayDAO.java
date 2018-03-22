package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Nguyễn Phúc Đạc
 */

@Service
@Transactional
public class CayDAO implements ICayDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void luu(CayMD cayMD) {
		String idCay = cayMD.getIdCay();
		Cay cay = null;
		
		if(idCay != null) {
			cay = this.timKiem(idCay);
		}
		if(cay == null) {
			cay = new Cay();
		}
		cay.setIdCay(cayMD.getIdCay());
		cay.setIdLoaiCay(cayMD.getIdLoaiCay());
		cay.setToaDoX(cayMD.getToaDoX());
		cay.setToaDoY(cayMD.getToaDoY());
		cay.setLuongNuocCan(cayMD.getLuongNuocCan());
		cay.setTinhTrang(cayMD.getTinhTrang());
		
		this.sessionFactory.getCurrentSession().persist(cay);
		
	}

	@Override
	public boolean xoa(String idCay) {
		String sql = "";
		if(idCay == null || idCay.equals("")) {
			return false;
		}
		sql = "delete from Cay where id_cay= :id_cay";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id_cay", idCay);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public Cay timKiem(String idCay) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Cay.class);
        crit.add(Restrictions.eq("idCay", idCay));
        return (Cay) crit.uniqueResult();
	}

	@Override
	public PaginationResult<CayMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<CayMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName) {
		String sql = "Select new " + CayMD.class.getName() 
				+ " (p.idCay, p.idLoaiCay, p.toaDoX, p.toaDoY, p.luongNuocCan, p.tinhTrang) " 
				+ " from "
				+ Cay.class.getName() + " p ";
		if (likeName != null && likeName.length() > 0) {
			sql += " Where lower(p.name) like :likeName ";
		}
		sql += " order by p.id asc ";

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		if (likeName != null && likeName.length() > 0) {
			query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
		}
		return new PaginationResult<>(query, page, maxResult, maxNavigationPage);
	}
	
	

}
