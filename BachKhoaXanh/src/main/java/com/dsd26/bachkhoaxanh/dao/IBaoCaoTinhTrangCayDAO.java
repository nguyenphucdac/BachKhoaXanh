package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangCay;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

public interface IBaoCaoTinhTrangCayDAO {
	public void luu(BaoCaoTinhTrangCayMD baoCaoTinhTrangCayMD);
	public boolean xoa(String id);
	public BaoCaoTinhTrangCay timKiem(String idCay);
	public PaginationResult<BaoCaoTinhTrangCayMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<BaoCaoTinhTrangCayMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
