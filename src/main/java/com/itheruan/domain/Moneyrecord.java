package com.itheruan.domain;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name = "moneyrecord")
@Data
public class Moneyrecord{
	@Id
	private Integer id;
	private Integer usercardPhone;
	private Double consumptionmoney;
	private Integer type;
	private Integer cardtype;
	private Integer commoditykind;
	private String commodity;
	private String executor;
	private String time;
}

