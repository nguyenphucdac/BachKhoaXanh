package com.dsd26.bachkhoaxanh.object;

import com.dsd26.bachkhoaxanh.entity.LoaiThanhVien;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;

public class LoaiThanhVienObject {
	private String idLoaiThanhVien;
	private String tenLoaiThanhVien;
	private String anhLoaiThanhVien;
	
	public LoaiThanhVienObject() {
		super();
	}
	
	public LoaiThanhVienObject(LoaiThanhVien loaiThanhVien) {
		this.idLoaiThanhVien = loaiThanhVien.getIdLoaiThanhVien();
		this.tenLoaiThanhVien = loaiThanhVien.getTenLoaiThanhVien();
		this.anhLoaiThanhVien = Host.getHost() + "get-anh-loai-thanh-vien?idLoaiThanhVien=" + loaiThanhVien.getIdLoaiThanhVien();
	}
	
	public LoaiThanhVienObject(LoaiThanhVienMD loaiThanhVienMD) {
		super();
		this.idLoaiThanhVien = loaiThanhVienMD.getIdLoaiThanhVien();
		this.tenLoaiThanhVien = loaiThanhVienMD.getTenLoaiThanhVien();
		this.anhLoaiThanhVien = Host.getHost() + "get-anh-loai-thanh-vien?idLoaiThanhVien=" + loaiThanhVienMD.getIdLoaiThanhVien();
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
