package com.dsd26.bachkhoaxanh.object;

import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;

public class ThanhVienObject {
	private String idThanhVien;
	private String tenTaiKhoan;
	private String matKhau;
	private String tenDayDu;
	private String trangThai;
	private int toaDoX;
	private int toaDoY;
	private String anhThanhVien;
	private LoaiThanhVienObject loaiThanhVienObject;
	
	public ThanhVienObject() {
		super();
	}
	
	public ThanhVienObject(ThanhVien thanhVien, LoaiThanhVienObject loaiThanhVienObject) {
		super();
		this.idThanhVien = thanhVien.getIdThanhVien();
		this.tenTaiKhoan = thanhVien.getTenTaiKhoan();
		this.tenDayDu = thanhVien.getTenDayDu();
		this.trangThai = thanhVien.getTrangThai();
		this.toaDoX = thanhVien.getToaDoX();
		this.toaDoY = thanhVien.getToaDoY();
		this.anhThanhVien = "/get-anh-thanh-vien?idThanhVien=" + thanhVien.getIdThanhVien();
		this.loaiThanhVienObject = loaiThanhVienObject;
	}
	
	public ThanhVienObject(ThanhVienMD thanhVienMD, LoaiThanhVienObject loaiThanhVienObject) {
		super();
		this.idThanhVien = thanhVienMD.getIdThanhVien();
		this.tenTaiKhoan = thanhVienMD.getTenTaiKhoan();
		this.tenDayDu = thanhVienMD.getTenDayDu();
		this.trangThai = thanhVienMD.getTrangThai();
		this.toaDoX = thanhVienMD.getToaDoX();
		this.toaDoY = thanhVienMD.getToaDoY();
		this.anhThanhVien = "/get-anh-thanh-vien?idThanhVien=" + thanhVienMD.getIdThanhVien();
		this.loaiThanhVienObject = loaiThanhVienObject;
	}
	
	
	public int getToaDoX() {
		return toaDoX;
	}

	public void setToaDoX(int toaDoX) {
		this.toaDoX = toaDoX;
	}

	public int getToaDoY() {
		return toaDoY;
	}

	public void setToaDoY(int toaDoY) {
		this.toaDoY = toaDoY;
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
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getAnhThanhVien() {
		return anhThanhVien;
	}
	public void setAnhThanhVien(String anhThanhVien) {
		this.anhThanhVien = anhThanhVien;
	}
	public LoaiThanhVienObject getLoaiThanhVien() {
		return loaiThanhVienObject;
	}
	public void setLoaiThanhVien(LoaiThanhVienObject loaiThanhVienObject) {
		this.loaiThanhVienObject = loaiThanhVienObject;
	}
	
	
	
	
	
}
