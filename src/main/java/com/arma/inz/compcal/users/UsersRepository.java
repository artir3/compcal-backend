package com.arma.inz.compcal.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<BaseUser, Long> {
    BaseUser findByEmail(String email);

    @Query("select u.password from BaseUser u where u.email = :email")
    String findPasswordByEmail(@Param("email") String email);

    BaseUser findOneByEmail(String email);

    BaseUser findOneByHash(String hash);

}

