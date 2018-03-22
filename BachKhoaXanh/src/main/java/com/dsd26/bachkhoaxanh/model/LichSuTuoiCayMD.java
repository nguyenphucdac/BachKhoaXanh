package com.dsd26.bachkhoaxanh.model;

import java.util.ArrayList;

/*
* author: Nguyen Van Cong
*/


public class LichSuTuoiCayMD {
	
	private ThanhVienMD thanhVien;
	private ArrayList<ChiTietLichSuTuoiMD> danhSachLichSuTuoi = new ArrayList();
	public LichSuTuoiCayMD(ThanhVienMD thanhVien, ArrayList<ChiTietLichSuTuoiMD> danhSachLichSuTuoi) {
		super();
		this.thanhVien = thanhVien;
		this.danhSachLichSuTuoi = danhSachLichSuTuoi;
	}
	public ThanhVienMD getThanhVien() {
		return thanhVien;
	}
	public void setThanhVien(ThanhVienMD thanhVien) {
		this.thanhVien = thanhVien;
	}
	public ArrayList<ChiTietLichSuTuoiMD> getDanhSachLichSuTuoi() {
		return danhSachLichSuTuoi;
	}
	public void setDanhSachLichSuTuoi(ArrayList<ChiTietLichSuTuoiMD> danhSachLichSuTuoi) {
		this.danhSachLichSuTuoi = danhSachLichSuTuoi;
	}
	
	

}
