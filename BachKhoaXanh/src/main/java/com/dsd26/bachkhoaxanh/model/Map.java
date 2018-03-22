package com.dsd26.bachkhoaxanh.model;

/*
* author: Nguyen Van Cong
*/

import java.util.ArrayList;

public class Map {
	
	private int[][] toDo;
	private ArrayList<CayMD> danhSachCay = new ArrayList();
	private ArrayList<DiemCapNuocMD> danhSachDiemCapNuoc = new ArrayList();
	private ArrayList<ThanhVienTrucTuyenMD> danhSachThanhVienTrucTuyen = new ArrayList();
	public int[][] getToDo() {
		return toDo;
	}
	public void setToDo(int[][] toDo) {
		this.toDo = toDo;
	}
	public ArrayList<CayMD> getDanhSachCay() {
		return danhSachCay;
	}
	public void setDanhSachCay(ArrayList<CayMD> danhSachCay) {
		this.danhSachCay = danhSachCay;
	}
	public ArrayList<ThanhVienTrucTuyenMD> getDanhSachThanhVienTrucTuyen() {
		return danhSachThanhVienTrucTuyen;
	}
	public void setDanhSachThanhVienTrucTuyen(ArrayList<ThanhVienTrucTuyenMD> danhSachThanhVienTrucTuyen) {
		this.danhSachThanhVienTrucTuyen = danhSachThanhVienTrucTuyen;
	}
	
	public void capNhatToDo(int x, int y, int giaTri) {
		this.toDo[x][y] = giaTri;
	}
	
	public void themCay(CayMD cayMD) {
		this.danhSachCay.add(cayMD);
		this.toDo[cayMD.getToaDoX()][cayMD.getToaDoY()] = CayMD.MA_TO_DO;
	}
	
	public boolean xoaCay (CayMD cayMD) {
		return this.danhSachCay.remove(cayMD);
	}
	
	public void themThanhVienTrucTuyen(ThanhVienTrucTuyenMD tvttMD) {
		this.toDo[tvttMD.getToDoX()][tvttMD.getToDoY()] = ThanhVienTrucTuyenMD.MA_TO_DO;
		this.danhSachThanhVienTrucTuyen.add(tvttMD);
	}
	
	public boolean xoaThanhVienTrucTuyenMD(ThanhVienTrucTuyenMD tvttMD) {
		return this.danhSachThanhVienTrucTuyen.remove(tvttMD);
	}
	
	public void themDiemCapNuoc(DiemCapNuocMD dcnMD) {
		this.toDo[dcnMD.getToDoX()][dcnMD.getToDoY()] = DiemCapNuocMD.MA_TO_DO;
		this.danhSachDiemCapNuoc.add(dcnMD);
	}
	public boolean xoaDiemCapNuoc(DiemCapNuocMD dcnMD) {
		return this.danhSachDiemCapNuoc.remove(dcnMD);
	}
}
