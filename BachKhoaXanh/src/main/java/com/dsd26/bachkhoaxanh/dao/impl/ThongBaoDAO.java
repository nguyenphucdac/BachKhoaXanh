package com.dsd26.bachkhoaxanh.dao.impl;

import com.dsd26.bachkhoaxanh.dao.IThongBaoDAO;
import com.dsd26.bachkhoaxanh.entity.ThongBao;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThongBaoMD;


/*
* author: Nguyễn Phúc Đạc
*/

public class ThongBaoDAO implements IThongBaoDAO {

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
		thongBao.setIdNguoiTao(thongBaoMD.getIdNguoiTao());
		
	}

	@Override
	public boolean xoa(String idThongBao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ThongBao timKiem(String idThongBao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<ThongBaoMD> queryRoles(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<ThongBaoMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName) {
		// TODO Auto-generated method stub
		return null;
	}

}
