package com.dsd26.bachkhoaxanh.model;

import java.util.Date;

/*
* author: Nguyen Van Cong
*/


public class ChiTietBaoCaoMD {
	private Date thoi_gian;
	private String tinhTrang;
	private ThanhVienMD thanhVien;
	public ChiTietBaoCaoMD(Date thoi_gian, String tinhTrang, ThanhVienMD thanhVien) {
		super();
		this.thoi_gian = thoi_gian;
		this.tinhTrang = tinhTrang;
		this.thanhVien = thanhVien;
	}
	public Date getThoi_gian() {
		return thoi_gian;
	}
	public void setThoi_gian(Date thoi_gian) {
		this.thoi_gian = thoi_gian;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public ThanhVienMD getThanhVien() {
		return thanhVien;
	}
	public void setThanhVien(ThanhVienMD thanhVien) {
		this.thanhVien = thanhVien;
	}

	
}
