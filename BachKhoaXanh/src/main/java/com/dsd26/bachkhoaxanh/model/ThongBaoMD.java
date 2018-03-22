package com.dsd26.bachkhoaxanh.model;

import java.util.Date;

/*
* author: Nguyen Van Cong
*/


public class ThongBaoMD {
	
	private String noiDung;
	private String nguoiTao;
	private Date thoiGian;
	
	public ThongBaoMD(String noiDung, String nguoiTao, Date thoiGian) {
		super();
		this.noiDung = noiDung;
		this.nguoiTao = nguoiTao;
		this.thoiGian = thoiGian;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	
	

}
