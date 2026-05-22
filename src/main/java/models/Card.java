package models;

import java.time.LocalDateTime;

public class Card {
    private String cardNumber;
    private String cvv;
    private String pinCode;
    private LocalDateTime expirationDate;
    private boolean isActive;
    private BankAccount linkedAccount;

    public Card(String cardNumber, String cvv, String pinCode, LocalDateTime expirationDate, boolean isActive, BankAccount linkedAccount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.pinCode = pinCode;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
        this.linkedAccount = linkedAccount;
    }

    public boolean pay(double amount){
        if (!isActive) {
            System.out.println("Payment failed: Card is inactive!");
            return false;
        }

        return linkedAccount.withdraw(amount , "Card payment" , "Payment by card " + cardNumber);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BankAccount getLinkedAccount() {
        return linkedAccount;
    }

    public void setLinkedAccount(BankAccount linkedAccount) {
        this.linkedAccount = linkedAccount;
    }
}
