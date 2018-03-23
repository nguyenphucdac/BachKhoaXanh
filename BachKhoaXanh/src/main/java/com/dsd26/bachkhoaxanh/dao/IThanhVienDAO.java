package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
* author: Nguyễn Phúc Đạc
*/

public interface IThanhVienDAO {
	public void luu(ThanhVienMD thanhVienMD);
	public boolean xoa(String idThanhVien);
	public ThanhVien timKiem(String idThanhVien);
	public PaginationResult<ThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<ThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
