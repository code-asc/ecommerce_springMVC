package com.app.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.AdminCountryCount;
import com.app.model.BrandsOnly;
import com.app.model.CategoryType;
import com.app.model.HighestSoldItem;
import com.app.model.ProductDetails;
import com.app.model.SubCategoryType;
import com.app.service.AdminOtherDetails;
import com.app.service.AdminPageAllInfo;
import com.app.service.BrandInfo;
import com.app.service.HighestSoldInfo;
import com.app.service.ProductInfo;


@Controller
public class AdminPageInfo {

	@Autowired
	HighestSoldInfo details;
	
	@Autowired
	AdminPageAllInfo allInfo;
	
	@Autowired
	AdminOtherDetails otherDetails;
	
	@Autowired
	BrandInfo brandInfo;
	
	@Autowired
	ProductInfo productInfo;
	
	@RequestMapping(value = "/getHighestSoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<HighestSoldItem> getProductHighest()
	{
		return details.getHighestSold();
	}
	
	@RequestMapping(value = "/getCountrySoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<AdminCountryCount> getCountryHighest()
	{
		return details.getHighestSoldByCountry();
	}
	
	@RequestMapping(value = "/admin" , method = RequestMethod.GET)
	public String getAdminPage(Model model)
	{
		model.addAttribute("customer" , allInfo.customerCountList().get(0).getTotal());
		model.addAttribute("product" , allInfo.productCountList().get(0).getTotal());
		model.addAttribute("shipping" , allInfo.shippingCountList().get(0).getTotal());
		model.addAttribute("supplier" , allInfo.supplierCountList().get(0).getTotal());
		model.addAttribute("category", allInfo.supplierCountList().get(0).getTotal());
		model.addAttribute("subCategory" , allInfo.categorySubCountList().get(0).getTotal());
		return "admin";
	}
	
	@RequestMapping(value = "/adminOther" , method = RequestMethod.GET)
	public String getAdminOther()
	{
		return "adminOther";
	}
	
	@RequestMapping(value = "/addBrand")
	public @ResponseBody int getAddBrand(@RequestParam("brandName") String brandName)
	{
		return otherDetails.addBrand(brandName);
	}
	
	@RequestMapping(value = "/addCategory")
	public @ResponseBody int getAddCategory(@RequestParam("categoryType") String categoryType)
	{
		return otherDetails.addCategory(categoryType);
	}
	
	@RequestMapping(value = "/addSubCategory")
	public @ResponseBody int getAddSubCategory(@RequestParam("categoryID") int categoryID , @RequestParam("subCategoryType") String subCategoryType)
	{
		return otherDetails.addSubCategory(categoryID, subCategoryType);
	}
	
	@RequestMapping(value = "/getOnlyBrand")
	public @ResponseBody List<BrandsOnly> getBrand()
	{
		return brandInfo.onlyBrands();
	}
	
	@RequestMapping(value = "/getCategoryOnly")
	public @ResponseBody List<CategoryType> getCategory()
	{
		return otherDetails.getCategory();
	}
	
	@RequestMapping(value = "/getSubCategoryOnly")
	public @ResponseBody List<SubCategoryType> getSubCategory()
	{
		return otherDetails.getSubCategory();
	}
	
	@RequestMapping(value = "/adminSubCategory"  , method = RequestMethod.GET)
	public String getAdminSubCategory(Model model)
	{
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminSubCategory";
	}
	
	@RequestMapping(value = "/adminEditForm" , method = RequestMethod.GET)
	public String getAdminEditForm(Model model)
	{
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminEdit";
	}
	
	@RequestMapping(value = "/adminRemoveForm" , method = RequestMethod.GET)
	public String getAdminRemoveForm(Model model)
	{
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminRemove";
	}
	
	@RequestMapping(value = "/adminRemoveForm" , method = RequestMethod.POST)
	public String postAdminRemoveForm(Model model , @RequestParam("products") int productID)
	{
		otherDetails.deleteProduct(productID);
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminRemove";
	}
	
	@RequestMapping(value = "/adminEditForm" , method = RequestMethod.POST)
	public String postAdminEditForm(Model model , @RequestParam("products") int productID ,@RequestParam("productDesc") String productDesc , @RequestParam("unitPrice") BigDecimal unitPrice , @RequestParam("unitInStock") int unitInStock , @RequestParam("discount") BigDecimal discount , @RequestParam("thumbNailPhoto") String thumbNailPhoto , @RequestParam("largePhoto") String largePhoto)
	{
		otherDetails.updateProduct(productID, productDesc, unitPrice, discount, unitInStock);
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminEdit";
	}
	
	@RequestMapping(value = "/getSubCategoryBasedOnCategoryID")
	public @ResponseBody List<SubCategoryType> getSubCategoryBasedOnCategoryID(@RequestParam("categoryID") int categoryID)
	{
		return otherDetails.getSubCategory(categoryID);
	}
	
	@RequestMapping(value = "getProductInfoBySubCategoryID" , method = RequestMethod.GET)
	public @ResponseBody List<ProductDetails> getProductInfoBySubCategoryID(@RequestParam("subCategoryID") int subCategoryID)
	{
		return productInfo.getOnlyProductDetails(subCategoryID);
	}
	
	@RequestMapping(value = "getProductInfoByProductID" , method = RequestMethod.GET)
	public @ResponseBody List<ProductDetails> getProductInfoByProductID(@RequestParam("productID") int productID)
	{
		return productInfo.getOnlyProductDetailsByProductID(productID);
	}
}
