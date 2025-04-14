package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
}
