package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.DiemCapNuoc;
import com.dsd26.bachkhoaxanh.model.DiemCapNuocMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

public interface IDiemCapNuocDAO {
	public void luu(DiemCapNuocMD diemCapNuocMD);
	public boolean xoa(String idDiemCapNuoc);
	public DiemCapNuoc timKiem(String idDiemCapNuoc);
	public PaginationResult<DiemCapNuocMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<DiemCapNuocMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
