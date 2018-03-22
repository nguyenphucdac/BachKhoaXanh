package com.dsd26.bachkhoaxanh.model;

import com.dsd26.bachkhoaxanh.entity.LoaiThanhVien;

/*
* author: Nguyễn Phúc Đạc
*/


public class LoaiThanhVienMD {

	private String idLoaiThanhVien;
	private String tenLoaiThanhVien;
	
	
	
	public LoaiThanhVienMD() {
		super();
	}
	
	public LoaiThanhVienMD(LoaiThanhVien loaiThanhVien) {
		this.idLoaiThanhVien = loaiThanhVien.getIdLoaiThanhVien();
		this.tenLoaiThanhVien = loaiThanhVien.getTenLoaiThanhVien();
	}
	
	public LoaiThanhVienMD(String idLoaiThanhVien, String tenLoaiThanhVien) {
		super();
		this.idLoaiThanhVien = idLoaiThanhVien;
		this.tenLoaiThanhVien = tenLoaiThanhVien;
	}
	public String getIdLoaiThanhVien() {
		return idLoaiThanhVien;
	}
	public void setIdLoaiThanhVien(String idLoaiThanhVien) {
		this.idLoaiThanhVien = idLoaiThanhVien;
	}
	public String getTenLoaiThanhVien() {
		return tenLoaiThanhVien;
	}
	public void setTenLoaiThanhVien(String tenLoaiThanhVien) {
		this.tenLoaiThanhVien = tenLoaiThanhVien;
	}
		
	
}
