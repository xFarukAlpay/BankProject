package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Accounts.Account;
import Accounts.AccountType;
import Accounts.CurrentAccount;
import Accounts.LongTermAccount;
import Accounts.SpecialAccount;
import Accounts.ShortTermAccount;

public class Bank {

    private ArrayList<Account> accounts;
    private Scanner scanner;
    private Account account;
    private LocalDate date;
    private DateTimeFormatter dateTimeFormatter;
    private static LocalDate lastInputDate;

    public Bank() {
        accounts = new ArrayList<Account>();
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        lastInputDate = LocalDate.now();
        scanner = new Scanner(System.in);
    }

    public LocalDate getLastInputDate() {
        return lastInputDate;
    }

    // Güncel tarih değiştirme bilgisi alıyoruz
    public void ChangeDate() {
        boolean validDate = false;
        LocalDate newDate = null;

        while (!validDate) {
            System.out.print("Yeni tarihi girin (gg.aa.yyyy): ");
            String dateStr = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            try {
                newDate = LocalDate.parse(dateStr, formatter);
                validDate = true; // Geçerli bir tarih girildiğinde döngüden çık
            } catch (DateTimeParseException e) {
                System.out.println("Geçersiz tarih formatı. Tekrar deneyin.");
            }
        }

        setAccountDate(newDate); // Hesapların tarihini değiştir
    }

    // Güncel tarihi kullanıcıdan alınan ile değiştiriyoruz
    public void setAccountDate(LocalDate newDate) {
        lastInputDate = newDate;
        System.out.println("Hesap açılış tarihi güncellendi: " + lastInputDate.format(dateTimeFormatter).toString());
    }

    public static LocalDate setAccountDate() {
        return lastInputDate;
    }

