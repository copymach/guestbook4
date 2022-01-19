package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {

//	필드
	@Autowired
	private GuestbookDao guestbookDao;
	
//	생성자
//	메서드gs
//	메서드일반

	
//	http://localhost:8088/guestbook4/guestbook/addList
	
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("guestbookController > addList() ");
		
		List<GuestbookVo> guestbookList = guestbookDao.getList();

//		Servlet간 공유하는 게스트북 객체
		model.addAttribute("guestbookList", guestbookList);
		
		return "addList";
	} // addList 종료

//	http://localhost:8088/guestbook4/guestbook/write?name=33&password=33&content=33
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name,
						@RequestParam("password") String password,
						@RequestParam("content") String content) {
		System.out.println("PhoneController > write () ");

//		vo로 만든다
		GuestbookVo guestbookVo = new GuestbookVo(name, password, content);

		System.out.println(guestbookVo);
		
//		받아온 파라미터를 서버에 넣는다
		guestbookDao.ContentInsert(guestbookVo);
		
//		리다이렉트
		return "redirect:addList";
	} // write 종료

	
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(Model model, 
							@RequestParam(value="no", required=false, defaultValue = "1") int no) {
		System.out.println("PhoneController > deleteForm () ");
		
		List<GuestbookVo> guestbookList = guestbookDao.getList();

//		Servlet간 공유하는 게스트북 객체
		model.addAttribute("guestbookList", guestbookList);
		
		return "deleteForm";
	} // deleteForm 종료
	

	
//	http://localhost:8088/guestbook4/guestbook/delete?no=81&password=77
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int ContentNo, @RequestParam("password") String password) {
		System.out.println("PhoneController > delete () ");
		
		guestbookDao.ContentDelete(ContentNo, password);
		
//		리다이렉트
		return "redirect:addList";
	} // delete 종료
	

//	http://localhost:8088/guestbook4/guestbook/deleteForm2
	@RequestMapping(value = "/deleteForm2", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {
		System.out.println("PhoneController > deleteForm2 () ");
		
		return "deleteForm";
	} // deleteForm 종료
	
	
} // end of Controller 


