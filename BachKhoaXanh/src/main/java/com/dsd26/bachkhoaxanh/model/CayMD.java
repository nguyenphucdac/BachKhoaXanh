package com.dsd26.bachkhoaxanh.model;

import com.dsd26.bachkhoaxanh.entity.Cay;

/*
 * author: Nguyễn Phúc Đạc
 */

public class CayMD {
	private String idCay;
	private String idLoaiCay;
	private int toaDoX;
	private int toaDoY;
	private int luongNuocCan;
	private String tinhTrang;
	
	public CayMD() {}
	
	public CayMD(Cay cay) {
		this.idCay = cay.getIdCay();
		this.idLoaiCay = cay.getIdLoaiCay();
		this.toaDoX = cay.getToaDoX();
		this.toaDoY = cay.getToaDoY();
		this.luongNuocCan = cay.getLuongNuocCan();
		this.tinhTrang = cay.getTinhTrang();
	}
	
	public CayMD(String idCay, String idLoaiCay, int toaDoX, int toaDoY, int luongNuocCan, String tinhTrang) {
		this.idCay = idCay;
		this.idLoaiCay = idLoaiCay;
		this.toaDoX = toaDoX;
		this.toaDoY = toaDoY;
		this.luongNuocCan = luongNuocCan;
		this.tinhTrang = tinhTrang;
	}
	
	public String getIdCay() {
		return idCay;
	}
	public void setIdCay(String idCay) {
		this.idCay = idCay;
	}
	public String getIdLoaiCay() {
		return idLoaiCay;
	}
	public void setIdLoaiCay(String idLoaiCay) {
		this.idLoaiCay = idLoaiCay;
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
	public int getLuongNuocCan() {
		return luongNuocCan;
	}
	public void setLuongNuocCan(int luongNuocCan) {
		this.luongNuocCan = luongNuocCan;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	
}