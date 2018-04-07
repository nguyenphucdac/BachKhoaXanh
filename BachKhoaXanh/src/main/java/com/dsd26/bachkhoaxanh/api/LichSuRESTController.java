package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.dao.ILichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.object.CayObject;
import com.dsd26.bachkhoaxanh.object.LichSuTuoiObject;
import com.dsd26.bachkhoaxanh.object.LoaiCayObject;
import com.dsd26.bachkhoaxanh.object.LoaiThanhVienObject;
import com.dsd26.bachkhoaxanh.object.ThanhVienObject;


/*
 * author: Nguyễn Phúc Đạc
 */

@RestController
public class LichSuRESTController {
	
	@Autowired
	private ILichSuTuoiDAO iLichSuTuoiDAO;
	@Autowired
	private ICayDAO iCayDAO;
	@Autowired
	private ILoaiCayDAO iLoaiCayDAO;
	@Autowired 
	private IThanhVienDAO iThanhVien;
	@Autowired 
	private ILoaiThanhVienDAO iLoaiThanhVien;
	
	@RequestMapping(value = "/get-lich-su-tuoi-cay/{idCay}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LichSuTuoiObject>  getCay(@PathVariable("idCay") String idCay) {
		List<LichSuTuoiObject> lstLichSuTuoi = new ArrayList<>();
		
		PaginationResult<LichSuTuoiMD> danhSach = iLichSuTuoiDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		
		for(int i = 0 ; i < danhSach.getList().size(); i++) {
			
			LichSuTuoiMD lichSuTuoiMD = danhSach.getList().get(i);
			
			if(lichSuTuoiMD.getIdCay().equals(idCay)) {
				Cay cay = iCayDAO.timKiem(lichSuTuoiMD.getIdCay());
				LoaiCay loaiCay = iLoaiCayDAO.timKiem(cay.getIdLoaiCay());
				LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
				CayObject cayObject = new CayObject(cay, loaiCayObject);
				
				ThanhVien thanhVien = iThanhVien.timKiem(lichSuTuoiMD.getIdThanhVien());
				LoaiThanhVienMD loaiThanhVienMD = new LoaiThanhVienMD(iLoaiThanhVien.timKiem(thanhVien.getIdLoaiThanhVien()));
				LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVienMD);
				ThanhVienObject thanhVienObject = new ThanhVienObject(thanhVien, loaiThanhVienObject);
				
				LichSuTuoiObject lichSuTuoiObject = new LichSuTuoiObject(lichSuTuoiMD, cayObject, thanhVienObject);
				
				lstLichSuTuoi.add(lichSuTuoiObject);
			}
			
		}
		
		
		return lstLichSuTuoi;
	}
	
	@RequestMapping(value = "/get-lich-su-tuoi-thanh-vien/{idThanhVien}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LichSuTuoiObject>  getThanhVien(@PathVariable("idThanhVien") String idThanhVien) {
		List<LichSuTuoiObject> lstLichSuTuoi = new ArrayList<>();
		
		PaginationResult<LichSuTuoiMD> danhSach = iLichSuTuoiDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		
		for(int i = 0 ; i < danhSach.getList().size(); i++) {
			
			LichSuTuoiMD lichSuTuoiMD = danhSach.getList().get(i);
			
			if(lichSuTuoiMD.getIdThanhVien().equals(idThanhVien)) {
				Cay cay = iCayDAO.timKiem(lichSuTuoiMD.getIdCay());
				LoaiCay loaiCay = iLoaiCayDAO.timKiem(cay.getIdLoaiCay());
				LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
				CayObject cayObject = new CayObject(cay, loaiCayObject);
				
				ThanhVien thanhVien = iThanhVien.timKiem(lichSuTuoiMD.getIdThanhVien());
				LoaiThanhVienMD loaiThanhVienMD = new LoaiThanhVienMD(iLoaiThanhVien.timKiem(thanhVien.getIdLoaiThanhVien()));
				LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVienMD);
				ThanhVienObject thanhVienObject = new ThanhVienObject(thanhVien, loaiThanhVienObject);
				
				LichSuTuoiObject lichSuTuoiObject = new LichSuTuoiObject(lichSuTuoiMD, cayObject, thanhVienObject);
				
				lstLichSuTuoi.add(lichSuTuoiObject);
			}
			
		}
		
		
		return lstLichSuTuoi;
	}

}
