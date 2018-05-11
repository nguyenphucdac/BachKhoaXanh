package ambe.com.vn.bachkhoaxanh.models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by AMBE on 18/03/2018.
 */

public class DiemCapNuoc implements Serializable {
    private String idDiemCapNuoc;
    private int toaDoX;
    private int toaDoY;
    private String luongNuocToiDa;
    private String tinhTrang;
    private Bitmap bitmap;
    private boolean chiDuongDcn;

    public boolean isChiDuongDcn() {
        return chiDuongDcn;
    }

    public void setChiDuongDcn(boolean chiDuongDcn) {
        this.chiDuongDcn = chiDuongDcn;
    }

    public DiemCapNuoc(String idDiemCapNuoc, int toaDoX, int toaDoY, String luongNuocToiDa, String tinhTrang, Bitmap bitmap) {
        this.idDiemCapNuoc = idDiemCapNuoc;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY;
        this.luongNuocToiDa = luongNuocToiDa;
        this.tinhTrang = tinhTrang;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getIdDiemCapNuoc() {
        return idDiemCapNuoc;
    }

    public void setIdDiemCapNuoc(String idDiemCapNuoc) {
        this.idDiemCapNuoc = idDiemCapNuoc;
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

    public String getLuongNuocToiDa() {
        return luongNuocToiDa;
    }

    public void setLuongNuocToiDa(String luongNuocToiDa) {
        this.luongNuocToiDa = luongNuocToiDa;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
