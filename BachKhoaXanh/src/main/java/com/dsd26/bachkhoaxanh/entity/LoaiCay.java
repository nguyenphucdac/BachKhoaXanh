package com.dsd26.bachkhoaxanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


/*
 * author: Nguyễn Phúc Đạc
 */

@Entity
@Table(name="Loai_Cay")
public class LoaiCay {
	private String idLoaiCay;
	private String tenLoaiCay;
	private byte[] anhLoaiCay;
	
	@Id
	@Column(name = "id_loai_cay", length = 20, nullable = false)
	public String getIdLoaiCay() {
		return idLoaiCay;
	}
	
	public void setIdLoaiCay(String idLoaiCay) {
		this.idLoaiCay = idLoaiCay;
	}
	
	@Column(name = "ten_loai_cay", length = 20, nullable = false)
	public String getTenLoaiCay() {
		return tenLoaiCay;
	}
	public void setTenLoaiCay(String tenLoaiCay) {
		this.tenLoaiCay = tenLoaiCay;
	}

	@Lob
    @Column(name = "anh_loai_cay", length = Integer.MAX_VALUE, nullable = true)
	public byte[] getAnhLoaiCay() {
		return anhLoaiCay;
	}

	public void setAnhLoaiCay(byte[] anhLoaiCay) {
		this.anhLoaiCay = anhLoaiCay;
	}
	
	
	
}
