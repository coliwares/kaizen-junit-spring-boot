# Kata Junit - Mockito - JaCoCo  sobre Spring Boot
Este repositorio está diseñado para aplicar de manera práctica conceptos de Junit y Mockito mediante un código simple comprendido por Entidades - Service y Repository sobre framework de Spring Boot.
En este documento se describe el paso a paso de la Kata y su aplicación.

### Implementaciones iniciales
> **Account.java  :** 
```java
package  com.example.app.junit.junitspringboot.models;
import  java.math.BigDecimal;
import  com.example.app.junit.junitspringboot.exceptions.InsufficientMoneyException;
public  class  Account {
	private  Long  id;
	private  String  person;
	private  BigDecimal  balance;
	public  Long  getId() {
		return  id;
	}
	public  void  setId(Long  id) {
		this.id = id;
	}
	public  String  getPerson() {
		return  person;
	}
	public  void  setPerson(String  person) {
		this.person = person;
	}
	public  BigDecimal  getBalance() {
		return  balance;
	}
	public  void  setBalance(BigDecimal  balance) {
		this.balance = balance;
	}
	public  Account() {
	}
	public  Account(Long  id, String  person, BigDecimal  balance) {
		this.id = id;
		this.person = person;
		this.balance = balance;
	}
	@Override
	public  String  toString() {
		return  "Account [balance=" + balance + ", id=" + id + ", person=" + person + "]";
	}
	@Override
	public  int  hashCode() {
		final  int  prime = 31;
		int  result = 1;
		result = prime * result + ((balance == null) ?  0  :  balance.hashCode());
		result = prime * result + ((id == null) ?  0  :  id.hashCode());
		result = prime * result + ((person == null) ?  0  :  person.hashCode());
		return  result;
	}
	@Override
	public  boolean  equals(Object  obj) {
		if (this == obj)
			return  true;
		if (obj == null)
			return  false;
		if (getClass() != obj.getClass())
			return  false;
		Account  other = (Account) obj;
		if (balance == null) {
				if (other.balance != null)
					return  false;
		} else  if (!balance.equals(other.balance))
			return  false;
		if (id == null) {
			if (other.id != null)
				return  false;
		} else  if (!id.equals(other.id))
			return  false;
		if (person == null) {
			if (other.person != null)
				return  false;
		} else  if (!person.equals(other.person))
			return  false;
		return  true;
	}
	public  void  withdraw(BigDecimal  amount) {
		BigDecimal  newBalance = this.balance.subtract(amount);
		if(newBalance.compareTo(BigDecimal.ZERO) < 0){
			throw  new  InsufficientMoneyException("Insufficient money in the account");
		}
		this.balance = newBalance;
	}
	public  void  deposit(BigDecimal  amount) {
		this.balance = this.balance.add(amount);
	}
}
`````
> **Bank.java  :** 
````java
package  com.example.app.junit.junitspringboot.models;
public  class  Bank {
	private  Long  id;
	private  String  name;
	private  int  transferQty;

	public  Long  getId() {
		return  id;
	}

	public  void  setId(Long  id) {
		this.id = id;
	}

	public  String  getName() {
		return  name;
	}

	public  void  setName(String  name) {
		this.name = name;
	}

	public  int  getTransferQty() {
		return  transferQty;
	}

	public  void  setTransferQty(int  transferQty) {
		this.transferQty = transferQty;
	}

	public  Bank() {
	}

	public  Bank(Long  id, String  name, int  transferQty) {
		this.id = id;
		this.name = name;
		this.transferQty = transferQty;
	}

	@Override
	public  String  toString() {
		return  "Bank [id=" + id + ", name=" + name + ", transferQty=" + transferQty + "]";
	}

	@Override
	public  int  hashCode() {
		final  int  prime = 31;
		int  result = 1;
		result = prime * result + ((id == null) ?  0  :  id.hashCode());
		result = prime * result + ((name == null) ?  0  :  name.hashCode());
		result = prime * result + transferQty;
		return  result;
	}

	@Override
	public  boolean  equals(Object  obj) {
		if (this == obj)
			return  true;
		if (obj == null)
			return  false;
		if (getClass() != obj.getClass())
			return  false;
		Bank  other = (Bank) obj;
		if (id == null) {
			if (other.id != null)
				return  false;
		} else  if (!id.equals(other.id))
			return  false;
		if (name == null) {
			if (other.name != null)
				return  false;
		} else  if (!name.equals(other.name))
			return  false;
		if (transferQty != other.transferQty)
			return  false;
		return  true;
	}
}
`````
> **AccountRepository.java  :** 
```java
public  interface  AccountRepository {
	List<Account> findAll();
	Account  findById(Long  id);
	void  update(Account  account);
}
`````
> **BankRepository .java  :** 
```java
public  interface  BankRepository {
	List<Bank> findAll();
	Bank  findById(Long  id);
	void  update(Bank  bank);
}
`````
> **AccountService .java  :** 
```java
public  interface  AccountService {
Account  findById(Long  id);
int  checkTotalTransfers(Long  bankId);
BigDecimal  checkBalance(Long  accountId);
void  bankTransfer(Long  OriginAccountNumber, Long  targetAccountNumber, BigDecimal  amount, Long bankId);
}
`````
> **AccountServiceImpl.java  :** 
```java
public  class  AccountServiceImpl  implements  AccountService{

	private  AccountRepository  accountRepository;
	private  BankRepository  bankRepository;

	public  AccountServiceImpl(AccountRepository  accountRepository, BankRepository  bankRepository) {
		this.accountRepository = accountRepository;
		this.bankRepository = bankRepository;
	}
	  
	@Override
	public  Account  findById(Long  id) {
		return  accountRepository.findById(id);
	}
	  
	@Override
	public  int  checkTotalTransfers(Long  bankId) {
		Bank  bank = bankRepository.findById(bankId);
		return  bank.getTransferQty();
	}
	  
	@Override
	public  BigDecimal  checkBalance(Long  accountId) {
		Account  account = accountRepository.findById(accountId);
		return  account.getBalance();
	}
	  
	@Override
	public  void  bankTransfer(Long  originAccountNumber, Long  targetAccountNumber, BigDecimal  amount, Long bankId) {
		Bank  bank = bankRepository.findById(bankId);
		int  transferQty = bank.getTransferQty();
		bank.setTransferQty(++transferQty);
		bankRepository.update(bank);
		  
		Account  originAccount = accountRepository.findById(originAccountNumber);
		Account  targetAccount = accountRepository.findById(targetAccountNumber);
		  
		originAccount.withdraw(amount);
		accountRepository.update(originAccount);
		  
		targetAccount.deposit(amount);
		accountRepository.update(targetAccount);
	}
}
````
> **InsufficientMoneyException.java**
````java
public  class  InsufficientMoneyException  extends  RuntimeException {
	public  InsufficientMoneyException(String  message) {
		super(message);
	}
}
````