package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.model.Paging;
import com.model.ProductInfo;
import com.service.ProductInfoService;
import com.service.ProductService;
import com.util.Constant;
import com.validate.ProductInfoValidate;

@Controller
public class ProductInfoController {
	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	private ProductInfoService productInfoService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductInfoValidate productInfoValidate;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if (binder.getTarget() == null) {
			return;
		}

		// convert string thanh date de insert vao DB
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

		if (binder.getTarget().getClass() == ProductInfo.class) {
			binder.setValidator(productInfoValidate);
		}
	}

	// tu dong redirect
	@RequestMapping(value = { "/product-info/list", "/product-info/list/" })
	public String redirect() {
		return "redirect:/product-info/list/1";
	}

	@RequestMapping(value = "/product-info/list/{page}")
	public String showProductInfoList(Model model, HttpSession session,
			@ModelAttribute("searchForm") ProductInfo productInfo, @PathVariable("page") int page) {

		// page
		Paging paging = new Paging(5);
		paging.setIndexPage(page);

		List<ProductInfo> productInfos = productInfoService.getAllProductInfo(productInfo, paging);

		if (session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if (session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		model.addAttribute("pageInfo", paging);
		model.addAttribute("productInfos", productInfos);
		return "productInfo-list";
	}

	@GetMapping("/product-info/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Add ProductInfo");
		model.addAttribute("modelForm", new ProductInfo());

		// seelect box categories
		List<Category> categories = productService.getAllCategory(null, null);
		Map<String, String> mapCategories = new HashMap<>();
		for (Category category : categories) {
			mapCategories.put(String.valueOf(category.getId()), category.getName());
		}
		model.addAttribute("mapCategory", mapCategories);

		model.addAttribute("viewOnly", false);
		return "productInfo-action";
	}

	@GetMapping("/product-info/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		log.info("Edit ProductInfo id: " + id);

		ProductInfo productInfo = productInfoService.findByIdProductInfo(id);

		if (productInfo != null) {

			// seelect box categories
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategories = new HashMap<>();
			for (Category category : categories) {
				mapCategories.put(String.valueOf(category.getId()), category.getName());
			}
			model.addAttribute("mapCategory", mapCategories);

			model.addAttribute("titlePage", "Edit  ProductInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", false);
			return "productInfo-action";
		}
		return "redirect:/productInfo-list";
	}

	@GetMapping("/product-info/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {
		log.info("View product-info id: " + id);

		ProductInfo productInfo = productInfoService.findByIdProductInfo(id);

		if (productInfo != null) {
			model.addAttribute("titlePage", "View productInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", true);
			return "productInfo-action";
		}
		return "redirect:/product-info-list";
	}

	@PostMapping("/product-info/save")
	public String save(Model model, @ModelAttribute("modelForm") @Validated ProductInfo productInfo,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			if (productInfo.getId() != null) {
				model.addAttribute("titlePage", "Update ProductInfo");

			} else {
				model.addAttribute("titlePage", "Insert ProductInfo");
			}

			// seelect box categories
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategories = new HashMap<>();
			for (Category category : categories) {
				mapCategories.put(String.valueOf(category.getId()), category.getName());
			}
			model.addAttribute("mapCategory", mapCategories);

			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", false);
			return "productInfo-action";
		}
		// set category
		Category category = new Category();
		category.setId(productInfo.getCateId());
		productInfo.setCategory(category);

		if (productInfo.getId() != null && productInfo.getId() != 0) {
			try {
				productInfoService.update(productInfo);
				session.setAttribute(Constant.MSG_SUCCESS, "Update ProductInfo");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Update Has Error");
			}

		} else {
			try {
				productInfoService.save(productInfo);
				session.setAttribute(Constant.MSG_SUCCESS, "Insert Success");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Insert Has Error");
			}
		}
		return "redirect:/product-info/list";
	}

	@GetMapping("/product-info/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id, HttpSession session) {
		log.info("Delete product-info id: " + id);

		ProductInfo productInfo = productInfoService.findByIdProductInfo(id);

		if (productInfo != null) {
			try {
				productInfoService.delete(productInfo);
				session.setAttribute(Constant.MSG_SUCCESS, "Delete Success");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Delete Has Error");
			}
		}
		return "redirect:/product-info/list";
	}

}
