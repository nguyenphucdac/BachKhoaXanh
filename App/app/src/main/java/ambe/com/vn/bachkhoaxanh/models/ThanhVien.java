package ambe.com.vn.bachkhoaxanh.models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by AMBE on 18/03/2018.
 */

public class ThanhVien implements Serializable {
    private String idThanhVien;
    private String tenTaiKhoan;
    private String matKhau;
    private String tenDayDu;
    private String idLoaiThanhVien;
    private int toaDoX;
    private int toaDoY;
    private Bitmap bitmap;

    public ThanhVien(String idThanhVien, String tenTaiKhoan, String matKhau, String tenDayDu, String idLoaiThanhVien) {
        this.idThanhVien = idThanhVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenDayDu = tenDayDu;
        this.idLoaiThanhVien = idLoaiThanhVien;
    }

    public ThanhVien(String idThanhVien, String tenTaiKhoan, String matKhau, String tenDayDu, String idLoaiThanhVien, int toaDoX, int toaDoY, Bitmap bitmap) {
        this.idThanhVien = idThanhVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenDayDu = tenDayDu;
        this.idLoaiThanhVien = idLoaiThanhVien;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY;
        this.bitmap=bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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

    public String getIdThanhVien() {
        return idThanhVien;
    }

    public void setIdThanhVien(String idThanhVien) {
        this.idThanhVien = idThanhVien;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenDayDu() {
        return tenDayDu;
    }

    public void setTenDayDu(String tenDayDu) {
        this.tenDayDu = tenDayDu;
    }

    public String getIdLoaiThanhVien() {
        return idLoaiThanhVien;
    }

    public void setIdLoaiThanhVien(String idLoaiThanhVien) {
        this.idLoaiThanhVien = idLoaiThanhVien;
    }
}
