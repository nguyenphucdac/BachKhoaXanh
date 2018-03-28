package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangDCN;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangDCN;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangDCNMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

public interface IBaoCaoTinhTrangDCNDAO {
	public void luu(BaoCaoTinhTrangDCNMD baoCaoTinhTrangDCNMD);
	public boolean xoa(String id);
	public BaoCaoTinhTrangDCN timKiem(String idDiemCapNuoc);
	public PaginationResult<BaoCaoTinhTrangDCNMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<BaoCaoTinhTrangDCNMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
