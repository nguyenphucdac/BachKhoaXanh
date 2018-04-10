package com.dsd26.bachkhoaxanh.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsd26.bachkhoaxanh.dao.IThongBaoDAO;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.entity.ThongBao;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;
import com.dsd26.bachkhoaxanh.model.ThongBaoMD;


/*
* author: Nguyễn Phúc Đạc
*/

public class ThongBaoDAO implements IThongBaoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void luu(ThongBaoMD thongBaoMD) {
		String idThongBao = thongBaoMD.getIdThongBao();
		ThongBao thongBao = null;
		
		if(idThongBao == null && idThongBao != "") {
			thongBao = this.timKiem(idThongBao);
		}
		if(thongBao == null) {
			thongBao = new ThongBao();
		}
		
		thongBao.setIdThongBao(thongBaoMD.getIdThongBao());
		thongBao.setNoiDung(thongBaoMD.getNoiDung());
		thongBao.setThoiGian(thongBaoMD.getThoiGian());
		thongBao.setIdThanhVien(thongBaoMD.getIdThanhVien());
		
		this.sessionFactory.getCurrentSession().persist(thongBao);
		
	}

	@Override
	public boolean xoa(String idThongBao) {
		String sql = "";
		if(idThongBao == null || idThongBao.equals("")) {
			return false;
		}
		sql = "delete from ThongBao where id_thong_bao= :id_thong_bao";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setParameter("id_thong_bao", idThongBao);
		
		int result = query.executeUpdate();
		
		return true;
	}

	@Override
	public ThongBao timKiem(String idThongBao) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(ThongBao.class);
        crit.add(Restrictions.eq("idThongBao", idThongBao));
        return (ThongBao) crit.uniqueResult();
	}

	@Override
	public PaginationResult<ThongBaoMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		return queryRoles(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<ThongBaoMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName) {
		String sql = "Select new " + ThongBaoMD.class.getName() 
				+ " (p.idThongBao, p.noiDung, p.thoiGian, p.idThanhVien) " 
				+ " from "
				+ ThongBao.class.getName() + " p ";
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
