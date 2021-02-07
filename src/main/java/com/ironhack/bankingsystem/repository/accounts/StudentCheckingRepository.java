package com.ironhack.bankingsystem.repository.accounts;


import com.ironhack.bankingsystem.model.account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking,Integer> {

}
