package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.IBaoCaoTinhTrangDCNDAO;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangCay;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangDCN;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangCayMD;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangDCNMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

@Service
@Transactional
public class BaoCaoTinhTrangDCNDAO implements IBaoCaoTinhTrangDCNDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void luu(BaoCaoTinhTrangDCNMD baoCaoTinhTrangDCNMD) {
		// TODO Auto-generated method stub
		String id = baoCaoTinhTrangDCNMD.getId();
		BaoCaoTinhTrangDCN baoCaoTinhTrangDCN = null;
		
		if(id != null && id.equals("")) {
			baoCaoTinhTrangDCN = this.timKiem(id);
		}
		if(baoCaoTinhTrangDCN == null) {
			baoCaoTinhTrangDCN = new BaoCaoTinhTrangDCN();
		}
		
		baoCaoTinhTrangDCN.setId(baoCaoTinhTrangDCNMD.getId());
		baoCaoTinhTrangDCN.setIdDiemCapNuoc(baoCaoTinhTrangDCNMD.getIdDiemCapNuoc());
		baoCaoTinhTrangDCN.setIdThanhVien(baoCaoTinhTrangDCNMD.getIdThanhVien());
		baoCaoTinhTrangDCN.setThoiGian(baoCaoTinhTrangDCNMD.getThoiGian());
		baoCaoTinhTrangDCN.setTinhTrang(baoCaoTinhTrangDCNMD.getTinhTrang());
		
		this.sessionFactory.getCurrentSession().persist(baoCaoTinhTrangDCN);
	}

	@Override
	public boolean xoa(String id) {
		// TODO Auto-generated method stub
		String sql = "";
		if(id == null || id.equals("")) {
			return false;
		}
		sql = "delete from BaoCaoTinhTrangDCN where id= :id";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public BaoCaoTinhTrangDCN timKiem(String idDiemCapNuoc) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(BaoCaoTinhTrangDCN.class);
        crit.add(Restrictions.eq("idDiemCapNuoc", idDiemCapNuoc));
        return (BaoCaoTinhTrangDCN) crit.uniqueResult();
	}

	@Override
	public PaginationResult<BaoCaoTinhTrangDCNMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<BaoCaoTinhTrangDCNMD> queryRoles(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + BaoCaoTinhTrangDCNMD.class.getName() 
				+ " (p.id,  p.idThanhVien, p.idDiemCapNuoc, p.tinhTrang, p.thoiGian) " 
				+ " from "
				+ BaoCaoTinhTrangDCN.class.getName() + " p ";
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