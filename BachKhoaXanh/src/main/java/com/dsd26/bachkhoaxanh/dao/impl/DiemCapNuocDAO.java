package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.IDiemCapNuocDAO;
import com.dsd26.bachkhoaxanh.entity.DiemCapNuoc;
import com.dsd26.bachkhoaxanh.model.DiemCapNuocMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

public class DiemCapNuocDAO implements IDiemCapNuocDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void luu(DiemCapNuocMD diemCapNuocMD) {
		String idDiemCapNuoc = diemCapNuocMD.getIdDiemCapNuoc();
		DiemCapNuoc diemCapNuoc = null;
		
		if(idDiemCapNuoc != null) {
			diemCapNuoc = this.timKiem(idDiemCapNuoc);
		}
		if(diemCapNuoc == null) {
			diemCapNuoc = new DiemCapNuoc();
		}
		diemCapNuoc.setIdDiemCapNuoc(diemCapNuocMD.getIdDiemCapNuoc());
		diemCapNuoc.setToaDoX(diemCapNuocMD.getToaDoX());
		diemCapNuoc.setToaDoY(diemCapNuocMD.getToaDoY());
		diemCapNuoc.setLuongNuocToiDa(diemCapNuocMD.getLuongNuocToiDa());
		diemCapNuoc.setTinhTrang(diemCapNuocMD.getTinhTrang());
		
		this.sessionFactory.getCurrentSession().persist(diemCapNuoc);
		
	}

	@Override
	public boolean xoa(String idDiemCapNuoc) {
		String sql = "";
		System.out.println("gia tri cua idDiemCapNuoc la:" + idDiemCapNuoc);
		if(idDiemCapNuoc == null || idDiemCapNuoc.equals("")) {
			return false;
		}
		sql = "delete from DiemCapNuoc where id_diem_cap_nuoc= :id_diem_cap_nuoc";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id_diem_cap_nuoc", idDiemCapNuoc);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public DiemCapNuoc timKiem(String idDiemCapNuoc) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(DiemCapNuoc.class);
        crit.add(Restrictions.eq("idDiemCapNuoc", idDiemCapNuoc));
        return (DiemCapNuoc) crit.uniqueResult();
	}

	@Override
	public PaginationResult<DiemCapNuocMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<DiemCapNuocMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName) {
		String sql = "Select new " + DiemCapNuocMD.class.getName() 
				+ " (p.idDiemCapNuoc,  p.toaDoX, p.toaDoY, p.luongNuocToiDa, p.tinhTrang) " 
				+ " from "
				+ DiemCapNuoc.class.getName() + " p ";
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
