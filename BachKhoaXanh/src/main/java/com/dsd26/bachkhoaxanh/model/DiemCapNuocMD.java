package com.dsd26.bachkhoaxanh.model;

/*
* author: Nguyen Van Cong
*/

public class DiemCapNuocMD {
	
	public final static int MA_TO_DO = 3;
	
	private String id;
	private int toDoX;
	private int toDoY;
	private int luongNuocToiDa;
	private String tinhTrang;
	
	
	public DiemCapNuocMD(String id, int toDoX, int toDoY, int luongNuocToiDa, String tinhTrang) {
		super();
		this.id = id;
		this.toDoX = toDoX;
		this.toDoY = toDoY;
		this.luongNuocToiDa = luongNuocToiDa;
		this.tinhTrang = tinhTrang;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getToDoX() {
		return toDoX;
	}
	public void setToDoX(int toDoX) {
		this.toDoX = toDoX;
	}
	public int getToDoY() {
		return toDoY;
	}
	public void setToDoY(int toDoY) {
		this.toDoY = toDoY;
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
