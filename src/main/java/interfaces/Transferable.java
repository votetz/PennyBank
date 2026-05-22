package interfaces;

import models.BankAccount;

public interface Transferable {
    void transfer(BankAccount target, double amount);
}