package com.itheruan.domain;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "cardtype")
@Data
public class Cardtype{
	@Id
	private Integer id;
	private String cardname;
	private Integer haircut;
	private Integer hairdressing;
}

