package com.dsd26.bachkhoaxanh.model;

import java.util.Date;

import com.dsd26.bachkhoaxanh.entity.LichSuTuoi;

/*
* author: Nguyễn Phúc Đạc
*/

public class LichSuTuoiMD {
	private String idLichSuTuoi;
	private String idCay;
	private String idThanhVien;
	private int luongNuocDaTuoi;
	private Date thoiGianTuoi;
	
	
	public LichSuTuoiMD() {
		super();
	}
	
	public LichSuTuoiMD(LichSuTuoi lichSuTuoi) {
		this.idLichSuTuoi = lichSuTuoi.getIdLichSuTuoi();
		this.idCay = lichSuTuoi.getIdCay();
		this.idThanhVien = lichSuTuoi.getIdThanhVien();
		this.luongNuocDaTuoi = lichSuTuoi.getLuongNuocDaTuoi();
		this.thoiGianTuoi = lichSuTuoi.getThoiGianTuoi();
	}
	
	public LichSuTuoiMD(String idLichSuTuoi, String idCay, String idThanhVien, int luongNuocDaTuoi, Date thoiGianTuoi) {
		super();
		this.idLichSuTuoi = idLichSuTuoi;
		this.idCay = idCay;
		this.idThanhVien = idThanhVien;
		this.luongNuocDaTuoi = luongNuocDaTuoi;
		this.thoiGianTuoi = thoiGianTuoi;
	}
	public String getIdLichSuTuoi() {
		return idLichSuTuoi;
	}
	public void setIdLichSuTuoi(String idLichSuTuoi) {
		this.idLichSuTuoi = idLichSuTuoi;
	}
	public String getIdCay() {
		return idCay;
	}
	public void setIdCay(String idCay) {
		this.idCay = idCay;
	}
	public String getIdThanhVien() {
		return idThanhVien;
	}
	public void setIdThanhVien(String idThanhVien) {
		this.idThanhVien = idThanhVien;
	}
	public int getLuongNuocDaTuoi() {
		return luongNuocDaTuoi;
	}
	public void setLuongNuocDaTuoi(int luongNuocDaTuoi) {
		this.luongNuocDaTuoi = luongNuocDaTuoi;
	}
	public Date getThoiGianTuoi() {
		return thoiGianTuoi;
	}
	public void setThoiGianTuoi(Date thoiGianTuoi) {
		this.thoiGianTuoi = thoiGianTuoi;
	}
	
	
}
