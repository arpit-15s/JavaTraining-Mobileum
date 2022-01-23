package com.project.bank.test;

import org.junit.Test;

import com.project.bank.service.Bank;
import static org.junit.Assert.*;

public class CloseAccTest {
	
	Bank bank = new Bank("ICICI Bank", "12345ax", "Indira Nagar");
	
	public CloseAccTest() {
		// TODO Auto-generated constructor stub
		this.bank.openAccount(1234554321, "Aman", "1441", 100000, 10.6);
//		this.bank.openAccount(1221212121, "Arpit", "1143", 100600, 9.2);
//		this.bank.openAccount(876543283, "Salman", "7868", 100070, 12);
	}

	@Test
	public void test1() {
		int acc = 1234554321;
		boolean result = bank.closeAccount(acc);
		System.out.println(result);
		assertEquals(true, result);
	}
	
	@Test
	public void test2() {
		int acc = 1234554322;
		boolean result = bank.closeAccount(acc);
		System.out.println(result);
		assertEquals(false, result);
	}
}
