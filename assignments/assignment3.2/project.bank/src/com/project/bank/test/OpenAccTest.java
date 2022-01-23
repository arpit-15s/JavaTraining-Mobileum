package com.project.bank.test;

import org.junit.Test;

import com.project.bank.service.Bank;
import static org.junit.Assert.*;

public class OpenAccTest {
	
	Bank bank = new Bank("ICICI Bank", "12345ax", "Indira Nagar");
	
	public OpenAccTest() {
		// TODO Auto-generated constructor stub
		this.bank.openAccount(1234554321, "Aman", "1441", 100000, 10.6);
//		this.bank.openAccount(1221212121, "Arpit", "1143", 100600, 9.2);
//		this.bank.openAccount(876543283, "Salman", "7868", 100070, 12);
	}

	@Test
	public void test1() {
		int acc = 1234554322;
		String name = "aman";
		String password = "1441";
		double balance= 10;
		double interestRate = 0.1;
		boolean result = bank.openAccount(acc, name, password, balance, interestRate);
		System.out.println(result);
		assertEquals(true, result);
	}
	
	@Test
	public void test2() {
		int acc = 1234554321;
		String name = "Karan";
		String password = "1442";
		double balance= 10000;
		double interestRate = 9.1;
		boolean result = bank.openAccount(acc, name, password, balance, interestRate);
		System.out.println(result);
		assertEquals(false, result);
	}
}
