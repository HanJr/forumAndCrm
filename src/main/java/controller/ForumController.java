package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import commandInstance.NewArticleRequest;
import common.Paging;
import dao.ForumDao;
import entity.ForumArticle;
import validator.NewArticleRequestValidator;
import variableObject.AuthInfo;

@Controller
@RequestMapping("/forum")
public class ForumController {
	
	//dao 대신에 forum 전용 서비스를 만드는 것이 더 효율적일 수 있었다는 생각을 해본다.
	private ForumDao dao;
	
	public void setDao(ForumDao dao) {
		this.dao = dao;
	}
	
	@GetMapping("/userForum")
	public String handleUserForum(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage,
							      @RequestParam(required = false, defaultValue = "1") int currentBlock) {
		
		int totalNumOfArticles = dao.getNumOfArticles();
		
		Paging paging = new Paging();

		System.out.println("raw = " + currentPage + " herrrrree");
		
		paging.currentPageInfo(currentPage, currentBlock, totalNumOfArticles);
		
		ArrayList<ForumArticle> articleList = (ArrayList<ForumArticle>) dao.getArticleList(paging);
		
		System.out.println("CA = " + paging.getCurrentPage() + " herrrrree");
		
		model.addAttribute("pagination", paging);
		model.addAttribute("articleList", articleList);
		
		return "forum/userForum";
	}
	
	@GetMapping("/composeArticle")
	public String composeArticle(Model model, HttpSession session, @RequestParam("currentPage") int currentPage, @RequestParam("currentBlock") int currentBlock) {
		
		if(session.getAttribute("authInfo") == null) {
			model.addAttribute("error", "Login is required");
			return "forward:userForum";
		}
		
		NewArticleRequest newArticle = new NewArticleRequest();
		newArticle.setAuthor(((AuthInfo) session.getAttribute("authInfo")).getName());
		
		model.addAttribute("newArticle", newArticle);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentBlock", currentBlock);
		
		return "forum/newArticleForm";
	}
	
	@PostMapping("/composeArticle")
	public String composeArticlePost(@Valid @ModelAttribute("newArticle") NewArticleRequest newArticle, Errors errors, HttpSession session) {
		
		if(errors.hasErrors()) {
			return "forum/newArticleForm";
		}
		
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		ForumArticle article = new ForumArticle();
		article.setName(newArticle.getAuthor());
		article.setArticleContents(newArticle.getArticleContents());
		article.setTitle(newArticle.getTitle());
		article.setViewNum(0);
		
		dao.insertArticle(article, authInfo);
		
		return "redirect:/forum/userForum";
	}
	
	@GetMapping("/deleteArticle/{id}")
	public String deleteArticle(@PathVariable("id") long id) {
		
		dao.deleteArticle(id);
		
		return "redirect:/forum/userForum";
	}
	
	@GetMapping("/editArticle")
	public String editArticle(@RequestParam("id") long id, Model model, @RequestParam("currentPage") int currentPage, @RequestParam("currentBlock") int currentBlock) {
		
		ForumArticle article = dao.getArticle(id);	
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentBlock", currentBlock);			
		model.addAttribute("article", article);
		return "forum/editArticleForm";
	}
	
	@PostMapping("/editArticle")
	public String editArticlePost(@ModelAttribute("article") ForumArticle article) {
		
		dao.updateArticle(article);
		
		return "redirect:/forum/userForum";
	}
	
	@GetMapping("/viewArticle/{articleId}")
	public String viewArticle(@PathVariable("articleId") Long id, Model model, HttpSession session, @RequestParam("currentPage") int currentPage, @RequestParam("currentBlock") int currentBlock) {
		
		ForumArticle article = dao.getArticle(id);
		article.setViewNum(article.getViewNum() + 1);
		dao.updateArticle(article);
		
		if(session.getAttribute("authInfo") != null) {
			AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
			model.addAttribute("authInfo", authInfo);
		}
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentBlock", currentBlock);		
		model.addAttribute("article", article);
		
		return "forum/articleViewer";
	}
	
	@InitBinder("newArticleRequest")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new NewArticleRequestValidator());
	}
}
