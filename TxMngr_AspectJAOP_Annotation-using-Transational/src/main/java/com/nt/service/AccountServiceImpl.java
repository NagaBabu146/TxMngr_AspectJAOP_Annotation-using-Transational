package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nt.dao.AccountDAO;
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
	private AccountDAO dao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String transferMoney(int srcNo, int destNo, int amt) {
		
		int count1=dao.withdraw(srcNo, amt);
		int count2=dao.deposit(destNo, amt);
		if(count1==0||count2==0) {
			throw new RuntimeException("Incomplete Details and Invalid account(Tx Rollback)");
		}else {
		System.out.println("MoneyTransfer Succesfully(Tx Commited)");	
		}
		
		return "Money Transfer Successfully";
	}

}
