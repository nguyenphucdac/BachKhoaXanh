package com.dsd26.bachkhoaxanh.model;

/*
* author: Nguyen Van Cong
*/


import java.util.ArrayList;

public class LichSuBaoCaoTinhTrangCayMD {
	
	private CayMD cayMD;
	private ArrayList<ChiTietBaoCaoMD> danhSachBaoCaoTinhTrangCay = new ArrayList();
	public LichSuBaoCaoTinhTrangCayMD(CayMD cayMD, ArrayList<ChiTietBaoCaoMD> danhSachBaoCaoTinhTrangCay) {
		super();
		this.cayMD = cayMD;
		this.danhSachBaoCaoTinhTrangCay = danhSachBaoCaoTinhTrangCay;
	}
	public CayMD getCayMD() {
		return cayMD;
	}
	public void setCayMD(CayMD cayMD) {
		this.cayMD = cayMD;
	}
	public ArrayList<ChiTietBaoCaoMD> getDanhSachBaoCaoTinhTrangCay() {
		return danhSachBaoCaoTinhTrangCay;
	}
	public void setDanhSachBaoCaoTinhTrangCay(ArrayList<ChiTietBaoCaoMD> danhSachBaoCaoTinhTrangCay) {
		this.danhSachBaoCaoTinhTrangCay = danhSachBaoCaoTinhTrangCay;
	}
	
	public void themBaoCaoTinhTrangCay(ChiTietBaoCaoMD ctbc) {
		this.danhSachBaoCaoTinhTrangCay.add(ctbc);
	}
}
