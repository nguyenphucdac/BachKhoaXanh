package com.dsd26.bachkhoaxanh.model;

import java.util.Date;

import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangDCN;;

/*
* author: VÅ© Ä�á»©c Viá»‡t
*/	

public class BaoCaoTinhTrangDCNMD {
	private String id;
	private String idThanhVien;
	private String idDiemCapNuoc;
	private String tinhTrang;
	private Date   thoiGian;
	
	public BaoCaoTinhTrangDCNMD() {
		super();
	}
	
	public BaoCaoTinhTrangDCNMD(BaoCaoTinhTrangDCN baoCaoTinhTrangDCN) {
		this.id = baoCaoTinhTrangDCN.getId();
		this.idThanhVien = baoCaoTinhTrangDCN.getIdThanhVien();
		this.idDiemCapNuoc = baoCaoTinhTrangDCN.getIdDiemCapNuoc();
		this.tinhTrang = baoCaoTinhTrangDCN.getTinhTrang();
		this.thoiGian =  baoCaoTinhTrangDCN.getThoiGian();
	}
	
	public BaoCaoTinhTrangDCNMD( String id, String idThanhVien, String idDiemCapNuoc, String tinhTrang, Date thoiGian ) {
		this.id = id;
		this.idThanhVien = idThanhVien;
		this.idDiemCapNuoc = idDiemCapNuoc;
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

	public String getIdDiemCapNuoc() {
		return idDiemCapNuoc;
	}

	public void setIdDiemCapNuoc(String idDiemCapNuoc) {
		this.idDiemCapNuoc = idDiemCapNuoc;
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
