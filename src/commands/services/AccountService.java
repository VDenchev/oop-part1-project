package commands.services;

import contracts.Account;
import contracts.AccountDAO;
import models.roles.Client;

import java.io.IOException;
import java.util.List;

public class AccountService {
    //TODO move up constants
    public static final String FILE_PATH = "src\\files\\accounts.txt";
    public static final String SEPARATOR = ":";

    public static final String ACCOUNT_NOT_FOUND = "Wrong credentials!";
    public static final String USERNAME_TAKEN = "This username has already been taken!";
    public static final String USERNAME_NOT_FOUND = "There is no account with that username!";

    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccountFromFile(String username, String password) throws IOException {
        Account account = accountDAO.get(username, password);
        if (account == null) {
            throw new IllegalArgumentException(ACCOUNT_NOT_FOUND);
        }

        return account;
    }

    public void addAccountToFile(String username, String password) throws IOException {
        if (isUsernameTaken(username)) {
            throw new IllegalArgumentException(USERNAME_TAKEN);
        }

        Account account = new Client(username, password);
        accountDAO.add(account);
    }

    public void removeAccountFromFile(String username) throws IOException {
        if (!isUsernameTaken(username)) {
            throw new IllegalArgumentException(USERNAME_NOT_FOUND);
        }

        accountDAO.remove(username);
    }

    public boolean isUsernameTaken(String username) throws IOException {
        List<Account> accounts = accountDAO.getAll();

        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }
}
