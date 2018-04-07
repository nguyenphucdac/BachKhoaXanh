package com.dsd26.bachkhoaxanh.object;

import java.util.Date;

import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;

public class LichSuTuoiObject {
	private String idLichSuTuoi;
	private int luongNuocDaTuoi;
	private Date thoiGian;
	private CayObject cayObject;
	private ThanhVienObject thanhVienObject;
	
	
	public LichSuTuoiObject() {
		super();
	}
	
	public LichSuTuoiObject(LichSuTuoiMD lichSuTuoiMD ,CayObject cayObject, ThanhVienObject thanhVienObject) {
		this.idLichSuTuoi = lichSuTuoiMD.getIdLichSuTuoi();
		this.luongNuocDaTuoi = lichSuTuoiMD.getLuongNuocDaTuoi();
		this.thoiGian = lichSuTuoiMD.getThoiGian();
		
		this.cayObject = cayObject;
		this.thanhVienObject = thanhVienObject;
	}
	
	
	public String getIdLichSuTuoi() {
		return idLichSuTuoi;
	}
	public void setIdLichSuTuoi(String idLichSuTuoi) {
		this.idLichSuTuoi = idLichSuTuoi;
	}
	public int getLuongNuocDaTuoi() {
		return luongNuocDaTuoi;
	}
	public void setLuongNuocDaTuoi(int luongNuocDaTuoi) {
		this.luongNuocDaTuoi = luongNuocDaTuoi;
	}
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	public CayObject getCayObject() {
		return cayObject;
	}

	public void setCayObject(CayObject cayObject) {
		this.cayObject = cayObject;
	}

	public ThanhVienObject getThanhVienObject() {
		return thanhVienObject;
	}

	public void setThanhVienObject(ThanhVienObject thanhVienObject) {
		this.thanhVienObject = thanhVienObject;
	}
	
	
}
