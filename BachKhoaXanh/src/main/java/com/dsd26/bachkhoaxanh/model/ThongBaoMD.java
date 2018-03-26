package com.dsd26.bachkhoaxanh.model;

import java.util.Date;

import com.dsd26.bachkhoaxanh.entity.ThongBao;


/*
* author: Nguyễn Phúc Đạc
*/

public class ThongBaoMD {
	private String idThongBao;
	private String noiDung;
	private Date thoiGian;
	private String idNguoiTao;
	

	public ThongBaoMD() {
		super();
	}
	
	public ThongBaoMD(ThongBao thongBao){
		this.idThongBao = thongBao.getIdThongBao();
		this.noiDung = thongBao.getNoiDung();
		this.thoiGian = thongBao.getThoiGian();
		this.idNguoiTao = thongBao.getIdNguoiTao();
	}
	
	public ThongBaoMD(String idThongBao, String noiDung, Date thoiGian, String idNguoiTao) {
		super();
		this.idThongBao = idThongBao;
		this.noiDung = noiDung;
		this.thoiGian = thoiGian;
		this.idNguoiTao = idNguoiTao;
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
	public String getIdNguoiTao() {
		return idNguoiTao;
	}
	public void setIdNguoiTao(String idNguoiTao) {
		this.idNguoiTao = idNguoiTao;
	}
	
	
}
