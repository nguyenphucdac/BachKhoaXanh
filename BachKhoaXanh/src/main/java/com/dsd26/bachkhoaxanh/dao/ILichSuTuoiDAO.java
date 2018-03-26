package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.LichSuTuoi;
import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

public interface ILichSuTuoiDAO {
	public void luu(LichSuTuoiMD lichSuTuoiMD);
	public boolean xoa(String idLichSuTuoi);
	public LichSuTuoi timKiem(String idLichSuTuoi);
	public PaginationResult<LichSuTuoiMD> queryRoles(int page, int maxResult, int maxNavigationPage);
	public PaginationResult<LichSuTuoiMD> queryRoles(int page, int maxResult, int maxNavigationPage, String likeName);
}
