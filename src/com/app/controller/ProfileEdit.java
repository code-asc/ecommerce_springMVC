package com.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.EditUserProfile;
import com.app.service.UpdateUserDetails;
import com.app.service.UserInfo;

@Controller
/**
 * The ProfileEdit class provides the customer to edit his profile
 */
public class ProfileEdit {
	
	@Autowired
	UserInfo info ;
	
	@Autowired
	UpdateUserDetails editUser;
	
	
	/**
	 *  onGetUserProfile method redirect the Customer to editProfile page on get request.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 * @param editProfile1 of type EditUserProfile. It is provided by the URL
	 */
	@RequestMapping(value = "/userProfileEdit" , method = RequestMethod.GET)
	public String onGetUserProfile(Model model , HttpSession session , @ModelAttribute("editProfile1") EditUserProfile editProfile1)
	{
		model.addAttribute("userInfo" , info.getUserDetails((int)session.getAttribute("userID")));
		return "userProfileEdit";
	}
	
	
	/**
	 *  onPostUserProfile method get the input fields from the editProfile page on post request.
	 * @param model of type Model. It is used to modify the view accordingly.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 * @param editProfile1 of type EditUserProfile. It is provided by the URL
	 */
	@RequestMapping(value="/userProfileEdit" , method = RequestMethod.POST)
	public String onPostUserProfile(@ModelAttribute("editProfile1") EditUserProfile editProfile1 , @RequestPart(value = "profilePhoto", required = false) MultipartFile file , Model model , HttpSession session)
	{
		  
		File dir = null;
		
		String firstName = editProfile1.getUserFirstName();
		String middleName = editProfile1.getUserMiddleName();
		String lastName = editProfile1.getUserLastName();
		String email = editProfile1.getUserEmail();
		String phone = editProfile1.getUserPhone();
		int userID = (int)session.getAttribute("userID");
		
		String fileName = editProfile1.getUserProfilePhoto().getOriginalFilename();
		editUser.setUserDetails(userID, firstName, middleName, lastName, email, phone, session);
		
		if(fileName != null && fileName != ""){
              try{
            	  
            	        BufferedOutputStream stream = null;
            	        String path = "C:/Users/mindfire/workspace/ProfileImage/"+session.getAttribute("userID").toString();
						//String path = "C:/Users/mindfire/workspace/ProjectDemo/WebContent/assets/usersInfo/ProfileImage/"+session.getAttribute("userID").toString();
						String completePath = path+"/"+fileName;
						byte[] photo = editProfile1.getUserProfilePhoto().getBytes();
						dir = new File(path);
						if(dir.exists() && (fileName != null && fileName != "")){
							File[] allFiles = dir.listFiles();
						
							for(File l : allFiles)
							{
								l.delete();
							}
						}
						else
						{
							dir.mkdir();
						}
					
						stream = new BufferedOutputStream(new FileOutputStream(new File(completePath)));
						stream.write(photo);
						stream.flush();
						stream.close();
						
						if(fileName != null && fileName != "")
						{	
							completePath = completePath.replace("C:/Users/mindfire/workspace/ProfileImage", "resources");
							editUser.setUserProfilePhoto(userID, completePath , session);
						}
						
						}catch(Exception e){
							e.printStackTrace();
						}
		}
		model.addAttribute("userInfo" , info.getUserDetails((int)session.getAttribute("userID")));
		return "userProfileEdit";
	}
	
}
