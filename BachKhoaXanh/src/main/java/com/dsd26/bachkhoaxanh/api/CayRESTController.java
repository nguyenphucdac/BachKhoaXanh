package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.business.Map;
import com.dsd26.bachkhoaxanh.business.Point;
import com.dsd26.bachkhoaxanh.business.TimDuongDi;
import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.object.CayObject;
import com.dsd26.bachkhoaxanh.object.LoaiCayObject;

/*
 * author: Nguyễn Phúc Đạc
 */ 

@RestController
public class CayRESTController {
	@Autowired
	private ICayDAO iCayDAO;
	
	@Autowired
	private ILoaiCayDAO iLoaiCayDAO;

	@RequestMapping(value = "/get-cay/{idCay}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CayObject>  getCay(@PathVariable("idCay") String idCay) {
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return new ResponseEntity<>(new CayObject(), HttpStatus.BAD_REQUEST);
		}
		
		LoaiCay loaiCay = iLoaiCayDAO.timKiem(cay.getIdLoaiCay());
		LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
		CayObject cayObj = new CayObject(cay, loaiCayObject);
		
		return new ResponseEntity<>(cayObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-list-cay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CayObject>> getListCay() {
		List<CayObject> lstCayObj = new ArrayList<CayObject>();
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		System.out.println(danhSachCay.getList().size());
		for(int i = 0 ; i < danhSachCay.getList().size(); i++) {
			
			CayMD cayMD = danhSachCay.getList().get(i);
			LoaiCay loaiCay = iLoaiCayDAO.timKiem(cayMD.getIdLoaiCay());
			LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
			CayObject cayObj = new CayObject(cayMD, loaiCayObject);
			
			lstCayObj.add(cayObj);
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.add("message", "successfuly");
		return new ResponseEntity<>(lstCayObj, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-trace-cay-1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Point> getTrace1(	@RequestBody(required = true) String body, 
							String idThanhVien,
							String toaDoX,
							String toaDoY,
							String idCay
			) {
		List<Point> trace = new ArrayList<>();
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return trace;
		}
		
		Point startPoint = new Point(Integer.parseInt(toaDoX), Integer.parseInt(toaDoY));
		Point targetPoint = new Point(cay.getToaDoX(), cay.getToaDoY());
		
		Map.map[targetPoint.x][targetPoint.y] = 4;
		trace = TimDuongDi.timDuongDi(startPoint, targetPoint);
		Map.map[targetPoint.x][targetPoint.y] = 1;
		
		return trace;
	}
	
	@RequestMapping(value = "/get-trace-cay-2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Point> getTrace2(	@RequestBody(required = true) String body, 
							String idThanhVien,
							String toaDoX,
							String toaDoY
			) {
		List<Point> trace = new ArrayList<>();
		List<Point> temp = new ArrayList<>();
		List<CayMD> lstCayRequire = new ArrayList<>();
		int minAccept = 2;
		int minRoad = Integer.MAX_VALUE;
		
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		CayMD cayMD = null;
		for(int i = 0 ; i < danhSachCay.getList().size(); i++) {
			cayMD = danhSachCay.getList().get(i);
			
			if(cayMD.getLuongNuocToiDa() - cayMD.getLuongNuocDaTuoi() >= minAccept) {
				lstCayRequire.add(cayMD);
			}
		}
		
		if(lstCayRequire.size() >= 1 ) {
			for(int i = 0; i< lstCayRequire.size(); i++) {
				
				Point startPoint = new Point(Integer.parseInt(toaDoX), Integer.parseInt(toaDoY));
				Point targetPoint = new Point(lstCayRequire.get(i).getToaDoX(), lstCayRequire.get(i).getToaDoY());
				
				Map.map[targetPoint.x][targetPoint.y] = 4;
				temp = TimDuongDi.timDuongDi(startPoint, targetPoint);
				Map.map[targetPoint.x][targetPoint.y] = 1;
				
				System.out.println(temp.size());
				if(temp.size() < minRoad) {
					trace = null;
					trace = temp;
					minRoad = trace.size();
				}
				
				temp = null;
			}
		}
		
		return trace;
		
	}
}
