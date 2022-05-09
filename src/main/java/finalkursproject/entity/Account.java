package finalkursproject.entity;

import java.util.Objects;

public class Account {
    private int idAccount;
    private double accountStatus;
    private String accountNumber;
    private int idClient;

    public Account() {
    }

    public Account(int idAccount, double accountStatus, String accountNumber, int idClient) {
        this.idAccount = idAccount;
        this.accountStatus = accountStatus;
        this.accountNumber = accountNumber;
        this.idClient = idClient;
    }

    public Account(double accountStatus, String accountNumber, int idClient) {
        this.accountStatus = accountStatus;
        this.accountNumber = accountNumber;
        this.idClient = idClient;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public double getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(double accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return idAccount == account.idAccount &&
                Double.compare(account.accountStatus, accountStatus) == 0 &&
                idClient == account.idClient &&
                Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAccount, accountStatus, accountNumber, idClient);
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", accountStatus=" + accountStatus +
                ", accountNumber='" + accountNumber + '\'' +
                ", idClient=" + idClient +
                '}';
    }
}
