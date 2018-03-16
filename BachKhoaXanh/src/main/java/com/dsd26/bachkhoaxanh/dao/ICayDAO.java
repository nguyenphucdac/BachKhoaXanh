package com.dsd26.bachkhoaxanh.dao;

import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;

/*
 * author: Nguyễn Phúc Đạc
 */

public interface ICayDAO {
	public void luu(CayMD cayMD);
	public boolean xoa(String idCay);
	public Cay timKiem(String idCay);
}
