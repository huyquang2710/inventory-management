package com.validate;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.model.ProductInfo;
import com.service.ProductInfoService;
import com.util.Constant;

@Component
public class ProductInfoValidate implements Validator {

	@Autowired
	private ProductInfoService productInfoService;

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz == ProductInfo.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductInfo productInfo = (ProductInfo) target;
		ValidationUtils.rejectIfEmpty(errors, Constant.CODE, Constant.MSG_REQUIRED);
		ValidationUtils.rejectIfEmpty(errors, Constant.NAME, Constant.MSG_REQUIRED);
		ValidationUtils.rejectIfEmpty(errors, Constant.DESCRIPTION, Constant.MSG_REQUIRED);
		ValidationUtils.rejectIfEmpty(errors, Constant.MULTIPART_FILE, Constant.MSG_REQUIRED);

		if (productInfo.getCode() != null) {
			List<ProductInfo> result = productInfoService.findProductInfoByCode(productInfo.getCode());
			if (result != null && !result.isEmpty()) {
				// check khi update
				if (productInfo.getId() != null && productInfo.getId() != 0) {
					if (result.get(0).getId() != productInfo.getId()) {
						errors.rejectValue(Constant.CODE, Constant.MSG_CODE_EXIST);
					}
				} else {
					// khi insert
					errors.rejectValue(Constant.CODE, Constant.MSG_CODE_EXIST);
				}
			}
		}
		if (productInfo.getMultipartFile() != null) {
			String extension = FilenameUtils.getExtension(productInfo.getMultipartFile().getOriginalFilename());
			if (!extension.equals(Constant.JPG) || !extension.equals(Constant.PNG)) {
				errors.rejectValue(Constant.MULTIPART_FILE, Constant.MSG_FILE_EXTENSION_ERROR);
			}
		}
	}
}
