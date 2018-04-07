package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;

/*
* author: Nguyễn Phúc Đạc
*/

@Service
@Transactional
public class ThanhVienDAO implements IThanhVienDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void luu(ThanhVienMD thanhVienMD) {
		String idThanhVien = thanhVienMD.getIdThanhVien();
		ThanhVien thanhVien = null;
		
		if(idThanhVien != null  && idThanhVien.equals("")) {
			thanhVien = this.timKiem(idThanhVien);
		}
		if(thanhVien == null) {
			thanhVien = new ThanhVien();
		}
		
		thanhVien.setIdThanhVien(thanhVienMD.getIdThanhVien());
		thanhVien.setIdLoaiThanhVien(thanhVienMD.getIdLoaiThanhVien());
		thanhVien.setTenTaiKhoan(thanhVienMD.getTenTaiKhoan());
		thanhVien.setMatKhau(thanhVienMD.getMatKhau());
		thanhVien.setTenDayDu(thanhVienMD.getTenDayDu());
		thanhVien.setTrangThai(thanhVienMD.getTrangThai());
		
		if (thanhVienMD.getAnhThanhVien() != null) {
            byte[] anhThanhVien = thanhVienMD.getAnhThanhVien().getBytes();
            if (anhThanhVien != null && anhThanhVien.length > 0) {
            	thanhVien.setAnhThanhVien(anhThanhVien);
            }
        }
		
		this.sessionFactory.getCurrentSession().persist(thanhVien);
		
	}

	@Override
	public boolean xoa(String idThanhVien) {
		String sql = "";
		if(idThanhVien == null || idThanhVien.equals("")) {
			return false;
		}
		sql = "delete from ThanhVien where id_thanh_vien= :id_thanh_vien";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id_thanh_vien", idThanhVien);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public ThanhVien timKiem(String idThanhVien) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(LoaiCay.class);
        crit.add(Restrictions.eq("idThanhVien", idThanhVien));
        return (ThanhVien) crit.uniqueResult();
	}

	@Override
	public PaginationResult<ThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<ThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName) {
		String sql = "Select new " + ThanhVienMD.class.getName() 
				+ " (p.idThanhVien, p.tenTaiKhoan, p.tenDayDu, p.trangThai) " 
				+ " from "
				+ ThanhVien.class.getName() + " p ";
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
