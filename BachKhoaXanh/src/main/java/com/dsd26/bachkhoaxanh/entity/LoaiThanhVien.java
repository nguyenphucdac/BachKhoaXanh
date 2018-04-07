package com.dsd26.bachkhoaxanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


/*
* author: Nguyen Van Cong
*/
@Entity
@Table(name="loai_thanh_vien")

public class LoaiThanhVien {
	
	private String idLoaiThanhVien;
	private String tenLoaiThanhVien;
	private byte[] anhLoaiThanhVien;
	
	@Id
	@Column(name = "id_loai_thanh_vien", length = 10, nullable = false)
	public String getIdLoaiThanhVien() {
		return idLoaiThanhVien;
	}
	public void setIdLoaiThanhVien(String idLoaiThanhVien) {
		this.idLoaiThanhVien = idLoaiThanhVien;
	}
	
	@Column(name = "ten_loai_thanh_vien", length = 50, nullable = false)
	public String getTenLoaiThanhVien() {
		return tenLoaiThanhVien;
	}
	public void setTenLoaiThanhVien(String tenLoaiThanhVien) {
		this.tenLoaiThanhVien = tenLoaiThanhVien;
	}
	
	@Lob
    @Column(name = "anh_loai_thanh_vien", length = Integer.MAX_VALUE, nullable = true)
	public byte[] getAnhLoaiThanhVien() {
		return anhLoaiThanhVien;
	}
	public void setAnhLoaiThanhVien(byte[] anhLoaiThanhVien) {
		this.anhLoaiThanhVien = anhLoaiThanhVien;
	}
	
	
	
}
