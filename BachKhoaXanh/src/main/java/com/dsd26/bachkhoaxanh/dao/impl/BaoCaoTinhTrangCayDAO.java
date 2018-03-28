package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.IBaoCaoTinhTrangCayDAO;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangCay;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

@Service
@Transactional
public class BaoCaoTinhTrangCayDAO implements IBaoCaoTinhTrangCayDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void luu(BaoCaoTinhTrangCayMD baoCaoTinhTrangCayMD) {
		// TODO Auto-generated method stub
		String id = baoCaoTinhTrangCayMD.getId();
		BaoCaoTinhTrangCay baoCaoTinhTrangCay = null;
		
		if(id != null && id.equals("")) {
			baoCaoTinhTrangCay = this.timKiem(id);
		}
		if(baoCaoTinhTrangCay == null) {
			baoCaoTinhTrangCay = new BaoCaoTinhTrangCay();
		}
		
		baoCaoTinhTrangCay.setId(baoCaoTinhTrangCayMD.getId());
		baoCaoTinhTrangCay.setIdCay(baoCaoTinhTrangCayMD.getIdCay());
		baoCaoTinhTrangCay.setIdThanhVien(baoCaoTinhTrangCayMD.getIdThanhVien());
		baoCaoTinhTrangCay.setThoiGian(baoCaoTinhTrangCayMD.getThoiGian());
		baoCaoTinhTrangCay.setTinhTrang(baoCaoTinhTrangCayMD.getTinhTrang());
		
		this.sessionFactory.getCurrentSession().persist(baoCaoTinhTrangCay);
	}

	@Override
	public boolean xoa(String id) {
		// TODO Auto-generated method stub
		String sql = "";
		if(id == null || id.equals("")) {
			return false;
		}
		sql = "delete from BaoCaoTinhTrangCay where id= :id";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public BaoCaoTinhTrangCay timKiem(String idCay) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(BaoCaoTinhTrangCay.class);
        crit.add(Restrictions.eq("idCay", idCay));
        return (BaoCaoTinhTrangCay) crit.uniqueResult();
	}

	@Override
	public PaginationResult<BaoCaoTinhTrangCayMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<BaoCaoTinhTrangCayMD> queryRoles(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + BaoCaoTinhTrangCayMD.class.getName() 
				+ " (p.id,  p.idThanhVien, p.idCay, p.tinhTrang, p.thoiGian) " 
				+ " from "
				+ BaoCaoTinhTrangCay.class.getName() + " p ";
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