package Accounts;
import java.time.LocalDate;
import java.time.Period;

import bank.Bank;

public class LongTermAccount extends Account {
    
    public LongTermAccount(int id, int balance) {
        super.setAccountId(id);
        super.setBakiye(balance);
        super.type = AccountType.LONGTERM;
    }

    @Override
    public void accountRules() {
        System.out.println("Yıllık %24 faiz verir ve en az 1500 TL bakiye olmak zorundadır.");
    }



    @Override
    public float benefit() {
        LocalDate lastInput = Bank.setAccountDate(); // Bank sınıfındaki lastInputDate'e erişmek için bu metodu kullanabilirsiniz

        int year = Period.between(super.opening_account_date, lastInput).getYears();
        int month = Period.between(super.opening_account_date, lastInput).getMonths();
        int day = Period.between(super.opening_account_date, lastInput).getDays();

        int total_days = year * 365 + month * 30 + day;

        double faiz_miktari = ((getBalance() * ((double)24 / 365) / 100) * total_days);

        return (float)faiz_miktari;
    } 
}
