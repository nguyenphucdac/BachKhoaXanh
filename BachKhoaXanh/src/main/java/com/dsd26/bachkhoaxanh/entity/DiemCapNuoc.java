package com.dsd26.bachkhoaxanh.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
* author: Nguyen Van Cong
*/
@Entity
@Table(name="diem_cap_nuoc")

public class DiemCapNuoc {

	private String idDiemCapNuoc;
	private String toDoX;
	private String toDoY;
	private int luongNuocToiDa;
	private String tinhTrang;
	
	@Id
	@Column(name = "id_diem_cap_nuoc", length = 20, nullable = false)
	public String getIdDiemCapNuoc() {
		return idDiemCapNuoc;
	}
	public void setIdDiemCapNuoc(String idDiemCapNuoc) {
		this.idDiemCapNuoc = idDiemCapNuoc;
	}
	
	@Column(name = "to_do_x", length = 100, nullable = false)
	public String getToDoX() {
		return toDoX;
	}
	public void setToDoX(String toDoX) {
		this.toDoX = toDoX;
	}
	
	@Column(name = "to_do_y", length = 100, nullable = false)
	public String getToDoY() {
		return toDoY;
	}
	public void setToDoY(String toDoY) {
		this.toDoY = toDoY;
	}
	
	@Column(name = "luong_nuoc_toi_da", length = 5)
	public int getLuongNuocToiDa() {
		return luongNuocToiDa;
	}
	public void setLuongNuocToiDa(int luongNuocToiDa) {
		this.luongNuocToiDa = luongNuocToiDa;
	}
	
	@Column(name = "tinh_trang", length = 50, nullable = false)
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	
}
