package ru.rssib.bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rssib.bonus.entity.Card;

import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findCardByNumber(String cardNumber);
}
