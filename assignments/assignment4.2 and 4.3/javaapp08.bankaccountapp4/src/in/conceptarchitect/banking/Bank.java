package in.conceptarchitect.banking;

public class Bank {

	
	private String name;
	private double rate;
	

	public Bank(String name, double rate) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.rate=rate;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public double getRate() {
		// TODO Auto-generated method stub
		return rate;
	}
	
	int accountCount=0, accountAdded = 0;	
	BankAccount [] accounts=new BankAccount[100];


	public int openAccount(String name, String password, double amount) {
		// TODO Auto-generated method stub
		int accountNumber= ++accountAdded;
		accountCount++;
		var account=new BankAccount(accountNumber, name,password,amount);
		accounts[accountNumber]=account;
		return accountNumber;
	}
	
	public double closeAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		if(accounts[accountNumber] == null) return -1;
		if(accountNumber<1 || accountNumber>accountAdded)
			return -1;
		
		var account=accounts[accountNumber];
		if(!account.authenticate(password))
			return -1;
		accounts[accountNumber]=null;
		accountCount--;
		return account.getBalance();
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}

	public BankAccount getAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub

		return accounts[accountNumber];
	}

	public double getBalance(int accountNumber, String password) {
		if(accounts[accountNumber] == null || !(accounts[accountNumber].authenticate(password))) return -1;
		return accounts[accountNumber].getBalance();
	}

	public void creditInterest() {
		// TODO Auto-generated method stub
		for(int i = 0; i < accounts.length; i++) {
			if(accounts[i] != null)
				accounts[i].creditInterest(rate);
		}
	}
	
	public boolean deposit(int accountNumber, double amount) {
		// TODO Auto-generated method stub
		if(accounts[accountNumber] != null &&amount > 0) {
			accounts[accountNumber].deposit((int)amount);
			return true;
		}	
		return false;
	}

	public Response withdraw(int accountNumber, double amount, String password) {
		// TODO Auto-generated method stub
		if(accountNumber >= 0 && accounts[accountNumber] != null) {
			return accounts[accountNumber].withdraw((int)amount, password);
		}
		return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
	}

	public Response transfer(int accountNumber, int amount, String password, int targetAccount) {
		// TODO Auto-generated method stub
		if(accountNumber >= 0 && targetAccount >= 0 && accounts[accountNumber] != null && accounts[targetAccount] != null) {
			Response result = accounts[accountNumber].withdraw((int)amount, password);
			if(result.getCode() == ResponseStatus.SUCCESS) {
				accounts[targetAccount].deposit((int)amount);
			}
			return result;
		}
		return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
	}
	
	public BankAccount[] viewAccount() {
		return accounts;
	}

}















