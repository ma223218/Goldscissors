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
import com.itheruan.service.CommodityService;
import com.itheruan.service.MoneyrecordService;
import com.itheruan.service.UsercardService;
import com.itheruan.utils.DateUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/commodityQian")
public class CommodityQianController {
	
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private UsercardService usercardService;
	
	@Autowired
	private MoneyrecordService moneyrecordService;
	
	
	@RequestMapping(value = "/queryCommodity",method = RequestMethod.GET)
	public Result queryCommodity(HttpServletRequest request,@RequestHeader HttpHeaders headers){
		System.out.println("查询消费内容");
		
		
		return new Result(ResultCode.SUCCESS);
	}
	
	
	@RequestMapping(value = "/savexiao",method = RequestMethod.POST)
	public Result add(HttpServletRequest request,@RequestHeader HttpHeaders headers){
		System.out.println("添加消费记录");
		
		int usercardPhone = 0;
		String executor ="";
		double consumptionmoney=0.0;
		int cardtype=5;
		int commoditykind=0;
		String commodity="";
		
		if ((request.getParameter("usercardPhone"))!=null && (request.getParameter("executor"))!=null
				&& (request.getParameter("consumptionmoney"))!=null && (request.getParameter("cardtype"))!=null
				&& (request.getParameter("commoditykind"))!=null && (request.getParameter("commodity"))!=null
				) {
			
			 usercardPhone = Integer.parseInt(request.getParameter("usercardPhone"));
			 executor = request.getParameter("executor");
			 consumptionmoney = (double)(Integer.parseInt(request.getParameter("consumptionmoney")));
			 cardtype =Integer.parseInt( request.getParameter("cardtype"));
			 commoditykind = Integer.parseInt( request.getParameter("commoditykind"));
			 commodity = request.getParameter("commodity");
			 //判断用户是否存在
			 System.out.println("用户");
			 Usercard usercard = new Usercard();
				usercard.setPhone(usercardPhone);
				usercard.setType(cardtype);
				System.out.println("查询会员的条件usercard:"+usercard);
				
				Usercard usercard2=usercardService.findOne(usercard);
				System.out.println("查询到的会员usercard2:"+usercard2);
				
			 if(usercard2==null)//如果查询为空则用户不存在
			 {
				 System.out.println("用户不存在");
				 return new Result(ResultCode.UNAUTHORISE);
			 }
			 
			 
		}
		else {
			return new Result(ResultCode.FAIL);//数据不全
		}
		
		
		List<String> list = headers.get("token");
		System.out.println(list);
		
		if (list==null) {
			System.out.println("kong");
			return new Result(ResultCode.UNAUTHENTICATED);
		}
		else
		{
			if (list.size()<=0) {
				System.out.println("sss");
				return new Result(ResultCode.UNAUTHENTICATED);
			}
			
			else {

				
				//System.out.println("usercardPhone："+usercardPhone+"executor:"+executor+"consumptionmoney:"+consumptionmoney+"cardtype:"+cardtype);
				DateUtils dateUtils = new DateUtils();
				
				Moneyrecord moneyrecord = new Moneyrecord();
			
				moneyrecord.setUsercardPhone(usercardPhone);
				moneyrecord.setConsumptionmoney(consumptionmoney);
				moneyrecord.setType(1);
				moneyrecord.setCardtype(cardtype);
				moneyrecord.setCommoditykind(commoditykind);
				moneyrecord.setCommodity(commodity);
				moneyrecord.setExecutor(executor);
				String timeStamp = dateUtils.timeStamp();
				String riqi= dateUtils.timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
				moneyrecord.setTime(riqi);
				
				System.out.println(moneyrecord);
				
				/**/
				//判断是否为充值类型
				if (moneyrecord.getType()==1) {
					System.out.println("消费金额");
					Usercard usercard = new Usercard();
					usercard.setPhone(usercardPhone);
					usercard.setType(cardtype);
					
					Usercard usercard2=usercardService.findOne(usercard);
					//System.out.println(usercard2);
					double money=moneyrecord.getConsumptionmoney();//获取钱数
					if(usercard2.getMoney()>=money){
						usercard2.setMoney(usercard2.getMoney()-money);
						//System.out.println(usercard2);
						usercardService.updete(usercard2);
					}
					else {
						return new Result(ResultCode.FAIL);//余额不足
					}
					
				}
				
				moneyrecordService.add(moneyrecord);
				
				return new Result(ResultCode.SUCCESS);//成功

			}

	}
	}
}
