package com.zerobase.townclub.persist;

import com.zerobase.townclub.persist.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByPhoneNum(String phoneNum);
  boolean existsByPhoneNum(String phoneNum);
}
