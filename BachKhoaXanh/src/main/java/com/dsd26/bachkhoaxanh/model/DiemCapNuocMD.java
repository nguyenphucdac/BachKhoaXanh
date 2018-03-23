package com.dsd26.bachkhoaxanh.model;

/*
* author: Nguyen Van Cong
*/

public class DiemCapNuocMD
{
	
	public final static int MA_TO_DO = 3;
	
	private String idDiemCapNuoc;
	private int toaDoX;
	private int toaDoY;
	private int luongNuocToiDa;
	private String tinhTrang;
	
	
	public DiemCapNuocMD(String idDiemCapNuoc, int toaDoX, int toaDoY, int luongNuocToiDa, String tinhTrang) {
		super();
		this.idDiemCapNuoc = idDiemCapNuoc;
		this.toaDoX = toaDoX;
		this.toaDoY = toaDoY;
		this.luongNuocToiDa = luongNuocToiDa;
		this.tinhTrang = tinhTrang;
	}
	
	
	public DiemCapNuocMD() {
	}


	public String getIdDiemCapNuoc() {
		return idDiemCapNuoc;
	}
	public void setIdDiemCapNuoc(String idDiemCapNuoc) {
		this.idDiemCapNuoc = idDiemCapNuoc;
	}
	public int getToaDoX() {
		return toaDoX;
	}
	public void setToDoX(int toaDoX) {
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
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	
	

}
