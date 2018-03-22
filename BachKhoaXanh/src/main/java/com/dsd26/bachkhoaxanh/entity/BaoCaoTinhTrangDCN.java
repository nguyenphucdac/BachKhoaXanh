package com.dsd26.bachkhoaxanh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
* author: Nguyen Van Cong
*/
@Entity
@Table(name="bao_cao_tinh_trang_dcn")

public class BaoCaoTinhTrangDCN {
	
	private String id;
	private String idThanhVien;
	private String idDiemCapNuoc;
	private String tinhTrang;
	private Date thoiGian;
	
	@Id
	@Column(name = "id", length = 20, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "id_thanh_vien", length = 20, nullable = false)
	public String getIdThanhVien() {
		return idThanhVien;
	}
	public void setIdThanhVien(String idThanhVien) {
		this.idThanhVien = idThanhVien;
	}
	
	@Column(name = "id_diem_cap_nuoc", length = 20, nullable = false)
	public String getIdDiemCapNuoc() {
		return idDiemCapNuoc;
	}
	public void setIdDiemCapNuoc(String idCay) {
		this.idDiemCapNuoc = idCay;
	}
	
	@Column(name = "tinh_trang", length = 50, nullable = false)
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	@Column(name = "thoi_gian")
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	
	
}