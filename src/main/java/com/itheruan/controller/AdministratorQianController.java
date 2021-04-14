package com.itheruan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itheruan.domain.Administrator;
import com.itheruan.entity.Result;
import com.itheruan.entity.ResultCode;
import com.itheruan.service.AdministratorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/administratorQian")
public class AdministratorQianController {
	
	@Autowired
	private AdministratorService administratorService;
	
	public String  names;
	
	/**
	 * 登录验证生产token
	 * @param request
	 * @param name
	 * @param password
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/findOne",method = RequestMethod.POST)
	public Result findAll(HttpServletRequest request,String name,String password,@RequestHeader HttpHeaders headers){
		System.out.println("administrator-------");
		List<String> list = headers.get("token");
		System.out.println("token:"+list);
		
		System.out.println("name:"+name+"\tpassword:"+password);
		Administrator administrator = new Administrator();
		administrator.setName(name);
		administrator.setPassword(password);
		
		Administrator one=new Administrator();
		
		if (name==null||password==null) {
			one=null;
		}
		else{
			one =  administratorService.findCS(administrator);
		}
		//List<Administrator> one= administratorService.findOne(administrator);
		
		System.out.println(one);
		
		//判断是否查到值
		String panduan="";
		
		//return new Result(ResultCode.SUCCESS, one);
		if (one==null) {
			System.out.println("没有查询到");
			panduan="1";
		}
		
		
		//判断是否已经等录
		if (list==null) {
			if (panduan=="1") {
				System.out.println("失败");
				return new Result(ResultCode.UNAUTHENTICATED);
			}
			else{
				
				System.out.println("成功");
				HttpSession session = request.getSession();
				session.setAttribute("administrator", one);
				names=name;
				//System.out.println("执行人："+names);
				//生成token
				String token = administrator.getName()+administrator.getPassword()+session.getId();
				
				return new Result(ResultCode.SUCCESS, token);
			}
		}
		else {
			System.out.println("已经登录了");
			System.out.println("执行人："+names);
			
			return new Result(ResultCode.FAIL,names);
		}
	
		
	}

	
	
	
	
	
}
