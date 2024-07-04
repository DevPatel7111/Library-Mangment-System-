package org.dev.JBDL_Lec14.repositary;

import org.dev.JBDL_Lec14.model.User;
import org.dev.JBDL_Lec14.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepositary extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where :q ", nativeQuery = true)
    List<User> findByNativeQuery(String q);

    User findByphoneNoAndUserType(String phoneNo , UserType type);
}