    public void getAccountDate() {
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                System.out.println(" Acilis Tarih: " + account.getDefaultDate());
            }
        } else {
            System.out.println("Goruntulenecek hesap yok..");
        }
    }

    // hesapların ıd numaralarını göstermeye yarar
    public void getAccountsId() {
        for (Account account : accounts) {
            System.out.println(account.getAccountId());
        }
    }

    // güncel tarih biligisini alır
    public void getDate() {
        date = LocalDate.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println(date.format(dateTimeFormatter).toString());
    }

    // default tarih biligisini alır
    public void DefaultgetDate() {
        LocalDate defaultDate = LocalDate.of(2023, 5, 5); // Varsayılan tarih değeri
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println(defaultDate.format(dateTimeFormatter).toString());
    }

    // kullanıcının girdiği para miktarını hesaplara ekler
    public void deposit(int Id, int cash) {
        boolean foundId = false;
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                if (Id == account.getAccountId()) {
                    foundId = true;
                    account.deposit(Id, cash); // cash parametresini kullanarak yatırım yapılıyor
                    account.transactions
                            .add("Hesaba " + cash + " TL para yatirildi." + LocalDate.now().format(dateTimeFormatter));
                    System.out.println(
                            account.getId() + " nolu hesabiniza " + cash + " tl yatirildi. Toplam bakiye: "
                                    + account.getBakiye());
                }
            }
        } else {
            System.out.println("Olusturulmus hesap yok..");
        }

        if (!foundId) {
            System.out.println("Yanlis Hesap id..");
        }
    }

    // kullanıcının girdiği para miktarını hesaplardan çeker
    public void Withdraw(int Id, int cash) {
        boolean foundId = false;
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                if (Id == account.getAccountId()) {
                    foundId = true;
                    if (cash <= account.getBakiye()) {
                        account.withdraw(Id, cash);
                        account.transactions.add(
                                "Hesaptan " + cash + " TL para cekildi." + LocalDate.now().format(dateTimeFormatter));
                        System.out.println(
                                account.getId() + " nolu hesaptan " + cash + " tl cekildi. Toplam bakiye : "
                                        + account.getBakiye());
                    } else {
                        System.out.println("Yetersiz Bakiye !");
                    }
                }
            }
        } else {
            System.out.println("Olusturulmus hesap yok..");
        }

        if (!foundId) {
            System.out.println("Yanlis Hesap id..");
        }
    }

    // son 5 işlemş ve bazı bilgilieri ekran yazdırmayı sağlayan fonkssiyon
    public void allAccountId_And_LastFiveTransactions() {
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                System.out.println("Hesap türü:  " + account.getType());
                System.out.println("Güncel tarih  " + lastInputDate.format(dateTimeFormatter).toString());
                System.out.println("Hesap id:  " + account.getId());
                System.out.println("Hesaptaki ana para:  " + account.getBalance());
                System.out.println("Hesaptaki kar:  " + account.benefit());

                int count = 1;
                for (int i = account.transactions.size() - 1; i >= 0; i--) {
                    if (count <= 5) {
                        System.out.println((i + 1) + ".islem : " + account.transactions.get(i));
                    }
                    count++;
                }
                System.out.println("--------------------------------------------------------------");
            }
        } else {
            System.out.println("Görüntülenecek hesap Yok..");
        }
    }

    // hesap oluşturur
    public void createAccount(int input) {
        scanner = new Scanner(System.in);
        int id;
        int balance;

        if (input == 1) {
            System.out.println("SHORT TERM ACCOUNT");
            System.out.println("id numarasi giriniz ?");
            id = scanner.nextInt();
            System.out.println("Baslangic para miktari giriniz.(min 1000TL).");
            balance = scanner.nextInt();

            if (balance >= 1000) {
                account = new ShortTermAccount(id, balance);
                accounts.add(account);
                account.transactions.add("Hesap olusturuldu.." + account.getDefaultDate());
                System.out.print("Hesap olusturuldu");
            } else {
                System.out.println("Kisa vadeli hesap olusturmak icin minimum 1000 TL bakiye olmalidir.");
            }
        } else if (input == 2) {
            System.out.println("LONG TERM ACCOUNT");
            System.out.println("id numarasi giriniz ?");
            id = scanner.nextInt();
            System.out.println("Baslangic para miktari giriniz.(min 1500TL).");
            balance = scanner.nextInt();

            if (balance >= 1500) {
                account = new LongTermAccount(id, balance);
                accounts.add(account);
                account.transactions.add("Hesap olusturuldu.." + account.getDefaultDate());
                System.out.print("Hesap olusturuldu");
            } else {
                System.out.println("Uzun vadeli hesap olusturmak icin minimum 1500 TL bakiye olmalidir.");
            }
        } else if (input == 3) {
            System.out.println("SPECIAL ACCOUNT");
            System.out.println("id numarasi giriniz ?");

            id = scanner.nextInt();
            System.out.println("Baslangic para miktarini giriniz ?");

            balance = scanner.nextInt();

            account = new SpecialAccount(id, balance);
            accounts.add(account);
            account.transactions.add("Hesap olusturuldu.." + account.getDefaultDate());
            System.out.print("Hesap olusturuldu");

        } else if (input == 4) {
            System.out.println("CURRENT ACCOUNT");
            System.out.println("id numarasi giriniz ?");
            id = scanner.nextInt();
            account = new CurrentAccount(id);
            accounts.add(account);
            account.transactions.add("Hesap olusturuldu.." + account.getDefaultDate());
            System.out.print("Hesap olusturuldu");
        }
    }

    // id kontrolu methodu
    public Boolean checkAccountId(int accountId) {
        Boolean bool = false;
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                bool = true;
            }
        }
        return bool;
    }

    // çekiliş fonksiyonu
    public void sortition() {
        // Özel hesaplarda her 2000 TL için bir puan tanımlanması
        for (Account account : accounts) {
            if (account.getType() == AccountType.SPECİAL) {
                int points = account.getBalance() / 2000;
                account.addPoints(points);
            }
        }

        // Çekilişin yapılması
        List<Account> specialAccounts = new ArrayList<>();

        for (Account account : accounts) {
            if (account.getType() == AccountType.SPECİAL && account.getPoints() > 0) {
                specialAccounts.add(account);
            }
        }

        if (specialAccounts.isEmpty()) {
            System.out.println("Çekiliş için yeterli hesap bulunmamaktadır.");
            return;
        }

        // Çekilişin yapılması için hesapların puanlarına göre ağırlıklı rastgele seçim
        // yapılıyor
        Account selectedAccount = null;
        int totalPoints = 0;
        for (Account account : specialAccounts) {
            totalPoints += account.getPoints();
        }

        Random random = new Random();
        int winningNumber = random.nextInt(totalPoints) + 1; // 1 ile toplam puan arasında bir sayı seç

        int accumulatedPoints = 0;
        for (Account account : specialAccounts) {
            accumulatedPoints += account.getPoints();
            if (winningNumber <= accumulatedPoints) {
                selectedAccount = account;
                break;
            }
        }

        if (selectedAccount != null) {
            selectedAccount.deposit(10000);
            System.out.println("Çekilişi kazanan hesap ID: " + selectedAccount.getId());
            System.out.println(
                    "Çekiliş ödülü olan 10000 TL hesaba yatırıldı. Yeni toplam bakiye: " + selectedAccount.getBakiye());
        } else {
            System.out.println("Çekiliş sırasında bir hata oluştu.");
        }

    }
}
