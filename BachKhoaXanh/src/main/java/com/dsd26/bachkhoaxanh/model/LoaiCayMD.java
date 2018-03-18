package com.dsd26.bachkhoaxanh.model;

/*
 * author: Nguyễn Phúc Đạc
 */

public class LoaiCayMD {
	private String idLoaiCay;
	private String tenLoaiCay;
	
	public LoaiCayMD() {
		super();
	}
	public LoaiCayMD(String idLoaiCay, String tenLoaiCay) {
		super();
		this.idLoaiCay = idLoaiCay;
		this.tenLoaiCay = tenLoaiCay;
	}
	
	public String getIdLoaiCay() {
		return idLoaiCay;
	}
	public void setIdLoaiCay(String idLoaiCay) {
		this.idLoaiCay = idLoaiCay;
	}
	public String getTenLoaiCay() {
		return tenLoaiCay;
	}
	public void setTenLoaiCay(String tenLoaiCay) {
		this.tenLoaiCay = tenLoaiCay;
	}
	
	
}
