package com.dsd26.bachkhoaxanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
* author: Nguyen Van Cong
*/
@Entity
@Table(name="thanh_vien")

public class ThanhVien {
	
	private String idThanhVien;
	private String tenTaiKhoan;
	private String matKhau;
	private String tenDayDu;
	private String idLoaiThanhVien;
	private String trangThai;
	private int toaDoX;
	private int toaDoY;
	private byte[] anhThanhVien;
	private int luongNuocMangTheo;
	
	@Id
	@Column(name = "id_thanh_vien", length = 20, nullable = false)
	public String getIdThanhVien() {
		return idThanhVien;
	}
	public void setIdThanhVien(String idThanhVien) {
		this.idThanhVien = idThanhVien;
	}
	
	
	@Column(name = "ten_tai_khoan", length = 30, nullable = false)
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	
	
	@Column(name = "mat_khau", length = 200, nullable = false)
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	
	@Column(name = "ten_day_du", length = 50, nullable = false)
	public String getTenDayDu() {
		return tenDayDu;
	}
	public void setTenDayDu(String tenDayDu) {
		this.tenDayDu = tenDayDu;
	}
	
	
	@Column(name = "id_loai_thanh_vien", length = 10, nullable = false)
	public String getIdLoaiThanhVien() {
		return idLoaiThanhVien;
	}
	public void setIdLoaiThanhVien(String idLoaiThanhVien) {
		this.idLoaiThanhVien = idLoaiThanhVien;
	}
	
	@Column(name = "trang_thai", length = 2, nullable = false)
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String String) {
		this.trangThai = String;
	}
	
	
	@Column(name = "toa_do_x", length = 5, nullable = true)
	public int getToaDoX() {
		return toaDoX;
	}
	public void setToaDoX(int toaDoX) {
		this.toaDoX = toaDoX;
	}
	
	@Column(name = "toa_do_y", length = 5, nullable = true)
	public int getToaDoY() {
		return toaDoY;
	}
	public void setToaDoY(int toaDoY) {
		this.toaDoY = toaDoY;
	}
	
	@Column(name = "anh_thanh_vien", length = Integer.MAX_VALUE, nullable = true)
	public byte[] getAnhThanhVien() {
		return anhThanhVien;
	}
	public void setAnhThanhVien(byte[] anhThanhVien) {
		this.anhThanhVien = anhThanhVien;
	}
	
	@Column(name = "luong_Nuoc_Mang_Theo", length = 20, nullable = true)
	public int getLuongNuocMangTheo() {
		return luongNuocMangTheo;
	}
	public void setLuongNuocMangTheo(int luongNuocMangTheo) {
		this.luongNuocMangTheo = luongNuocMangTheo;
	}
	
	
}
