package com.dsd26.bachkhoaxanh.object;

import com.dsd26.bachkhoaxanh.entity.LoaiCay;

public class LoaiCayObject {
	private String idLoaiCay;
	private String tenLoaiCay;
	private String anhLoaiCay;
	
	public LoaiCayObject(LoaiCay loaiCay) {
		this.idLoaiCay = loaiCay.getIdLoaiCay();
		this.tenLoaiCay = loaiCay.getTenLoaiCay();
	}
	
	public LoaiCayObject() {
		super();
	}
	public LoaiCayObject(String idLoaiCay, String tenLoaiCay, String anhLoaiCay) {
		super();
		this.idLoaiCay = idLoaiCay;
		this.tenLoaiCay = tenLoaiCay;
		this.anhLoaiCay = anhLoaiCay;
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
	public String getAnhLoaiCay() {
		return anhLoaiCay;
	}
	public void setAnhLoaiCay(String anhLoaiCay) {
		this.anhLoaiCay = anhLoaiCay;
	}
	
	
}
