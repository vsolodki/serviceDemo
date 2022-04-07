package cz.spring.demo.controllers;

import cz.spring.demo.entities.Transaction;
import cz.spring.demo.repositories.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TransactionController {
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @PostMapping("transactions/transaction/create")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction){
        try{
            Transaction newTransaction = transactionRepository.save(new Transaction(transaction.getTimestamp(), transaction.getActor(), transaction.getType(), transaction.getData()));
            return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("transactions/transaction/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Long id){
        Optional<Transaction> transaction = transactionRepository.getTransactionById(id);
        if (transaction.isPresent()){
            return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("transactions/type/{type}")
    public ResponseEntity<List<Transaction>> getByType(@PathVariable String type){
        try {
            return new ResponseEntity<>(transactionRepository.getTransactionsByType(type), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("transactions/actor/{actor}")
    public ResponseEntity<List<Transaction>> getByActor(@PathVariable String actor){
        try {
            return new ResponseEntity<>(transactionRepository.getTransactionsByActor(actor), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("transactions")
    public ResponseEntity<List<Transaction>> getAll(){
        try {
            return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/transactions/transaction/update/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") Long id, @RequestBody Transaction transaction) {
        Optional<Transaction> transactionData = transactionRepository.findById(id);
        if (transactionData.isPresent()) {
            Transaction updatedTransaction = transactionData.get();
            updatedTransaction.setTimestamp(transaction.getTimestamp());
            updatedTransaction.setType(transaction.getType());
            updatedTransaction.setActor(transaction.getActor());
            updatedTransaction.setData(transaction.getData());
            return new ResponseEntity<>(transactionRepository.save(updatedTransaction), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("transactions/transaction/remove/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable Long id){
        try {
            transactionRepository.removeTransactionById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
