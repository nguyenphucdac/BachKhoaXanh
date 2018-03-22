package com.dsd26.bachkhoaxanh.model;

import com.dsd26.bachkhoaxanh.entity.ThanhVien;

/*
* author: Nguyễn Phúc Đạc
*/


public class ThanhVienMD {
	
	private String idThanhVien;
	private String tenTaiKhoan;
	private String matKhau;
	private String tenDayDu;
	private LoaiThanhVienMD loaiThanhVienMD;
	
	public ThanhVienMD() {}
	
	public ThanhVienMD(ThanhVien thanhVien) {
		this.idThanhVien = thanhVien.getIdThanhVien();
		this.tenTaiKhoan = thanhVien.getTenTaiKhoan();
		this.tenDayDu = thanhVien.getTenDayDu();
		this.loaiThanhVienMD.setIdLoaiThanhVien(thanhVien.getIdLoaiThanhVien());
	}
	
	public ThanhVienMD(String idThanhVien, String tenTaiKhoan, String tenDayDu) {
		super();
		this.idThanhVien = idThanhVien;
		this.tenTaiKhoan = tenTaiKhoan;
		this.tenDayDu = tenDayDu;
		
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
	public LoaiThanhVienMD getLoaiThanhVienMD() {
		return loaiThanhVienMD;
	}
	public void setLoaiThanhVienMD(LoaiThanhVienMD loaiThanhVienMD) {
		this.loaiThanhVienMD = loaiThanhVienMD;
	}
	
	

}
