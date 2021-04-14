package com.itheruan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itheruan.domain.Moneyrecord;
import com.itheruan.domain.Usercard;
import com.itheruan.entity.Result;
import com.itheruan.entity.ResultCode;
import com.itheruan.service.MoneyrecordService;
import com.itheruan.service.UsercardService;
import com.itheruan.utils.DateUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/moneyrecordQianController")
public class MoneyrecordQianController {
	
	@Autowired
	private MoneyrecordService moneyrecordservice;
	
	@Autowired
	private UsercardService usercardService;
	
	/**
	 * 查询全部
	 * @param request
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.GET)
	public Result findAll(HttpServletRequest request,@RequestHeader HttpHeaders headers){
		//System.out.println("简历");
		System.out.println("moneyrecord-------");
		List<String> list = headers.get("token");
		System.out.println(list);
		if (list==null) {
			System.out.println("kong");
			return new Result(ResultCode.FAIL);
		}else {
			if (list.size()<=0) {
				//System.out.println("sss");
				return new Result(ResultCode.FAIL);

			}else {

				System.out.println("查询全部");
				
				List<Moneyrecord> all = moneyrecordservice.findAll();
				System.out.println(all);
				return new Result(ResultCode.SUCCESS,all);
			}
		
			
		}
		
		
		
	}
	
	/**
	 * 添加充值记录
	 * @param request
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/savechong",method = RequestMethod.POST)
	public Result add(HttpServletRequest request,@RequestHeader HttpHeaders headers/*,@RequestBody  Moneyrecord moneyrecord*/){
		System.out.println("添加");
		
		int usercardPhone = 0;
		String executor ="";
		double consumptionmoney=0.0;
		int cardtype=5;
		
		if ((request.getParameter("usercardPhone"))!=null && (request.getParameter("executor"))!=null
				&& (request.getParameter("consumptionmoney"))!=null && (request.getParameter("cardtype"))!=null)
		{
			
			 usercardPhone = Integer.parseInt(request.getParameter("usercardPhone"));
			 executor = request.getParameter("executor");
			 consumptionmoney = (double)(Integer.parseInt(request.getParameter("consumptionmoney")));
			 cardtype =Integer.parseInt( request.getParameter("cardtype"));
			 
			 //判断用户是否存在
			 System.out.println("用户");
			 Usercard usercard = new Usercard();
				usercard.setPhone(usercardPhone);
				usercard.setType(cardtype);
				//System.out.println("usercard:"+usercard);
				
				Usercard usercard2=usercardService.findOne(usercard);
				//System.out.println("usercard2:"+usercard2);
				
			 if(usercard2==null)//如果查询为空则用户不存在
			 {
				 System.out.println("用户不存在");
				 return new Result(ResultCode.UNAUTHORISE);
			 }
			 
			 
		}
		else {
			return new Result(ResultCode.FAIL);
		}
		
		
		List<String> list = headers.get("token");
		System.out.println(list);
		
		if (list==null) {
			System.out.println("kong");
			return new Result(ResultCode.UNAUTHENTICATED);
		}else {
			if (list.size()<=0) {
				System.out.println("sss");
				return new Result(ResultCode.UNAUTHENTICATED);

			}else {

				
				//System.out.println("usercardPhone："+usercardPhone+"executor:"+executor+"consumptionmoney:"+consumptionmoney+"cardtype:"+cardtype);
				DateUtils dateUtils = new DateUtils();
				
				Moneyrecord moneyrecord = new Moneyrecord();
			
				moneyrecord.setUsercardPhone(usercardPhone);
				moneyrecord.setConsumptionmoney(consumptionmoney);
				moneyrecord.setType(2);
				moneyrecord.setCardtype(cardtype);
				moneyrecord.setCommoditykind(0);
				moneyrecord.setCommodity("充值");
				moneyrecord.setExecutor(executor);
				String timeStamp = dateUtils.timeStamp();
				String riqi= dateUtils.timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
				moneyrecord.setTime(riqi);
				
				System.out.println(moneyrecord);
				
				//判断是否为充值类型
				if (moneyrecord.getType()==2) {
					System.out.println("充值金额");
					Usercard usercard = new Usercard();
					usercard.setPhone(usercardPhone);
					usercard.setType(cardtype);
					
					Usercard usercard2=usercardService.findOne(usercard);
					//System.out.println(usercard2);
					
					double money=moneyrecord.getConsumptionmoney();//获取钱数
					usercard2.setMoney(usercard2.getMoney()+money);
					usercardService.updete(usercard2);
					
					
				}
				else {
					return new Result(ResultCode.FAIL);
				}
				
				
				
				
				moneyrecordservice.add(moneyrecord);
				
				return new Result(ResultCode.SUCCESS);

			}
		
			
		}
		
		
		//生成token
		//request.getSession().getAttribute("administrator")
		
		/**/
	}
	
	
	@RequestMapping(value = "/del",method = RequestMethod.GET)
	public Result del(HttpServletRequest request,@RequestHeader HttpHeaders headers,int id){
		System.out.println("删除记录");
		System.out.println(id);
		
		return new Result(ResultCode.SUCCESS);
	}
	
}
