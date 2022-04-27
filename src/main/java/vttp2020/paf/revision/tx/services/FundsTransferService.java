package vttp2020.paf.revision.tx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2020.paf.revision.tx.repositories.AccountRepository;

@Service
public class FundsTransferService {


    @Autowired
    private AccountRepository acctRepo;
     
    @Transactional
    public void transfer(String srcAcct, String destAcct, Float amount) {

        //Deduct from srcAcct
        try {
            acctRepo.withdraw(srcAcct, amount);
            if (amount > 100)
                throw new IllegalArgumentException("You cannot transfer more than $100");

            acctRepo.deposit(srcAcct, amount);
        } catch (Exception e) {
            e.printStackTrace();
            // if it throws exception, then the transation will rollback
           throw e;
        }
    }
    
    // if it exits here, then the transaction will commit 
}
