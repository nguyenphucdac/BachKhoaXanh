package com.dsd26.bachkhoaxanh.model;

import java.util.ArrayList;

/*
* author: Nguyen Van Cong
*/


public class LichSuBaoCaoTinhTrangDiemCapNuocMD {
	
	private DiemCapNuocMD diemCapNuoc;
	private ArrayList<ChiTietBaoCaoMD> danhSachBaoCaoTinhTrangDiemCapNuoc = new ArrayList();
	public LichSuBaoCaoTinhTrangDiemCapNuocMD(DiemCapNuocMD diemCapNuoc,
			ArrayList<ChiTietBaoCaoMD> danhSachBaoCaoTinhTrangDiemCapNuoc) {
		super();
		this.diemCapNuoc = diemCapNuoc;
		this.danhSachBaoCaoTinhTrangDiemCapNuoc = danhSachBaoCaoTinhTrangDiemCapNuoc;
	}
	public DiemCapNuocMD getDiemCapNuoc() {
		return diemCapNuoc;
	}
	public void setDiemCapNuoc(DiemCapNuocMD diemCapNuoc) {
		this.diemCapNuoc = diemCapNuoc;
	}
	public ArrayList<ChiTietBaoCaoMD> getDanhSachBaoCaoTinhTrangDiemCapNuoc() {
		return danhSachBaoCaoTinhTrangDiemCapNuoc;
	}
	public void setDanhSachBaoCaoTinhTrangDiemCapNuoc(ArrayList<ChiTietBaoCaoMD> danhSachBaoCaoTinhTrangDiemCapNuoc) {
		this.danhSachBaoCaoTinhTrangDiemCapNuoc = danhSachBaoCaoTinhTrangDiemCapNuoc;
	}
	
	

}
