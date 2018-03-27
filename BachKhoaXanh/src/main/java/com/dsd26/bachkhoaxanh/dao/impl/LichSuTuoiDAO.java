package com.dsd26.bachkhoaxanh.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd26.bachkhoaxanh.dao.ILichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.entity.LichSuTuoi;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.LoaiThanhVien;
import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
* author: Nguyễn Phúc Đạc
*/

@Service
@Transactional
public class LichSuTuoiDAO implements ILichSuTuoiDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void luu(LichSuTuoiMD lichSuTuoiMD) {
		String idLichSuTuoi = lichSuTuoiMD.getIdLichSuTuoi();
		LichSuTuoi lichSuTuoi = null;
		
		if(idLichSuTuoi != null && idLichSuTuoi.equals("")) {
			lichSuTuoi = this.timKiem(idLichSuTuoi);
		}
		if(lichSuTuoi == null) {
			lichSuTuoi = new LichSuTuoi();
		}
		
		lichSuTuoi.setIdLichSuTuoi(lichSuTuoiMD.getIdLichSuTuoi());
		lichSuTuoi.setIdCay(lichSuTuoiMD.getIdCay());
		lichSuTuoi.setIdThanhVien(lichSuTuoiMD.getIdThanhVien());
		lichSuTuoi.setLuongNuocDaTuoi(lichSuTuoiMD.getLuongNuocDaTuoi());
		lichSuTuoi.setThoiGian(lichSuTuoiMD.getThoiGian());
		
		this.sessionFactory.getCurrentSession().persist(lichSuTuoi);
	}

	@Override
	public boolean xoa(String idLichSuTuoi) {
		String sql = "";
		if(idLichSuTuoi == null || idLichSuTuoi.equals("")) {
			return false;
		}
		sql = "delete from LichSuTuoi where id_lich_su_tuoi= :id_lich_su_tuoi";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id_lich_su_tuoi", idLichSuTuoi);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public LichSuTuoi timKiem(String idLichSuTuoi) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(LichSuTuoi.class);
        crit.add(Restrictions.eq("idLichSuTuoi", idLichSuTuoi));
        return (LichSuTuoi) crit.uniqueResult();
	}

	@Override
	public PaginationResult<LichSuTuoiMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<LichSuTuoiMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName) {
		String sql = "Select new " + LichSuTuoiMD.class.getName() 
				+ " (p.idLichSuTuoi, p.idCay, p.idThanhVien, p.luongNuocDaTuoi, p.thoiGian) " 
				+ " from "
				+ LichSuTuoi.class.getName() + " p ";
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
