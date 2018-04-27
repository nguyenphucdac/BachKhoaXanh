package ambe.com.vn.bachkhoaxanh.models;

import java.io.Serializable;

/**
 * Created by AMBE on 21/03/2018.
 */

public class LichSuTuoiCay implements Serializable {
    private String idLichSuTuoi;
    private int luongNuocDaTuoi;
    private String thoiGian;
    private Cay cayObject;
    private ThanhVien thanhVienObject;

    public LichSuTuoiCay(String idLichSuTuoi, int luongNuocDaTuoi, String thoiGian) {
        this.idLichSuTuoi = idLichSuTuoi;
        this.luongNuocDaTuoi = luongNuocDaTuoi;
        this.thoiGian = thoiGian;
    }

    public LichSuTuoiCay(String idLichSuTuoi, int luongNuocDaTuoi, String thoiGian, Cay cayObject, ThanhVien thanhVienObject) {
        this.idLichSuTuoi = idLichSuTuoi;
        this.luongNuocDaTuoi = luongNuocDaTuoi;
        this.thoiGian = thoiGian;
        this.cayObject = cayObject;
        this.thanhVienObject = thanhVienObject;
    }

    public LichSuTuoiCay() {
    }

    public String getIdLichSuTuoi() {
        return idLichSuTuoi;
    }

    public void setIdLichSuTuoi(String idLichSuTuoi) {
        this.idLichSuTuoi = idLichSuTuoi;
    }

    public int getLuongNuocDaTuoi() {
        return luongNuocDaTuoi;
    }

    public void setLuongNuocDaTuoi(int luongNuocDaTuoi) {
        this.luongNuocDaTuoi = luongNuocDaTuoi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Cay getCayObject() {
        return cayObject;
    }

    public void setCayObject(Cay cayObject) {
        this.cayObject = cayObject;
    }

    public ThanhVien getThanhVienObject() {
        return thanhVienObject;
    }

    public void setThanhVienObject(ThanhVien thanhVienObject) {
        this.thanhVienObject = thanhVienObject;
    }
}