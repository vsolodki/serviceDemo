package cz.spring.demo.repositories;

import cz.spring.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> getTransactionById(Long id);
    List<Transaction> getTransactionsByActor(String actor);
    List<Transaction> getTransactionsByType(String type);
    void removeTransactionById(Long id);
}
