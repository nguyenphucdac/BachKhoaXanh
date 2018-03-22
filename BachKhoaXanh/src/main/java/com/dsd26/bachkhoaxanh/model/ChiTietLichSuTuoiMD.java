package com.dsd26.bachkhoaxanh.model;

import java.util.Date;

/*
* author: Nguyen Van Cong
*/


public class ChiTietLichSuTuoiMD {
	
	private CayMD cayMD;
	private Date thoi_gian;
	private int luong_nuoc_da_tuoi;
	public ChiTietLichSuTuoiMD(CayMD cayMD, Date thoi_gian, int luong_nuoc_da_tuoi) {
		super();
		this.cayMD = cayMD;
		this.thoi_gian = thoi_gian;
		this.luong_nuoc_da_tuoi = luong_nuoc_da_tuoi;
	}
	public CayMD getCayMD() {
		return cayMD;
	}
	public void setCayMD(CayMD cayMD) {
		this.cayMD = cayMD;
	}
	public Date getThoi_gian() {
		return thoi_gian;
	}
	public void setThoi_gian(Date thoi_gian) {
		this.thoi_gian = thoi_gian;
	}
	public int getLuong_nuoc_da_tuoi() {
		return luong_nuoc_da_tuoi;
	}
	public void setLuong_nuoc_da_tuoi(int luong_nuoc_da_tuoi) {
		this.luong_nuoc_da_tuoi = luong_nuoc_da_tuoi;
	}
	
	

}
