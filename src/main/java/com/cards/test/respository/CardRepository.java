package com.cards.test.respository;

import com.cards.test.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CardRepository  extends JpaRepository<Card,Long> {

    @Query(value = "SELECT * FROM cards WHERE user_id =?1", nativeQuery = true)
    List<Card> findByMail(Long user_id);

    Page<Card> findCardsByName(String name, Pageable pageable);

    Page<Card> findCardByColor(String color, Pageable pageable);
}
