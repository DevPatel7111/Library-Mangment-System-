package org.dev.JBDL_Lec14.repositary;

import org.dev.JBDL_Lec14.model.Transition;
import org.dev.JBDL_Lec14.model.TransitionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface TxnRepositary extends JpaRepository<Transition,Integer> {

    Transition findByUserPhoneNoAndBookBookNoAndTxnstatus(String userPhoneNo, String bookNo, TransitionStatus txnstatus);
}