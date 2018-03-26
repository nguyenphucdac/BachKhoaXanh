package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.ThongBao;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThongBaoMD;

/*
* author: Nguyễn Phúc Đạc
*/


public interface IThongBaoDAO {
	public void luu(ThongBaoMD thongBaoMD);
	public boolean xoa(String idThongBao);
	public ThongBao timKiem(String idThongBao);
	public PaginationResult<ThongBaoMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<ThongBaoMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
