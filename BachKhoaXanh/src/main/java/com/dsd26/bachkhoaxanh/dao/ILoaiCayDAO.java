package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;


/*
 * author: Nguyễn Phúc Đạc
 */

public interface ILoaiCayDAO {
	public void luu(LoaiCayMD loaiCayMD);
	public boolean xoa(String idLoaiCay);
	public LoaiCay timKiem(String idLoaiCay);
	public PaginationResult<LoaiCayMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<LoaiCayMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
