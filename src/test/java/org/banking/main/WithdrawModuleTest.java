package org.banking.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.banking.main.dao.BankingOperations;
import org.banking.main.exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

public class WithdrawModuleTest {
	
	@Test
	public void testCase1() throws ClassNotFoundException, SQLException, InvalidAmountException
	{
		
		assertEquals(5000, BankingOperations.withdraw(342343242,5000));
	}

	
	@Test
	public void testCase2() throws ClassNotFoundException, SQLException, InvalidAmountException
	{
		
		assertEquals(5000, BankingOperations.withdraw(342343242,15000));
	}
}
