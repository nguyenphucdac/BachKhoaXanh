package com.dsd26.bachkhoaxanh.model;

import com.dsd26.bachkhoaxanh.entity.ThanhVien;

/*
* author: Nguyễn Phúc Đạc
*/


public class ThanhVienMD {
	
	private String idThanhVien;
	private String idLoaiThanhVien;
	private String tenTaiKhoan;
	private String matKhau;
	private String tenDayDu;
	private int trangThai;
	
	public ThanhVienMD() {}
	
	public ThanhVienMD(ThanhVien thanhVien) {
		this.idThanhVien = thanhVien.getIdThanhVien();
		this.idLoaiThanhVien = thanhVien.getIdLoaiThanhVien();
		this.tenTaiKhoan = thanhVien.getTenTaiKhoan();
		this.matKhau = "";
		this.tenDayDu = thanhVien.getTenDayDu();
		this.trangThai = thanhVien.getTrangThai();
	}
	
	public ThanhVienMD(String idThanhVien, String tenTaiKhoan, String tenDayDu, int trangThai) {
		super();
		this.idThanhVien = idThanhVien;
		this.tenTaiKhoan = tenTaiKhoan;
		this.tenDayDu = tenDayDu;
		this.trangThai = trangThai;
	}

	public String getIdThanhVien() {
		return idThanhVien;
	}

	public void setIdThanhVien(String idThanhVien) {
		this.idThanhVien = idThanhVien;
	}

	public String getIdLoaiThanhVien() {
		return idLoaiThanhVien;
	}

	public void setIdLoaiThanhVien(String idLoaiThanhVien) {
		this.idLoaiThanhVien = idLoaiThanhVien;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTenDayDu() {
		return tenDayDu;
	}

	public void setTenDayDu(String tenDayDu) {
		this.tenDayDu = tenDayDu;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	

}
