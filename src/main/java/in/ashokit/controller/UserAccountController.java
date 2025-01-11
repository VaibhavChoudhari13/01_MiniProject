package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.UserAccount;
import in.ashokit.service.UserAccountService;

@Controller
public class UserAccountController {
	
	@Autowired
	private UserAccountService userAccSer; 

	@GetMapping("/")
	public String index(Model model) {
	
		UserAccount us = new UserAccount();
		
		model.addAttribute("user", us);  // to load the page with empty binding object
		
		return "index" ;
	
	}
	
	@PostMapping("/save-user")
	public String handleSubmitBtn(@ModelAttribute("user") UserAccount userAcc ,Model model ) {
		
		System.out.println(userAcc);
		
		String msg = userAccSer.saveorUpdateUserAcc(userAcc);
		
		model.addAttribute("msg", msg);  // to represent success msg on UI 
		
		UserAccount us = new UserAccount();  

		model.addAttribute("user", us); // once success msg displayed on UI , to load the blank form 
		
		return "index";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		
		List<UserAccount> allUserAccounts = userAccSer.getAllUserAccounts();
		
		for(UserAccount user:allUserAccounts) {
			
			System.out.println(user.getUserId());
			System.out.println(user.getFullName());
			
		}
		
		model.addAttribute("users", allUserAccounts);
		
		return "view-users" ;
	}
	
	@GetMapping("/edit")
	public String editUserAccount(@RequestParam("id") Integer userId ,Model model) {
		
		UserAccount userAcc = userAccSer.getUserAcc(userId);
		
		model.addAttribute("user", userAcc);
		
		return "index" ;
	}
	
	@GetMapping("/delete")
	public String deleteUserAccount(@RequestParam ("id") Integer userId , Model model) {
		
		boolean deleteUserAccount = userAccSer.deleteUserAccount(userId);
		
		model.addAttribute("msg", "User Record Deleted Successfully !!");
		
		return "forward:/users";
		
	}
	
	@GetMapping("/update")											
	public String updateUserAccStaus(@RequestParam ("id") Integer userId ,
									 @RequestParam ("actSwitch") String actSwitch , Model model) {
		
		boolean updateUserAccStatus = userAccSer.updateUserAccStatus(userId, actSwitch);
		
		if("Y".equals(actSwitch)) {
			
			model.addAttribute("msg1", "User Account Activated!!");
		
		} else {
			
			model.addAttribute("msg1", "User Account De-Activated!!");
		}
		
		
		return "forward:/users";
	}
}
