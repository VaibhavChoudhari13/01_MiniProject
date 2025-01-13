package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.UserAccount;

public interface UserAccountService {
	
	
	public String saveorUpdateUserAcc(UserAccount useAcc);  // This is Upsert method
	
	public List<UserAccount> getAllUserAccounts();
	
	public UserAccount getUserAcc(Integer userId);
		
	public boolean deleteUserAccount(Integer userId);
	
	public boolean updateUserAccStatus(Integer userId , String actSwitch);
	
	
	

}
