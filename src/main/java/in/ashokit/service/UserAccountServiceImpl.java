package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.UserAccount;
import in.ashokit.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	private UserAccountRepository userAccRepo ;

	
	@Override
	public String saveorUpdateUserAcc(UserAccount userAcc) {
		
	
		
		Integer userId = userAcc.getUserId();
		
		userAccRepo.save(userAcc);
		
		if(userId == null) {
		
			return "Record Saved Successfully!!";
		}
		
		else {
		
			return "Record Updated Successfully !!";
		}
	}

	
	@Override
	public List<UserAccount> getAllUserAccounts() {
		
		List<UserAccount> listUsers = userAccRepo.findAll();  

		// Task 101 - findAll method is available in JPA repository
		
		return listUsers;
	}
	

	@Override
	public UserAccount getUserAcc(Integer userId) {
		
		Optional<UserAccount> userAcc = userAccRepo.findById(userId);
		
		if(userAcc.isPresent()) {
			
			return 	userAcc.get() ;
		}
		
		return null;
	}

	
	@Override
	public boolean deleteUserAccount(Integer userId) {
		
		boolean existsById = userAccRepo.existsById(userId);
		
		if(existsById) {
	
			userAccRepo.deleteById(userId);
			
			return true ;
		}
		
		return false;
	}

	
	
	@Override
	public boolean updateUserAccStatus(Integer userId, String actSwitch) {

		try {
		
		userAccRepo.updateUserAccStatus(userId ,actSwitch );
		
		}
		
		catch(Exception e ) {
			
			e.printStackTrace();
		}
		return true ;
	}

}
