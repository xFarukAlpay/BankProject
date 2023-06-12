package Accounts;

import java.time.LocalDate;
import java.time.Period;

import bank.Bank;

public class ShortTermAccount extends Account
{
	
	public ShortTermAccount(int Id, int balance) 
	{
		// TODO Auto-generated constructor stub
		super.setAccountId(Id);
		super.setBakiye(balance);
		super.type =AccountType.SHORTTERM;
		
	}
	@Override
	public void accountRules() 
	{
		// TODO Auto-generated method stub
		System.out.println("Yillik %17 faiz verir ve en az 1000 tl bakiye olmak zorundadir.");
	}
	
	@Override
	
    public float benefit() {
        LocalDate lastInput = Bank.setAccountDate(); // Bank sınıfındaki lastInputDate'e erişmek için bu metodu kullanabilirsiniz

        int year = Period.between(super.opening_account_date, lastInput).getYears();
        int month = Period.between(super.opening_account_date, lastInput).getMonths();
        int day = Period.between(super.opening_account_date, lastInput).getDays();

        int total_days = year * 365 + month * 30 + day;

        double faiz_miktari = ((getBalance() * ((double)17 / 365) / 100) * total_days);

        return (float)faiz_miktari;
    } 
}

