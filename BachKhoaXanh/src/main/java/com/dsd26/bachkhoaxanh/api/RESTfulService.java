package com.dsd26.bachkhoaxanh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.google.gson.Gson;

/*
 * author: Nguyễn Phúc Đạc
 */

// class demo goi api tra ve json
@RestController
public class RESTfulService {
	
	@Autowired
	private ICayDAO iCayDAO;
	
	@RequestMapping(value = "/get_cay/{idCay}", 
            method = RequestMethod.GET
           )
    @ResponseBody
    public String getCay(@PathVariable("idCay") String idCay)  {
		System.out.println(idCay);
		Cay cay = iCayDAO.timKiem(idCay);
		
		if(cay == null) {
			return "bad request";
		}
		
		CayMD cayMD = new CayMD(cay);
		
		Gson gson = new Gson();
		String jsonObject = gson.toJson(cayMD);

		return jsonObject;
	}
 
}
