package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.ILoaiThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.LoaiThanhVien;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;


/*
* author: Nguyễn Phúc Đạc
*/

@Service
@Transactional
public class LoaiThanhVienDAO implements ILoaiThanhVienDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void luu(LoaiThanhVienMD loaiThanhVienMD) {
		String idLoaiThanhVien = loaiThanhVienMD.getIdLoaiThanhVien();
		LoaiThanhVien loaiThanhVien = null;
		
		if(idLoaiThanhVien != null && idLoaiThanhVien.equals("")) {
			loaiThanhVien = this.timKiem(idLoaiThanhVien);
		}
		if(loaiThanhVien == null) {
			loaiThanhVien = new LoaiThanhVien();
		}
		
		loaiThanhVien.setIdLoaiThanhVien(loaiThanhVienMD.getIdLoaiThanhVien());
		loaiThanhVien.setTenLoaiThanhVien(loaiThanhVienMD.getTenLoaiThanhVien());
		
		if (loaiThanhVienMD.getAnhLoaiThanhVien() != null) {
            byte[] anhLoaiThanhVien = loaiThanhVienMD.getAnhLoaiThanhVien().getBytes();
            if (anhLoaiThanhVien != null && anhLoaiThanhVien.length > 0) {
            	loaiThanhVien.setAnhLoaiThanhVien(anhLoaiThanhVien);
            }
        }
		
		this.sessionFactory.getCurrentSession().persist(loaiThanhVien);
	}

	@Override
	public boolean xoa(String idLoaiThanhVien) {
		
		String sql = "";
		if(idLoaiThanhVien == null || idLoaiThanhVien.equals("")) {
			return false;
		}
		sql = "delete from LoaiThanhVien where id_loai_thanh_vien= :id_loai_thanh_vien";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id_loai_thanh_vien", idLoaiThanhVien);
		
		int result = query.executeUpdate();
		
		return true;
		
	}

	@Override
	public LoaiThanhVien timKiem(String idLoaiThanhVien) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(LoaiThanhVien.class);
        crit.add(Restrictions.eq("idLoaiThanhVien", idLoaiThanhVien));
        return (LoaiThanhVien) crit.uniqueResult();
	}

	@Override
	public PaginationResult<LoaiThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<LoaiThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		String sql = "Select new " + LoaiThanhVienMD.class.getName() 
				+ " (p.idLoaiThanhVien, p.tenLoaiThanhVien) " 
				+ " from "
				+ LoaiThanhVien.class.getName() + " p ";
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
