package com.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.model.Category;
import com.service.ProductService;
import com.util.Constant;

@Component
public class CategoryValidator implements Validator {
	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz == Category.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Category category = (Category) target;
		ValidationUtils.rejectIfEmpty(errors, Constant.CODE, Constant.MSG_REQUIRED);
		ValidationUtils.rejectIfEmpty(errors, Constant.NAME, Constant.MSG_REQUIRED);
		ValidationUtils.rejectIfEmpty(errors, Constant.DESCRIPTION, Constant.MSG_REQUIRED);

		if (category.getCode() != null) {
			List<Category> result = productService.findCategory(Constant.CODE, category);
			if (result != null && !result.isEmpty()) {
				errors.rejectValue(Constant.CODE, Constant.MSG_CODE_EXIST);
			}
		}

	}
}
