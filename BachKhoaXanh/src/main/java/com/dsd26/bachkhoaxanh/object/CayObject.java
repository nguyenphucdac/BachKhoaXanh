package com.dsd26.bachkhoaxanh.object;

import com.dsd26.bachkhoaxanh.model.CayMD;

public class CayObject {
	private String idCay;
	private int toaDoX;
	private int toaDoY;
	private int luongNuocToiDa;
	private int luongNuocDaTuoi;
	private String tinhTrang;
	private LoaiCayObject loaiCayObject;

	public CayObject() {}
	
	public CayObject(CayMD cayMD, LoaiCayObject loaiCayObject) {
		this.idCay = cayMD.getIdCay();
		this.toaDoX = cayMD.getToaDoX();
		this.toaDoY = cayMD.getToaDoY();
		this.luongNuocToiDa = cayMD.getLuongNuocToiDa();
		this.luongNuocDaTuoi = cayMD.getLuongNuocDaTuoi();
		this.tinhTrang = cayMD.getTinhTrang();
		
		this.loaiCayObject = loaiCayObject;
	}
	

	public String getIdCay() {
		return idCay;
	}

	public void setIdCay(String idCay) {
		this.idCay = idCay;
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

	public LoaiCayObject getLoaiCayObject() {
		return loaiCayObject;
	}

	public void setLoaiCayObject(LoaiCayObject loaiCayObject) {
		this.loaiCayObject = loaiCayObject;
	}

	
	
	
}
