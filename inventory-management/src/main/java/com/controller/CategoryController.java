package com.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BaseDAOImpl;
import com.model.Category;
import com.service.ProductService;
import com.util.Constant;
import com.validate.CategoryValidator;

@Controller
public class CategoryController {
	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryValidator categoryValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if (binder.getTarget() == null) {
			return;
		}

		// convert string thanh date de insert vao DB
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

		if (binder.getTarget().getClass() == Category.class) {
			binder.setValidator(categoryValidator);
		}
	}

	@RequestMapping(value = "/category/list")
	public String showCategoryList(Model model, HttpSession session, @ModelAttribute("searchForm") Category category) {
		List<Category> categories = productService.getAllCategory(category);

		if (session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if (session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		model.addAttribute("categories", categories);
		return "category-list";
	}

	@GetMapping("/category/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Add Category");
		model.addAttribute("modelForm", new Category());
		model.addAttribute("viewOnly", false);
		return "category-action";
	}

	@GetMapping("/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		log.info("Edit category id: " + id);

		Category category = productService.findByIdCategory(id);

		if (category != null) {
			model.addAttribute("titlePage", "Edit  Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", false);
			return "category-action";
		}
		return "redirect:/category-list";
	}

	@GetMapping("/category/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {
		log.info("View category id: " + id);

		Category category = productService.findByIdCategory(id);

		if (category != null) {
			model.addAttribute("titlePage", "View Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", true);
			return "category-action";
		}
		return "redirect:/category-list";
	}

	@PostMapping("/category/save")
	public String save(Model model, @ModelAttribute("modelForm") @Validated Category category, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			if (category.getId() != null) {
				model.addAttribute("titlePage", "Update Category");

			} else {
				model.addAttribute("titlePage", "Insert Category");
			}
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", false);
			return "category-action";
		}
		if (category.getId() != null && category.getId() != 0) {
			try {
				productService.update(category);
				session.setAttribute(Constant.MSG_SUCCESS, "Update Success");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Update Has Error");
			}

		} else {
			try {
				productService.save(category);
				session.setAttribute(Constant.MSG_SUCCESS, "Insert Success");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Insert Has Error");
			}
		}
		return "redirect:/category/list";
	}

	@GetMapping("/category/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id, HttpSession session) {
		log.info("Delete category id: " + id);

		Category category = productService.findByIdCategory(id);

		if (category != null) {
			try {
				productService.delete(category);
				session.setAttribute(Constant.MSG_SUCCESS, "Delete Success");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Delete Has Error");
			}
		}
		return "redirect:/category/list";
	}
}
