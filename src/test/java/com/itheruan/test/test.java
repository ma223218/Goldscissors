package com.itheruan.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheruan.dao.AdministratorMapper;
import com.itheruan.dao.MoneyrecordMapper;
import com.itheruan.dao.UsercardMapper;
import com.itheruan.domain.Moneyrecord;
import com.itheruan.domain.Usercard;
import com.itheruan.service.AdministratorService;
import com.itheruan.utils.DateUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @program: 
 * @description:
 * @author: YAN.YUE.SHUANG
 * @create: 2021-04-07 08:18
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class test {

    @Autowired
    private 	AdministratorService  studentMapper;
    
    @Autowired
    private AdministratorMapper ad;
    
    @Autowired
    private MoneyrecordMapper mr;
    
    @Autowired
    private UsercardMapper um;
    
    @Test
    public void test_01() {

System.out.println("进");
      //List<Administrator> list = ad.selectAll();
		/*DateUtils dateUtils = new DateUtils();
		
		Moneyrecord moneyrecord = new Moneyrecord();
	
		moneyrecord.setUsercardPhone(123789);
		moneyrecord.setConsumptionmoney(20.00);
		moneyrecord.setType(1);
		moneyrecord.setCardtype(3);
		moneyrecord.setCommoditykind(2);
		moneyrecord.setCommodity("美容");
		moneyrecord.setExecutor("zhangsan");
		
		String timeStamp = dateUtils.timeStamp();
		String riqi= dateUtils.timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
		
		moneyrecord.setTime(riqi);*/
		
		//System.out.println(moneyrecord);
	  //mr.insert(moneyrecord);
	  //System.out.println("123");
      //List<Moneyrecord> list = mr.selectAll();
      //System.out.println(list);
      
//     Administrator administrator = new Administrator();
//     administrator.setName("zhangsan");
//     administrator.setPassword("123456");
//     
//     Administrator one = ad.selectOne(administrator);
//     
//     System.out.println(one);

//	Usercard usercard = new Usercard();
//	usercard.setPhone(123789);
//	usercard.setType(3);
//	Usercard one = um.selectOne(usercard);
//	System.out.println(one);
	Example example = new Example(Usercard.class);
	Criteria criteria = example.createCriteria();
	criteria.andEqualTo("type", 1);

	List<Usercard>usercards=um.selectByExample(example);
	System.out.println(usercards);
    }

   
}
