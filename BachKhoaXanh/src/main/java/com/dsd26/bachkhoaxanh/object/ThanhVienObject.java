package com.dsd26.bachkhoaxanh.object;

import com.dsd26.bachkhoaxanh.entity.ThanhVien;

public class ThanhVienObject {
	private String idThanhVien;
	private String tenTaiKhoan;
	private String matKhau;
	private String tenDayDu;
	private int trangThai;
	private String anhThanhVien;
	private LoaiThanhVienObject loaiThanhVien;
	
	public ThanhVienObject() {
		super();
	}
	
	public ThanhVienObject(ThanhVien thanhVien, LoaiThanhVienObject loaiThanhVien) {
		super();
		this.idThanhVien = thanhVien.getIdThanhVien();
		this.tenTaiKhoan = thanhVien.getTenTaiKhoan();
		this.tenDayDu = thanhVien.getTenDayDu();
		this.trangThai = thanhVien.getTrangThai();
		this.anhThanhVien = Host.getHost() + "anh-thanh-vien?idThanhVien=" + thanhVien.getIdThanhVien();
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
	public String getAnhThanhVien() {
		return anhThanhVien;
	}
	public void setAnhThanhVien(String anhThanhVien) {
		this.anhThanhVien = anhThanhVien;
	}
	public LoaiThanhVienObject getLoaiThanhVien() {
		return loaiThanhVien;
	}
	public void setLoaiThanhVien(LoaiThanhVienObject loaiThanhVien) {
		this.loaiThanhVien = loaiThanhVien;
	}
	
	
	
	
	
}
