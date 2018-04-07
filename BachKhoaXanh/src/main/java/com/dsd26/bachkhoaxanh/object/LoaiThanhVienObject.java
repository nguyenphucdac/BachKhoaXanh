package com.dsd26.bachkhoaxanh.object;

public class LoaiThanhVienObject {
	private String idLoaiThanhVien;
	private String tenLoaiThanhVien;
	private String anhLoaiThanhVien;
	
	
	public LoaiThanhVienObject() {
		super();
	}
	public LoaiThanhVienObject(String idLoaiThanhVien, String tenLoaiThanhVien, String anhLoaiThanhVien) {
		super();
		this.idLoaiThanhVien = idLoaiThanhVien;
		this.tenLoaiThanhVien = tenLoaiThanhVien;
		this.anhLoaiThanhVien = anhLoaiThanhVien;
	}
	public String getIdLoaiThanhVien() {
		return idLoaiThanhVien;
	}
	public void setIdLoaiThanhVien(String idLoaiThanhVien) {
		this.idLoaiThanhVien = idLoaiThanhVien;
	}
	public String getTenLoaiThanhVien() {
		return tenLoaiThanhVien;
	}
	public void setTenLoaiThanhVien(String tenLoaiThanhVien) {
		this.tenLoaiThanhVien = tenLoaiThanhVien;
	}
	public String getAnhLoaiThanhVien() {
		return anhLoaiThanhVien;
	}
	public void setAnhLoaiThanhVien(String anhLoaiThanhVien) {
		this.anhLoaiThanhVien = anhLoaiThanhVien;
	}
	
	
}
