package com.ironhack.bankingsystem.repository.user;


import com.ironhack.bankingsystem.model.user.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Integer> {

}
