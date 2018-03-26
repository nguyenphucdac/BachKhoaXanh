package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.LoaiThanhVien;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
* author: Nguyễn Phúc Đạc
*/

public interface ILoaiThanhVienDAO {
	public void luu(LoaiThanhVienMD loaiThanhVienMD);
	public boolean xoa(String idLoaiThanhVien);
	public LoaiThanhVien timKiem(String idLoaiThanhVien);
	public PaginationResult<LoaiThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<LoaiThanhVienMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
