package lk.ijse.userservice.repostory;

import lk.ijse.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceDAO extends JpaRepository<UserEntity,String> {
}
