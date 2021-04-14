package com.itheruan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itheruan.domain.Moneyrecord;
import com.itheruan.domain.Usercard;
import com.itheruan.entity.Result;
import com.itheruan.entity.ResultCode;
import com.itheruan.service.UsercardService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usercardQian")
public class UsercardQianController {

	@Autowired
	private UsercardService usercardService;
	
	/**
	 * 根据会员类型查询会员
	 * @param request
	 * @param headers
	 * @param type
	 * @return
	 */
    @RequestMapping(value = "/findByIdtype",method = RequestMethod.GET)
	public Result findByIdtype(HttpServletRequest request,@RequestHeader HttpHeaders headers){
    	System.out.println("进入会员查询");
    	int type = Integer.parseInt(request.getParameter("type"));
    	System.out.println("type:"+type);	
		/**/
    	
    	System.out.println("usercard-------");
    	List<String> list = headers.get("token");
		System.out.println("token:"+list);
		
		if (list==null) {
			System.out.println("kong");
			return new Result(ResultCode.FAIL);
		}else {
			if (list.size()<=0) {
				System.out.println("sss");
				return new Result(ResultCode.FAIL);

			}else {
				
				List<Usercard> huiyuan=usercardService.findByType(type);
				System.out.println(huiyuan);
				return new Result(ResultCode.SUCCESS,huiyuan);
			}
		}
		
		
	}
	
	
	
}
