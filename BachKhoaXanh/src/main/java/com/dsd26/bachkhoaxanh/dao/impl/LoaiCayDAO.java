package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Nguyễn Phúc Đạc
 */

@Service
@Transactional
public class LoaiCayDAO implements ILoaiCayDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void luu(LoaiCayMD loaiCayMD) {
		String idLoaiCay = loaiCayMD.getIdLoaiCay();
		LoaiCay loaiCay = null;
		
		if(idLoaiCay != null && idLoaiCay.equals("")) {
			loaiCay = this.timKiem(idLoaiCay);
		}
		if(loaiCay == null) {
			loaiCay = new LoaiCay();
		}
		loaiCay.setIdLoaiCay(loaiCayMD.getIdLoaiCay());
		loaiCay.setTenLoaiCay(loaiCayMD.getTenLoaiCay());
		
		if (loaiCayMD.getAnhLoaiCay() != null) {
            byte[] anhLoaiCay = loaiCayMD.getAnhLoaiCay().getBytes();
            if (anhLoaiCay != null && anhLoaiCay.length > 0) {
            	loaiCay.setAnhLoaiCay(anhLoaiCay);
            }
        }
		
		this.sessionFactory.getCurrentSession().persist(loaiCay);
	}

	@Override
	public boolean xoa(String idLoaiCay) {
		String sql = "";
		if(idLoaiCay == null || idLoaiCay.equals("")) {
			return false;
		}
		sql = "delete from LoaiCay where id_loai_cay= :id_loai_cay";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id_loai_cay", idLoaiCay);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public LoaiCay timKiem(String idLoaiCay) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(LoaiCay.class);
        crit.add(Restrictions.eq("idLoaiCay", idLoaiCay));
        return (LoaiCay) crit.uniqueResult();
	}

	@Override
	public PaginationResult<LoaiCayMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<LoaiCayMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName) {
		String sql = "Select new " + LoaiCayMD.class.getName() 
				+ " (p.idLoaiCay, p.tenLoaiCay) " 
				+ " from "
				+ LoaiCay.class.getName() + " p ";
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
