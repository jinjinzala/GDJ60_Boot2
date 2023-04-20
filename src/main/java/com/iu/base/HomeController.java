package com.iu.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.iu.base.aoptest.Card;
import com.iu.base.aoptest.Transport;
import com.iu.base.board.BoardFileVO;
import com.iu.base.util.Pager;

@Controller
public class HomeController {
	@Autowired
	private Transport transport;
	
	@Autowired
	private Card card;
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/use")
	public void use() throws Exception {
		
		Pager pager = new Pager();
		pager.setKind("버스타이틀");
		transport.useBus(pager);
		
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setFileName("서프아아아알");
		transport.useSubway(boardFileVO);
	
		
		transport.takeWalk();
	}
	
}
