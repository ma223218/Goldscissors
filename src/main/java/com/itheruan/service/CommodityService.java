package com.itheruan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheruan.dao.CommodityMapper;

@Service
public class CommodityService {

	@Autowired
	private CommodityMapper commodityMapper;
	
	
	
}
