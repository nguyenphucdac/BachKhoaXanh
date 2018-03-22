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
@Table(name="lich_su_tuoi")

public class LichSuTuoi {
	
	private String idLichSuTuoi;
	private String idCay;
	private String idThanhVien;
	private int luongNuocDaTuoi;
	private Date thoiGianTuoi;
	
	@Id
	@Column(name = "id_lst", length = 20, nullable = false)
	public String getIdLichSuTuoi() {
		return idLichSuTuoi;
	}
	public void setIdLichSuTuoi(String idLichSuTuoi) {
		this.idLichSuTuoi = idLichSuTuoi;
	}
	
	
	@Column(name = "id_cay", length = 20, nullable = false)
	public String getIdCay() {
		return idCay;
	}
	public void setIdCay(String idCay) {
		this.idCay = idCay;
	}
	
	
	@Column(name = "id_thanh_vien", length = 20, nullable = false)
	public String getIdThanhVien() {
		return idThanhVien;
	}
	public void setIdThanhVien(String idThanhVien) {
		this.idThanhVien = idThanhVien;
	}
	
	
	@Column(name = "luong_nuoc_da_tuoi", length = 5, nullable = false)
	public int getLuongNuocDaTuoi() {
		return luongNuocDaTuoi;
	}
	public void setLuongNuocDaTuoi(int luongNuocDaTuoi) {
		this.luongNuocDaTuoi = luongNuocDaTuoi;
	}
	
	
	@Column(name = "thoi_gian")
	public Date getThoiGianTuoi() {
		return thoiGianTuoi;
	}
	public void setThoiGianTuoi(Date thoiGianTuoi) {
		this.thoiGianTuoi = thoiGianTuoi;
	}
	
	
	
}
