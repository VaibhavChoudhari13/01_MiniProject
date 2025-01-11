package in.ashokit.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	@Modifying
	@Transactional
	@Query("update UserAccount set activeSw=:actSwitch where userId=:userId ")
	public void updateUserAccStatus(Integer userId , String actSwitch);
	
}
