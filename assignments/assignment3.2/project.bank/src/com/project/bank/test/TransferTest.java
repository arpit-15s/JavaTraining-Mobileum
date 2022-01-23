package com.project.bank.test;

import org.junit.Test;

import com.project.bank.service.Bank;
import static org.junit.Assert.*;

public class TransferTest {
	
	Bank bank = new Bank("Axis Bank", "12345ax", "Indira Nagar");
	
	public TransferTest() {
		// TODO Auto-generated constructor stub
		this.bank.openAccount(1234554321, "Aman", "1441", 100000, 10.6);
		this.bank.openAccount(1221212121, "Arpit", "1143", 100600, 9.2);
		this.bank.openAccount(876543283, "Salman", "7868", 100070, 12);
	}

	@Test
	public void test1() {
		int acc1 = 1234554321;
		int acc2 = 1221212121;
		String password = "1441";
		int amount = 5000;
		boolean result = bank.transfer(acc1, acc2, password, amount);
		assertEquals(true, result);
	}
	
	@Test
	public void test2() {
		int acc1 = 1234554322;
		int acc2 = 1221212121;
		String password = "1441";
		int amount = 5000;
		boolean result = bank.transfer(acc1, acc2, password, amount);
		assertEquals(false, result);
	}
	
	@Test
	public void test3() {
		int acc1 = 1234554322;
		int acc2 = 1221212121;
		String password = "1441";
		int amount = 5000000;
		boolean result = bank.transfer(acc1, acc2, password, amount);
		assertEquals(false, result);
	}
	
	@Test
	public void test4() {
		int acc1 = 1234554322;
		int acc2 = 1221212121;
		String password = "1444";
		int amount = 5000;
		boolean result = bank.transfer(acc1, acc2, password, amount);
		assertEquals(false, result);
	}
	
	@Test
	public void test5() {
		int acc1 = 1234554322;
		int acc2 = 1221212121;
		String password = "1441";
		int amount = -1;
		boolean result = bank.transfer(acc1, acc2, password, amount);
		assertEquals(false, result);
	}
}
