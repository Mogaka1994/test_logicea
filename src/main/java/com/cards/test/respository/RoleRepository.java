package com.cards.test.respository;

import com.cards.test.entity.Roles;
import com.cards.test.util.Erole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByName(Erole name);


}
