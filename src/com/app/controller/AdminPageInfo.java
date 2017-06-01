package com.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
/**
 * The AdminPageInfo class provides all the Admin page details
 */
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
	
	
	/**
	 *  getProductHighest method returns details of most sold product.
	 */
	@RequestMapping(value = "/getHighestSoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<HighestSoldItem> getProductHighest()
	{
		return details.getHighestSold();
	}
	
	
	/**
	 *  getCountryHighest method returns details of most active country.
	 */
	@RequestMapping(value = "/getCountrySoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<AdminCountryCount> getCountryHighest()
	{
		return details.getHighestSoldByCountry();
	}
	
	
	/**
	 *  getAdminPage method returns all the information such as number of customers, products, shipping, supplier, category and subcategory.
	 */
	@RequestMapping(value = "/admin" , method = RequestMethod.GET)
	public String getAdminPage(Model model , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && session.getAttribute("role").toString().compareTo("admin")==0)
		{
		model.addAttribute("customer" , allInfo.customerCountList().get(0).getTotal());
		model.addAttribute("product" , allInfo.productCountList().get(0).getTotal());
		model.addAttribute("shipping" , allInfo.shippingCountList().get(0).getTotal());
		model.addAttribute("supplier" , allInfo.supplierCountList().get(0).getTotal());
		model.addAttribute("category", allInfo.categoryCountList().get(0).getTotal());
		model.addAttribute("subCategory" , allInfo.categorySubCountList().get(0).getTotal());
		return "admin";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  getAdminOther method is used to check whether the user is admin or customer and redirect to adminOther page
	 */
	@RequestMapping(value = "/adminOther" , method = RequestMethod.GET)
	public String getAdminOther(HttpSession session)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
		return "adminOther";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  getAddBrand method provides user all brand details.
	 * @param brandName of type String.
	 */
	@RequestMapping(value = "/addBrand")
	public @ResponseBody int getAddBrand(@RequestParam("brandName") String brandName)
	{
		return otherDetails.addBrand(brandName);
	}
	
	/**
	 *  getAddCategory method provides user all category details.
	 * @param categoryType of type String.
	 */
	@RequestMapping(value = "/addCategory")
	public @ResponseBody int getAddCategory(@RequestParam("categoryType") String categoryType)
	{
		return otherDetails.addCategory(categoryType);
	}
	
	/**
	 *  getAddSubCategory method provides user all SubCategory details.
	 * @param subCategoryType of type String.
	 * @param categoryID of type integer
	 */
	@RequestMapping(value = "/addSubCategory")
	public @ResponseBody int getAddSubCategory(@RequestParam("categoryID") int categoryID , @RequestParam("subCategoryType") String subCategoryType)
	{
		return otherDetails.addSubCategory(categoryID, subCategoryType);
	}
	
	
	/**
	 *  getBrand method provides user all Brand details.
	 */
	@RequestMapping(value = "/getOnlyBrand")
	public @ResponseBody List<BrandsOnly> getBrand()
	{
		return brandInfo.onlyBrands();
	}
	
	
	/**
	 *  getCategory method provides user all category details.
	 */
	@RequestMapping(value = "/getCategoryOnly")
	public @ResponseBody List<CategoryType> getCategory()
	{
		return otherDetails.getCategory();
	}
	
	
	/**
	 *  getSubCategory method provides user all subcategory details.
	 */
	@RequestMapping(value = "/getSubCategoryOnly")
	public @ResponseBody List<SubCategoryType> getSubCategory()
	{
		return otherDetails.getSubCategory();
	}
	
	
	/**
	 *  getAdminSubCategory method provides user all subcategory details.
	 *  @param model of type Model
	 *  @param session of type HttpSession
	 */
	@RequestMapping(value = "/adminSubCategory"  , method = RequestMethod.GET)
	public String getAdminSubCategory(Model model , HttpSession session)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminSubCategory";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  getAdminEditForm method provides user to edit details of product.
	 *  @param model of type Model
	 *  @param session of type HttpSession
	 */
	@RequestMapping(value = "/adminEditForm" , method = RequestMethod.GET)
	public String getAdminEditForm(Model model , HttpSession session)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminEdit";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  getAdminRemoveForm method provides user to remove product.
	 *  @param model of type Model
	 *  @param session of type HttpSession
	 */
	@RequestMapping(value = "/adminRemoveForm" , method = RequestMethod.GET)
	public String getAdminRemoveForm(Model model , HttpSession session)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminRemove";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  postAdminRemoveForm method provides user to remove product.
	 *  @param model of type Model
	 *  @param productID of type integer
	 *  @param session of type HttpSession
	 */
	@RequestMapping(value = "/adminRemoveForm" , method = RequestMethod.POST)
	public String postAdminRemoveForm(Model model , @RequestParam("products") int productID , HttpSession session)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
		otherDetails.deleteProduct(productID);
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminRemove";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  postAdminEditForm method provides user to edit details of product.
	 *  @param model of type Model
	 *  @param productID of type integer
	 *  @param productDesc of type String
	 *  @param unitPrice of type BigDecimal
	 *  @param unitInStock of type integer
	 *  @param discount of type BigInteger
	 *  @param thumbNailPhoto of type String
	 *  @param largePhoto of type String
	 *  @param session of type HttpSession
	 */
	@RequestMapping(value = "/adminEditForm" , method = RequestMethod.POST)
	public String postAdminEditForm(Model model , HttpSession session , @RequestParam("products") int productID ,@RequestParam("productDesc") String productDesc , @RequestParam("unitPrice") BigDecimal unitPrice , @RequestParam("unitInStock") int unitInStock , @RequestParam("discount") BigDecimal discount , @RequestParam("thumbNailPhoto") String thumbNailPhoto , @RequestParam("largePhoto") String largePhoto)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
		otherDetails.updateProduct(productID, productDesc, unitPrice, discount, unitInStock);
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminEdit";
		}else{
			return "redirect:signin.html";
		}
	}
	
	

	/**
	 *  getSubCategoryBasedOnCategoryID method provides user to subCategory details of based on categoryID.
	 *  @param categoryID of type integer
	 */
	@RequestMapping(value = "/getSubCategoryBasedOnCategoryID")
	public @ResponseBody List<SubCategoryType> getSubCategoryBasedOnCategoryID(@RequestParam("categoryID") int categoryID)
	{
		return otherDetails.getSubCategory(categoryID);
	}
	
	
	/**
	 *  getProductInfoBySubCategoryID method provides user to product details of based on subcategoryID.
	 *  @param subCategoryID of type integer
	 */
	@RequestMapping(value = "getProductInfoBySubCategoryID" , method = RequestMethod.GET)
	public @ResponseBody List<ProductDetails> getProductInfoBySubCategoryID(@RequestParam("subCategoryID") int subCategoryID)
	{
		return productInfo.getOnlyProductDetails(subCategoryID);
	}
	
	
	/**
	 *  getProductInfoByProductID method provides user to product details of based on productID.
	 *  @param productID of type integer
	 */
	@RequestMapping(value = "getProductInfoByProductID" , method = RequestMethod.GET)
	public @ResponseBody List<ProductDetails> getProductInfoByProductID(@RequestParam("productID") int productID)
	{
		return productInfo.getOnlyProductDetailsByProductID(productID);
	}
	
	
	
	/**
	 *  getAdminAdd method provides user to add new products.
	 *  @param model of type Model
	 *  @param session of type HttpSession
	 */
	@RequestMapping(value = "adminAdd" , method = RequestMethod.GET)
	public String getAdminAdd(Model model , HttpSession session)
	{
		if(session.getAttribute("isUserLoggedIn") != null && session.getAttribute("role").toString().compareTo("admin")==0)
		{
		model.addAttribute("category" , otherDetails.getCategory());
		model.addAttribute("brand", brandInfo.onlyBrands());
		model.addAttribute("shipping", allInfo.shippingCountList());
		model.addAttribute("supplier", allInfo.supplierCountList());
		return "adminAdd";
		}
		else
		{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  addProductToDB method provides user to add details of product to database.
	 *  @param model of type Model
	 *  @param productID of type integer
	 *  @param productDesc of type String
	 *  @param unitPrice of type BigDecimal
	 *  @param unitInStock of type integer
	 *  @param discount of type BigInteger
	 *  @param thumbNailPhoto of type String
	 *  @param thumbNailPhotoType of type String
	 *  @param largePhoto of type String
	 *  @param largePhotoType of type String
	 *  @param brandId of type integer
	 *  @param supplierID of type integer
	 *  @param shippingID of type integer
	 */
	@RequestMapping(value = "/addToDatabase" , method = RequestMethod.GET)
	public List<String> addProductToDB(@RequestParam("productName") String productName , @RequestParam("productDesc") String productDesc , @RequestParam("supplierID") int supplierID , @RequestParam("subCategoryID") int subcategoryID , @RequestParam("unitPrice") BigDecimal unitPrice , @RequestParam("thumbNail") String thumbNail , @RequestParam("thumbNailType") String thumbNailType , @RequestParam("largePhotoType") String largePhotoType , @RequestParam("largePhoto") String largePhoto , @RequestParam("quantity") int quantity , @RequestParam("discount") BigDecimal discount , @RequestParam("rating") int rating , @RequestParam("brandID") int brandID)
	{
		int temp = otherDetails.addProduct(productName, productDesc, supplierID, subcategoryID, unitPrice, thumbNail, thumbNailType, largePhotoType, largePhoto, quantity, discount, rating, brandID);	
		List<String> list = new ArrayList<>();
		if(temp > 0)
		{
			list.add("success");
			return list;
		}
		else
		{
			list.add("fail");
			return list;
		}
	}
	
	
	/**
	 *  getAdminProductEdit method provides user to get new details of product after edit.
	 *  @param model of type Model
	 *  @param productID of type integer
	 *  @param session of type HttpSession
	 */
	
	@RequestMapping(value = "/adminProductEdit" , method = RequestMethod.GET)
	public String getAdminProductEdit(@RequestParam("productID") int productID , Model model , HttpSession session)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
			model.addAttribute("productInfo" , productInfo.getOnlyProductDetailsByProductID(productID));
		return "adminProductEdit";
		}else{
			return "redirect:signin.html";
		}
	}
	
	
	/**
	 *  editProductDirect method provides user to edit details of product to directly from user_action_single page.
	 *  @param model of type Model
	 *  @param productID of type integer
	 *  @param productDesc of type String
	 *  @param unitPrice of type BigDecimal
	 *  @param unitInStock of type integer
	 *  @param discount of type BigInteger
	 *  @param thumbNailPhoto of type String
	 *  @param thumbNailPhotoType of type String
	 *  @param largePhoto of type String
	 *  @param largePhotoType of type String
	 *  @param brandId of type integer
	 *  @param supplierID of type integer
	 *  @param shippingID of type integer
	 */

	@RequestMapping(value = "/editProductDirect" , method = RequestMethod.GET)
	public List<String> editProductDirect(@RequestParam("productID") int productID, @RequestParam("productDesc") String productDesc , @RequestParam("unitPrice") BigDecimal unitPrice , @RequestParam("thumbNailPhoto") String thumbNail ,  @RequestParam("largePhoto") String largePhoto , @RequestParam("unitInStock") int stock , @RequestParam("discount") BigDecimal discount )
	{
		int temp = otherDetails.editProductSinglePage(productID, productDesc, unitPrice, stock, discount, thumbNail, largePhoto);
		List<String> list = new ArrayList<>();
		if(temp > 0)
		{
			list.add("success");
			return list;
		}
		else
		{
			list.add("fail");
			return list;
		}
	}
	
	
	/**
	 *  deleteFromUserPage method provides user to delete product to directly from user_action_single page.
	 *  @param session of type HttpSession
	 *  @param productID of type integer
	 */
	@RequestMapping(value = "/user_action_single_delete" , method = RequestMethod.GET)
	public String deleteFromUserPage(@RequestParam("removeProduct") int productID , HttpSession session)
	{
		if(session.getAttribute("role").toString().compareTo("admin")==0)
		{
			System.out.println(session.getAttribute("tempPath").toString());
			otherDetails.deleteProduct(productID);
			return "redirect:"+session.getAttribute("tempPath").toString();
		}else{
			return "signin.html";
		}
	}
	
	
	/**
	 *  getAllOnlineUsers method provides user to get number of active users.
	 */
	@RequestMapping(value = "/onlineUsers" , method = RequestMethod.GET)
	public @ResponseBody int getAllOnlineUsers()
	{
		return allInfo.onlineUsers();
	}
	
	
	
	/**
	 *  getUserOffline method makes user offline when browser is closed.
	 */
	@RequestMapping(value = "/onWindowClose" , method = RequestMethod.GET)
	public @ResponseBody void getUserOffline(HttpSession session)
	{
		if(session.getAttribute("isLoggedInUser")!=null && (boolean)session.getAttribute("isLoggedInUser"))
		{
		 allInfo.changeUserStatusToOffline((int)session.getAttribute("userID"));;
		}
	}
}
