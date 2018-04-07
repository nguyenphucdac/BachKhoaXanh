package com.dsd26.bachkhoaxanh.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dsd26.bachkhoaxanh.entity.LoaiCay;

/*
 * author: Nguyễn Phúc Đạc
 */

public class LoaiCayMD {
	private String idLoaiCay;
	private String tenLoaiCay;
	private CommonsMultipartFile anhLoaiCay;
	
	public LoaiCayMD() {
		super();
	}
	
	public LoaiCayMD(LoaiCay loaiCay) {
		this.idLoaiCay = loaiCay.getIdLoaiCay();
		this.tenLoaiCay = loaiCay.getTenLoaiCay();
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

	public CommonsMultipartFile getAnhLoaiCay() {
		return anhLoaiCay;
	}

	public void setAnhLoaiCay(CommonsMultipartFile anhLoaiCay) {
		this.anhLoaiCay = anhLoaiCay;
	}
	
	
	
}
