package com.dsd26.bachkhoaxanh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;

/*
 * author: Nguyễn Phúc Đạc
 */ 

@RestController
public class CayRESTController {
	@Autowired
	private ICayDAO iCayDAO;

	@RequestMapping(value = "/get_cay/{idCay}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CayMD getCay(@PathVariable("idCay") String idCay) {
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return new CayMD();
		}
		CayMD cayMD = new CayMD(cay);
		
		return cayMD;
	}
}
