package com.dsd26.bachkhoaxanh.model;

/*
* author: Nguyen Van Cong
*/

public class ThanhVienTrucTuyenMD {
	
	public final static int MA_TO_DO = 5;
	
	private String tenTaiKhoan;
	private String tenDayDu;
	private int toDoX;
	private int toDoY;
	
	
	public ThanhVienTrucTuyenMD(String tenTaiKhoan, String tenDayDu, int toDoX, int toDoY) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.tenDayDu = tenDayDu;
		this.toDoX = toDoX;
		this.toDoY = toDoY;
	}
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
