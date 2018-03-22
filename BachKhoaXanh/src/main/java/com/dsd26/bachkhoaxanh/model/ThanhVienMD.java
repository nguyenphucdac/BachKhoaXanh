package com.dsd26.bachkhoaxanh.model;

/*
* author: Nguyen Van Cong
*/


public class ThanhVienMD {
	
	private String idThanhVien;
	private String tenTaiKhoan;
	private String tenDayDu;
	private LoaiThanhVienMD loaiThanhVien;
	public ThanhVienMD(String idThanhVien, String tenTaiKhoan, String tenDayDu, LoaiThanhVienMD loaiThanhVien) {
		super();
		this.idThanhVien = idThanhVien;
		this.tenTaiKhoan = tenTaiKhoan;
		this.tenDayDu = tenDayDu;
		this.loaiThanhVien = loaiThanhVien;
	}
	public String getIdThanhVien() {
		return idThanhVien;
	}
	public void setIdThanhVien(String idThanhVien) {
		this.idThanhVien = idThanhVien;
	}
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	public String getTenDayDu() {
		return tenDayDu;
	}
	public void setTenDayDu(String tenDayDu) {
		this.tenDayDu = tenDayDu;
	}
	public LoaiThanhVienMD getLoaiThanhVien() {
		return loaiThanhVien;
	}
	public void setLoaiThanhVien(LoaiThanhVienMD loaiThanhVien) {
		this.loaiThanhVien = loaiThanhVien;
	}
	
	

}
