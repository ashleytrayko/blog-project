package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/",""})
	public String index(Model model) { //@AuthenticationPrincipal PrincipalDetail principal
		model.addAttribute("boards", boardService.list());
		return "index";
	}
	
	//user권한이 필요 
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
