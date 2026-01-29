package ru.rssib.bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rssib.bonus.entity.Operation;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByCardId(Long cardId);
}
