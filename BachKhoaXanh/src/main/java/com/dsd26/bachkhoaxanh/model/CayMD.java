package com.dsd26.bachkhoaxanh.model;

import com.dsd26.bachkhoaxanh.entity.Cay;
/*
 * author: Nguyễn Phúc Đạc
 */

public class CayMD {
	private String idCay;
	private String tenCay;
	private String idLoaiCay;
	private int toaDoX;
	private int toaDoY;
	private int luongNuocToiDa;
	private int luongNuocDaTuoi;
	private String tinhTrang;
	
	public CayMD() {}
	
	public CayMD(Cay cay) {
		this.idCay = cay.getIdCay();
		this.tenCay = cay.getTenCay();
		this.idLoaiCay = cay.getIdLoaiCay();
		this.toaDoX = cay.getToaDoX();
		this.toaDoY = cay.getToaDoY();
		this.luongNuocToiDa = cay.getLuongNuocToiDa();
		this.luongNuocDaTuoi = cay.getLuongNuocDaTuoi();
		this.tinhTrang = cay.getTinhTrang();
	}
	
	public CayMD(String idCay,String tenCay, String idLoaiCay, int toaDoX, int toaDoY, int luongNuocToiDa, int luongNuocDaTuoi, String tinhTrang) {
		this.idCay = idCay;
		this.tenCay = tenCay;
		this.idLoaiCay = idLoaiCay;
		this.toaDoX = toaDoX;
		this.toaDoY = toaDoY;
		this.luongNuocToiDa = luongNuocToiDa;
		this.luongNuocDaTuoi = luongNuocDaTuoi;
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

	public int getLuongNuocToiDa() {
		return luongNuocToiDa;
	}

	public void setLuongNuocToiDa(int luongNuocToiDa) {
		this.luongNuocToiDa = luongNuocToiDa;
	}

	public int getLuongNuocDaTuoi() {
		return luongNuocDaTuoi;
	}

	public void setLuongNuocDaTuoi(int luongNuocDaTuoi) {
		this.luongNuocDaTuoi = luongNuocDaTuoi;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	public String getTenCay() {
		return tenCay;
	}
	public void setTenCay(String tenCay) {
		this.tenCay = tenCay;
	}
	
	
}
