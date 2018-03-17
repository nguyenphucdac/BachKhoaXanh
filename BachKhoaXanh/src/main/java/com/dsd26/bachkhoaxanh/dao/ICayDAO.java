package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;


/*
 * author: Nguyễn Phúc Đạc
 */

public interface ICayDAO {
	public void luu(CayMD cayMD);
	public boolean xoa(String idCay);
	public Cay timKiem(String idCay);
	public PaginationResult<CayMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<CayMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
