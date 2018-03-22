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
@Table(name="thong_bao")

public class ThongBao {

	private String idThongBao;
	private String noiDung;
	private Date thoiGian;
	private String nguoiTao;
	
	
	@Id
	@Column(name = "id_thong_bao", length = 30, nullable = false)
	public String getIdThongBao() {
		return idThongBao;
	}
	public void setIdThongBao(String idThongBao) {
		this.idThongBao = idThongBao;
	}
	
	@Column(name = "noi_dung", length = 200)
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	
	@Column(name = "thoi_gian")
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	
	@Column(name = "nguoi_tao", length = 20, nullable = false)
	public String getNguoiTao() {
		return nguoiTao;
	}
	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}
	
	
}
