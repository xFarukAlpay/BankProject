package Accounts;

import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Account {
    private int points;
    private int account_id;
    private int bakiye=0;
    protected AccountType type;
    public ArrayList<String> transactions;
    protected String openedDate;
    protected String DefaultopenedDate;
    DateTimeFormatter dateTimeFormatter;
    protected LocalDate opening_account_date;
    public LocalDate defaultDate;
    
    public Account() {
        transactions = new ArrayList<String>();
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        opening_account_date = LocalDate.of(2023, 5, 5); // Varsayılan açılış tarihi
        openedDate = LocalDate.now().format(dateTimeFormatter);
        DefaultopenedDate = opening_account_date.format(dateTimeFormatter);
    }

    // Özel bir açılış tarihi belirterek hesap oluşturur
    public Account(LocalDate customOpeningDate) {
        transactions = new ArrayList<String>();
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        opening_account_date = customOpeningDate;
        openedDate = LocalDate.now().format(dateTimeFormatter);
        DefaultopenedDate = opening_account_date.format(dateTimeFormatter);
    }

    // Hesabın açıldığı tarih ile güncel tarih arasındaki gün farkını hesaplar
    public int calculateDaysDifference() {
        LocalDate currentDate = LocalDate.now(); // Güncel tarih
        long daysDifference = ChronoUnit.DAYS.between(LocalDate.parse(openedDate, dateTimeFormatter), currentDate);
        return (int) daysDifference;
    }

    // Default tarihi günceller ve döndürür
    public LocalDate updateDefaultDate() {
        this.defaultDate = LocalDate.now();
        return defaultDate;
    }
    
    // Bakiyeyi ayarlar
    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }
    
    // Bakiyeyi döndürür
    public int getBakiye() {
        return bakiye;
    }
    
    // Hesap numarasını ayarlar
    public void setAccountId(int id) {
        if (this.account_id == 0) {
            this.account_id = id;
        }
        else {
            System.out.println("Hesap id numarasi degistirilemez..");
        }
    }
    
    // Hesap numarasını döndürür
    public int getAccountId() {
        return this.account_id;
    }
    
    // Belirli bir hesaba para yatırır
    public void deposit(int accountId, int cash) {
        if (this.account_id == accountId) {
            bakiye += cash;
        }
    }

    // Belirli bir hesaptan para çeker
    public void withdraw(int accountId, int cash) {
        if (account_id == accountId) {
            bakiye -= cash;
        }
    }
    
    // Hesabın anlık bakiyesini döndürür
    public int getBalance() {
        return bakiye;
    }

    // Hesap numarasını döndürür
    public int getId() {
        return getAccountId();
    }
    
    // Hesabın açılış tarihini döndürür
    public String getDate() {
        return openedDate;
    }

    // Hesabın varsayılan açılış tarihini döndürür
    public String getDefaultDate() {
        return DefaultopenedDate;
    }

    // Hesap tipini döndürür
    public AccountType getType() {
        return type;
    }

    // Hesap için faiz oranını hesaplar
    public abstract float benefit();

    // Hesap kurallarını ekrana yazdırır
    public abstract void accountRules();

    // Hesaba puan ekler
    public void addPoints(int amount) {
        points += amount;
    }
    
    // Hesabın puanını döndürür
    public int getPoints() {
        return points;
    }

    // Belirli bir hesaba para yatırır
    public void deposit(int cash) {
        bakiye += cash;
    }
}
