package ambe.com.vn.bachkhoaxanh.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
    private String luongNuocCan;
    private Bitmap bitmap;

    public Cay(String idCay, String idLoaiCay, int toaDoX, int toaDoY, String tinhTrang, String luongNuocCan) {
        this.idCay = idCay;
        this.idLoaiCay = idLoaiCay;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY;
        this.tinhTrang = tinhTrang;
        this.luongNuocCan = luongNuocCan;
    }

    public Cay(Bitmap bitmap, int toaDoX, int toaDoY) {
        this.bitmap = bitmap;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY;

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

    public String getLuongNuocCan() {
        return luongNuocCan;
    }

    public void setLuongNuocCan(String luongNuocCan) {
        this.luongNuocCan = luongNuocCan;
    }
}
