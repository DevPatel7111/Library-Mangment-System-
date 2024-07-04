package org.dev.JBDL_Lec14.controller;

import jakarta.validation.Valid;
import org.dev.JBDL_Lec14.dto.TxnRequest;
import org.dev.JBDL_Lec14.exception.TxnException;
import org.dev.JBDL_Lec14.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
public class TxnController {
    @Autowired
    private TxnService txnService;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid TxnRequest txnRequest) throws TxnException {
        String txnId =  txnService.create(txnRequest);
        return new ResponseEntity<>(txnId, HttpStatus.OK);
    }
    @PutMapping("/returnBook")
    public ResponseEntity<Integer> returnBook(@RequestBody TxnRequest txnRequest) throws TxnException {
        int txnId = txnService.returnBook(txnRequest);
        return new ResponseEntity<>(txnId, HttpStatus.OK);
    }

}
