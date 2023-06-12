package Accounts;

import java.time.LocalDate;
import java.time.Period;

import bank.Bank;

public class SpecialAccount extends Account {

    public SpecialAccount(int Id, int balance) {
        super.setAccountId(Id);
        super.setBakiye(balance);
        super.type = AccountType.SPECİAL;
    }

    @Override
    public void accountRules() {
        System.out.println("%12 faiz verir ve en az hesap açtığındaki kadar para hesapta bakiye olması gerekiyor.");
    }


    @Override
    public float benefit() {
        LocalDate lastInput = Bank.setAccountDate(); // Bank sınıfındaki lastInputDate'e erişmek için bu metodu kullanabilirsiniz

        int year = Period.between(super.opening_account_date, lastInput).getYears();
        int month = Period.between(super.opening_account_date, lastInput).getMonths();
        int day = Period.between(super.opening_account_date, lastInput).getDays();

        int total_days = year * 365 + month * 30 + day;

        double faiz_miktari = ((getBalance() * ((double)12 / 365) / 100) * total_days);

        return (float)faiz_miktari;
    } 

}
