package org.launchcode.PetLife.models.data;

import org.launchcode.PetLife.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository <User, Long>{
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    Boolean existsByEmail(String email);

    public User findByResetPasswordToken(String token);


}
