package Test;

import java.util.Scanner;
import bank.Bank;

public class Main {
    static Scanner scanner;
    static String lastInput;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int input;

        Bank bank = new Bank();

        while (true) {
            System.out.println("\n########################\n");
            System.out.println("1-Create_S_ID_balance");
            System.out.println("2-Create_L_ID_balance");
            System.out.println("3-Create_O_ID_balance");
            System.out.println("4-Create_C_ID_balance");
            System.out.println("5-Increase_ID_cash");
            System.out.println("6-Decrease_ID_cash");
            System.out.println("7-Set_dd_mm_yy");
            System.out.println("8-Show Accounts Id And Last 5 Transactions");
            System.out.println("9-ShowIDs");
            System.out.println("10-Sortition");
            System.out.println("0-Exit\n");

            System.out.print("Lütfen bir işlem seçiniz: ");
            input = scanner.nextInt();

            if (input == 0) {
                System.out.println("Programdan çıkılıyor...");
                break;
            }

            requestFromUser(input, bank);
        }

        scanner.close();
    }

    static void requestFromUser(int input, Bank bank) {
        switch (input) {
            case 1: {
                bank.createAccount(1);
                lastInput = "Hesap oluşturuldu";
                break;
            }
            case 2: {
                bank.createAccount(2);
                lastInput = "Hesap oluşturuldu";
                break;
            }
            case 3: {
                bank.createAccount(3);
                lastInput = "Hesap oluşturuldu";
                break;
            }
            case 4: {
                bank.createAccount(4);
                lastInput = "Hesap oluşturuldu";
                break;
            }
            case 5: {
                System.out.print("Para yatırmak istediğiniz hesap ID'si: ");
                int id = scanner.nextInt();

                if (bank.checkAccountId(id)) {
                    System.out.print("Yatırmak istediğiniz para miktarı: ");
                    int cash = scanner.nextInt();
                    bank.deposit(id, cash);
                    lastInput = id + " nolu hesapta " + cash + " TL yatırıldı";
                } else {
                    System.out.println("Yanlış ID numarası..");
                    lastInput = "Geçersiz işlem: Yanlış hesap ID'si";
                }
                break;
            }
            case 6: {
                System.out.print("Para çekmek istediğiniz hesap ID'si: ");
                int id = scanner.nextInt();

                if (bank.checkAccountId(id)) {
                    System.out.print("Çekmek istediğiniz para miktarı: ");
                    int cash = scanner.nextInt();
                    bank.Withdraw(id, cash);
                    lastInput = id + " nolu hesaptan " + cash + " TL çekildi";
                } else {
                    System.out.println("Yanlış ID numarası..");
                    lastInput = "Geçersiz işlem: Yanlış hesap ID'si";
                }
                break;
            }
            case 7: {
                bank.ChangeDate();
                lastInput = "Tarih güncellendi";
                break;
            }
            case 8: {
                /* Tüm hesapların ID'lerini ve son 5 işlemi gösterir */
                bank.allAccountId_And_LastFiveTransactions();
                System.out.println("Son işlem: " + lastInput);
                break;
            }
            case 9: {
                bank.getAccountsId();
                lastInput = "Hesap ID'leri gösterildi";
                break;
            }
            case 10: {
                bank.sortition();
                lastInput = "Kur'a çekimi yapıldı";
                break;
            }
            default: {
                System.out.println("Geçersiz istek..");
                lastInput = "Geçersiz işlem: Geçersiz seçenek";
            }
        }
    }
}
