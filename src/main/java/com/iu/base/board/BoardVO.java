package com.iu.base.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BoardVO {

	private Long num;
	private String title;
	private String contents;
	private  String Writer;
	private  Date regDate;
	private  Long hit;
	
}
