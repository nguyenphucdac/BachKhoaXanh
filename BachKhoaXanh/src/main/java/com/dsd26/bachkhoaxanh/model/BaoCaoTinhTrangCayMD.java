package com.dsd26.bachkhoaxanh.model;

import java.util.Date;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangCay;

/*
* author: Vũ Đức Việt
*/	

public class BaoCaoTinhTrangCayMD {
	
	private String id;
	private String idThanhVien;
	private String idCay;
	private String tinhTrang;
	private Date   thoiGian;
	
	public BaoCaoTinhTrangCayMD() {
		super();
	}
	
	public BaoCaoTinhTrangCayMD(BaoCaoTinhTrangCay baoCaoTinhTrangCay) {
		this.id = baoCaoTinhTrangCay.getId();
		this.idThanhVien = baoCaoTinhTrangCay.getIdThanhVien();
		this.idCay = baoCaoTinhTrangCay.getIdCay();
		this.tinhTrang = baoCaoTinhTrangCay.getTinhTrang();
		this.thoiGian =  baoCaoTinhTrangCay.getThoiGian();
	}
	
	public BaoCaoTinhTrangCayMD( String id, String idThanhVien, String idCay, String tinhTrang, Date thoiGian ) {
		this.id = id;
		this.idThanhVien = idThanhVien;
		this.idCay = idCay;
		this.tinhTrang = tinhTrang;
		this.thoiGian = thoiGian;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdThanhVien() {
		return idThanhVien;
	}

	public void setIdThanhVien(String idThanhVien) {
		this.idThanhVien = idThanhVien;
	}

	public String getIdCay() {
		return idCay;
	}

	public void setIdCay(String idCay) {
		this.idCay = idCay;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	
	
	
}
