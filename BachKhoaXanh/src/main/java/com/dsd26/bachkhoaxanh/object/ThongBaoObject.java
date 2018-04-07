package com.dsd26.bachkhoaxanh.object;

import java.util.Date;

public class ThongBaoObject {
	private String idThongBao;
	private String noiDung;
	private Date thoiGian;
	
	public ThongBaoObject() {
		super();
	}
	public ThongBaoObject(String idThongBao, String noiDung, Date thoiGian) {
		super();
		this.idThongBao = idThongBao;
		this.noiDung = noiDung;
		this.thoiGian = thoiGian;
	}
	public String getIdThongBao() {
		return idThongBao;
	}
	public void setIdThongBao(String idThongBao) {
		this.idThongBao = idThongBao;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	
	
}
