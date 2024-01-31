package com.zerobase.townclub.persist;

import com.zerobase.townclub.persist.entity.Sms;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Long> {
  Optional<Sms> findFirst1ByPhoneNumOrderByCreatedAtDesc(String phoneNum);
  }
