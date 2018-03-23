package ambe.com.vn.bachkhoaxanh.models;

import java.io.Serializable;

/**
 * Created by AMBE on 21/03/2018.
 */

public class LichSuTuoiCay implements Serializable {
    private String id;
    private String idCay;
    private String luongNuoc;
    private String thoiGian;


    public LichSuTuoiCay() {
    }

    public LichSuTuoiCay(String id, String idCay, String luongNuoc, String thoiGian) {
        this.id = id;
        this.idCay = idCay;
        this.luongNuoc = luongNuoc;
        this.thoiGian = thoiGian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCay() {
        return idCay;
    }

    public void setIdCay(String idCay) {
        this.idCay = idCay;
    }

    public String getLuongNuoc() {
        return luongNuoc;
    }

    public void setLuongNuoc(String luongNuoc) {
        this.luongNuoc = luongNuoc;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
