package commands.services;

import models.roles.contracts.Account;
import commands.dao.contracts.AccountDAO;
import models.roles.Client;

import java.io.IOException;
import java.util.List;

public class AccountService {
    public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Wrong credentials!";
    public static final String USERNAME_TAKEN_MESSAGE = "This username is already taken!";
    public static final String USERNAME_NOT_FOUND_MESSAGE = "There is no account with such username!";

    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccountFromFile(String username, String password) throws IOException {
        Account account = accountDAO.get(username, password);
        boolean accountNotFound = account == null;

        if (accountNotFound) {
            throw new IllegalArgumentException(ACCOUNT_NOT_FOUND_MESSAGE);
        }

        return account;
    }

    public void addAccountToFile(String username, String password) throws IOException {
        if (usernameExists(username)) {
            throw new IllegalArgumentException(USERNAME_TAKEN_MESSAGE);
        }

        Account account = new Client(username, password);
        accountDAO.add(account);
    }

    public void removeAccountFromFile(String username) throws IOException {
        if (!usernameExists(username)) {
            throw new IllegalArgumentException(USERNAME_NOT_FOUND_MESSAGE);
        }

        accountDAO.remove(username);
    }

    public boolean usernameExists(String username) throws IOException {
        List<Account> accounts = accountDAO.getAll();

        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }
}
