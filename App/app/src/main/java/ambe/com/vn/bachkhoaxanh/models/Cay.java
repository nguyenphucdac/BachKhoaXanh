package ambe.com.vn.bachkhoaxanh.models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by AMBE on 18/03/2018.
 */

public class Cay implements Serializable {
    private String idCay;
    private String idLoaiCay;
    private int toaDoX;
    private int toaDoY;
    private String tinhTrang;
    private int luongNuocToiDa;
    private int luongNuocDaTuoi;
    private Bitmap bitmap;
    private LoaiCayObject loaiCayObject;

    public Cay(String idCay, String idLoaiCay, int toaDoX, int toaDoY, String tinhTrang, int luongNuocToiDa, int luongNuocDaTuoi, Bitmap bitmap, LoaiCayObject loaiCayObject) {
        this.idCay = idCay;
        this.idLoaiCay = idLoaiCay;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY ;
        this.tinhTrang = tinhTrang;
        this.luongNuocToiDa = luongNuocToiDa;
        this.luongNuocDaTuoi = luongNuocDaTuoi;
        this.bitmap = bitmap;
        this.loaiCayObject = loaiCayObject;
    }

    public LoaiCayObject getLoaiCayObject() {
        return loaiCayObject;
    }

    public void setLoaiCayObject(LoaiCayObject loaiCayObject) {
        this.loaiCayObject = loaiCayObject;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getIdCay() {
        return idCay;
    }

    public void setIdCay(String idCay) {
        this.idCay = idCay;
    }

    public String getIdLoaiCay() {
        return idLoaiCay;
    }

    public void setIdLoaiCay(String idLoaiCay) {
        this.idLoaiCay = idLoaiCay;
    }

    public int getToaDoX() {
        return toaDoX;
    }

    public void setToaDoX(int toaDoX) {
        this.toaDoX = toaDoX;
    }

    public int getToaDoY() {
        return toaDoY;
    }

    public void setToaDoY(int toaDoY) {
        this.toaDoY = toaDoY;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getLuongNuocToiDa() {
        return luongNuocToiDa;
    }

    public void setLuongNuocToiDa(int luongNuocToiDa) {
        this.luongNuocToiDa = luongNuocToiDa;
    }

    public int getLuongNuocDaTuoi() {
        return luongNuocDaTuoi;
    }

    public void setLuongNuocDaTuoi(int luongNuocDaTuoi) {
        this.luongNuocDaTuoi = luongNuocDaTuoi;
    }
}
