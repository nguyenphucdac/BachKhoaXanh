package com.dsd26.bachkhoaxanh.model;

public class ThanhVienTrucTuyenMD {
	
	private String tenTaiKhoan;
	private String tenDayDu;
	private int toDoX;
	private int toDoY;

	
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	public String getTenDayDu() {
		return tenDayDu;
	}
	public void setTenDayDu(String tenDayDu) {
		this.tenDayDu = tenDayDu;
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
	
	public void capNhatToDo(int x, int y) {
		this.setToDoX(x);
		this.setToDoY(y);
	}
	
}
