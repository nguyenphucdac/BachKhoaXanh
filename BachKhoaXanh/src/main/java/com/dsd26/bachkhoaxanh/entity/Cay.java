package com.dsd26.bachkhoaxanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * author: Nguyễn Phúc Đạc
 */

@Entity
@Table(name="Cay")
public class Cay {
	private String idCay;
	private String tenCay;
	private String idLoaiCay;
	private int toaDoX;
	private int toaDoY;
	private int luongNuocToiDa;
	private int luongNuocDaTuoi;
	private String tinhTrang;
	
	@Id
	@Column(name = "id_cay", length = 20, nullable = false)
	public String getIdCay() {
		return idCay;
	}
	public void setIdCay(String idCay) {
		this.idCay = idCay;
	}
	
	@Column(name = "id_loai_cay", length = 20, nullable = false)
	public String getIdLoaiCay() {
		return idLoaiCay;
	}
	public void setIdLoaiCay(String idLoaiCay) {
		this.idLoaiCay = idLoaiCay;
	}
	@Column(name = "toa_do_x", length = 20, nullable = false)
	public int getToaDoX() {
		return toaDoX;
	}
	public void setToaDoX(int toaDoX) {
		this.toaDoX = toaDoX;
	}
	
	@Column(name = "toa_do_y", length = 20, nullable = false)
	public int getToaDoY() {
		return toaDoY;
	}
	public void setToaDoY(int toaDoY) {
		this.toaDoY = toaDoY;
	}
	
	@Column(name = "luong_nuoc_toi_da", length = 20, nullable = false)
	public int getLuongNuocToiDa() {
		return luongNuocToiDa;
	}
	public void setLuongNuocToiDa(int luongNuocToiDa) {
		this.luongNuocToiDa = luongNuocToiDa;
	}
	
	@Column(name = "luong_nuoc_da_tuoi", length = 20, nullable = false)
	public int getLuongNuocDaTuoi() {
		return luongNuocDaTuoi;
	}
	public void setLuongNuocDaTuoi(int luongNuocDaTuoi) {
		this.luongNuocDaTuoi = luongNuocDaTuoi;
	}
	
	@Column(name = "tinh_trang", length = 50, nullable = false)
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	@Column(name = "ten_cay", length = 30, nullable = false)
	public String getTenCay() {
		return tenCay;
	}
	public void setTenCay(String tenCay) {
		this.tenCay = tenCay;
	}
	
	
	
	
}
