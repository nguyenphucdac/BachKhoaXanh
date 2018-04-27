package ambe.com.vn.bachkhoaxanh.models;

import java.io.Serializable;

/**
 * Created by AMBE on 18/03/2018.
 */

public class LoaiThanhVien implements Serializable {
    private String idLoaiThanhVien;
    private String tenLoaiThanhVien;
    private String anhLoaiThanhVien;


    public LoaiThanhVien(String idLoaiThanhVien, String tenLoaiThanhVien, String anhLoaiThanhVien) {
        this.idLoaiThanhVien = idLoaiThanhVien;
        this.tenLoaiThanhVien = tenLoaiThanhVien;
        this.anhLoaiThanhVien = anhLoaiThanhVien;
    }

    public LoaiThanhVien(String idLoaiThanhVien, String tenLoaiThanhVien) {
        this.idLoaiThanhVien = idLoaiThanhVien;
        this.tenLoaiThanhVien = tenLoaiThanhVien;
    }

    public String getIdLoaiThanhVien() {
        return idLoaiThanhVien;
    }

    public void setIdLoaiThanhVien(String idLoaiThanhVien) {
        this.idLoaiThanhVien = idLoaiThanhVien;
    }

    public String getTenLoaiThanhVien() {
        return tenLoaiThanhVien;
    }

    public void setTenLoaiThanhVien(String tenLoaiThanhVien) {
        this.tenLoaiThanhVien = tenLoaiThanhVien;
    }

    public String getAnhLoaiThanhVien() {
        return anhLoaiThanhVien;
    }

    public void setAnhLoaiThanhVien(String anhLoaiThanhVien) {
        this.anhLoaiThanhVien = anhLoaiThanhVien;
    }
}

