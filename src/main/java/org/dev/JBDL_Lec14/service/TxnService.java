package org.dev.JBDL_Lec14.service;

import jakarta.transaction.Transactional;
import org.dev.JBDL_Lec14.dto.TxnRequest;
import org.dev.JBDL_Lec14.exception.TxnException;
import org.dev.JBDL_Lec14.model.*;
import org.dev.JBDL_Lec14.repositary.TxnRepositary;
import org.dev.JBDL_Lec14.repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private TxnRepositary txnRepositary;
    @Value("${book.valid.upto}")
    private int bookValidUpto;

    @Value("${book.fine.amount.per.day}")
    private int bookFineAmountPerDay;



    // To check if the user is present or not
    public User getUserFromDB(TxnRequest txnRequest) throws TxnException {
        User userFromDb = userService.getStudentByPhoneNo(txnRequest.getUserPhoneNumber());
        if (userFromDb == null) {
            throw new TxnException("User is not registered");
        }
        return userFromDb;
    }

    // To check if the book is present or not
    public Book getBookFromDB(TxnRequest txnRequest) throws TxnException {
        List<Book> books = bookService.filter(BookFilterType.BOOK_NO, Operator.EQUALS, txnRequest.getBookNo());
        if (books.isEmpty()) {
            throw new TxnException("Book does not belong to my library");
        }
        Book bookFromDb = books.get(0);
        return bookFromDb;
    }

    // To create a transaction and save it
    @Transactional(rollbackOn = {TxnException.class})
    public String createTxn(User userFromDb, Book bookFromDb) throws TxnException {
        String txnId = UUID.randomUUID().toString();
        Transition txn = Transition.builder()
                .txnId(txnId)
                .user(userFromDb)
                .book(bookFromDb)
                .txnstatus(TransitionStatus.ISSUED)
                .build();

        // Save transaction to repository
        txnRepositary.save(txn);

        // Update book data to associate it with the user
        bookFromDb.setUser(userFromDb);
        bookService.updateBookData(bookFromDb);

        return txnId;
    }

    // A method to create a transaction
    public String create(TxnRequest txnRequest) throws TxnException {
        User userFromDb = getUserFromDB(txnRequest);
        Book bookFromDb = getBookFromDB(txnRequest);
        if (bookFromDb.getUser() != null) {
            throw new TxnException("Book already exists");
        }
        return createTxn(userFromDb, bookFromDb);
    }

    // A method to return a book
    public int returnBook(TxnRequest txnRequest) throws TxnException {
        User userFromDb = getUserFromDB(txnRequest);
        Book bookFromDb = getBookFromDB(txnRequest);
        if (bookFromDb.getUser() != userFromDb) {
            throw new TxnException("This was not the user who has issued this book");
        }
        Transition txn = txnRepositary.findByUserPhoneNoAndBookBookNoAndTxnstatus(txnRequest.getUserPhoneNumber(), txnRequest.getBookNo(),TransitionStatus.ISSUED);
           int fine = calculateFine(txn,bookFromDb.getSecurityAmount());
        // Additional logic for returning the book should be implemented here
        // Example:

        // - Update the book's user to null
        // - Update the transaction status to RETURNED
        if(fine == bookFromDb.getSecurityAmount()){
           txn.setTxnstatus(TransitionStatus.RETURN);
        }else {
            txn.setTxnstatus(TransitionStatus.FINED);
        }
        txn.setFineAmount(fine);
        bookFromDb.setUser(null);
        bookService.updateBookData(bookFromDb);
        return  fine;


        // - Save the changes to the repository

        // Return a proper status or ID if needed
    }

     public  int calculateFine(Transition txn, Integer securityAmount) {
        long issueDate = txn.getCreationDate().getTime();
        long returDate = System.currentTimeMillis();

        long timeDiff = returDate - issueDate;
        int daysPassed = (int) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        if (daysPassed>Integer.valueOf(bookValidUpto)){
            int fineAmount = (daysPassed - Integer.valueOf(bookValidUpto))*Integer.valueOf(bookFineAmountPerDay);
              return fineAmount;
        }
        return securityAmount;


    }
}
