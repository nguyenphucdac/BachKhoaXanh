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
    private String trangThai;
    private int toaDoX;
    private int toaDoY;
    private String anhThanhVien;
    private int luongNuocMangTheo;
    private Bitmap bitmap;
    private LoaiThanhVien loaiThanhVien;

    public ThanhVien(String idThanhVien, String tenTaiKhoan, String matKhau, String tenDayDu, String trangThai, int toaDoX, int toaDoY, String anhThanhVien, Bitmap bitmap, LoaiThanhVien loaiThanhVien) {
        this.idThanhVien = idThanhVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenDayDu = tenDayDu;
        this.trangThai = trangThai;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY;
        this.anhThanhVien = anhThanhVien;
        this.bitmap = bitmap;
        this.loaiThanhVien = loaiThanhVien;
    }

    public int getLuongNuocMangTheo() {
        return luongNuocMangTheo;
    }

    public void setLuongNuocMangTheo(int luongNuocMangTheo) {
        this.luongNuocMangTheo = luongNuocMangTheo;
    }

    public ThanhVien(String idThanhVien, String tenTaiKhoan, String matKhau, String tenDayDu, String trangThai, int toaDoX, int toaDoY, String anhThanhVien, int luongNuocMangTheo, Bitmap bitmap, LoaiThanhVien loaiThanhVien) {
        this.idThanhVien = idThanhVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenDayDu = tenDayDu;
        this.trangThai = trangThai;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY;
        this.anhThanhVien = anhThanhVien;
        this.luongNuocMangTheo = luongNuocMangTheo;
        this.bitmap = bitmap;
        this.loaiThanhVien = loaiThanhVien;
    }

    public ThanhVien(String idThanhVien, String tenTaiKhoan, String matKhau, String tenDayDu, String trangThai, int toaDoX, int toaDoY, String anhThanhVien) {
        this.idThanhVien = idThanhVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenDayDu = tenDayDu;
        this.trangThai = trangThai;
        this.toaDoX = toaDoX ;
        this.toaDoY = toaDoY ;
        this.anhThanhVien = anhThanhVien;
    }

    public ThanhVien(String idThanhVien, String tenTaiKhoan, String matKhau, String tenDayDu, String trangThai, int toaDoX, int toaDoY, Bitmap bitmap) {
        this.idThanhVien = idThanhVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.tenDayDu = tenDayDu;
        this.trangThai = trangThai;
        this.toaDoX = toaDoX;
        this.toaDoY = toaDoY;
        this.bitmap = bitmap;
    }

    public ThanhVien() {
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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

    public String getAnhThanhVien() {
        return anhThanhVien;
    }

    public void setAnhThanhVien(String anhThanhVien) {
        this.anhThanhVien = anhThanhVien;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public LoaiThanhVien getLoaiThanhVien() {
        return loaiThanhVien;
    }

    public void setLoaiThanhVien(LoaiThanhVien loaiThanhVien) {
        this.loaiThanhVien = loaiThanhVien;
    }
}
