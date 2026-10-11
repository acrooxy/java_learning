package src;

public class HomeActivity6BankAcc {
    public static void main(String[] args) {
        class BankAccount{
            private int balance = 0;
            void deposit(int amount){
                balance += amount;
                System.out.println(String.format("Deposited %s", amount));
            }
            void withdraw(int amount){
                balance -= amount;
                System.out.println(String.format("Withdraw %s", amount));

            }
            void getBalance(){
                System.out.println(String.format("Balance is %s", balance));

            }

        }
        BankAccount bankAccount1 = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();
        bankAccount1.deposit(100);
        bankAccount2.deposit(200);
        System.out.println(bankAccount1.balance);
        System.out.println(bankAccount2.balance);

        bankAccount1.withdraw(200);
        bankAccount2.withdraw(200);
//        System.out.println("There is: " + bankAccount1.balance);
//        System.out.println("There is: " + bankAccount2.balance);

        bankAccount1.getBalance();
        bankAccount2.getBalance();
    }
}
