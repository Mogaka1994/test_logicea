package com.cards.test.respository;

import com.cards.test.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT * FROM cards WHERE user_id =?1", nativeQuery = true)
    List<Card> findByMail(Long user_id);

    //    @Query(value = "SELECT * FROM cards WHERE name =?1 AND user_id =?1", nativeQuery = true)
//    Page<Card> findCardsByName(String status,Long user_id, Pageable pageable);
//
    List<Card> findByColor(String color, Pageable pageable);

//
//    @Query(value = "SELECT * FROM cards WHERE color =?1 AND user_id =?1", nativeQuery = true)
//    Page<Card> findByByColor(String color,Long user_id, Pageable pageable);

}


