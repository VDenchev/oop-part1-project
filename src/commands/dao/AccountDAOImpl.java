package commands.dao;

import models.roles.contracts.Account;
import commands.dao.contracts.AccountDAO;
import models.roles.PermissionLevel;
import models.roles.Admin;
import models.roles.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AccountDAOImpl implements AccountDAO {
    private String path;
    private String separator;

    public AccountDAOImpl(String path, String separator) {
        this.path = path;
        this.separator = separator;
    }

    @Override
    public List<Account> getAll() throws IOException {
        Scanner scanner = new Scanner(new File(path));
        List<Account> accounts = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(separator);

            boolean isValidCredentials = tokens.length == 3;
            if (isValidCredentials) {
                String username = tokens[0];
                String password = tokens[1];
                PermissionLevel permissionLevel = PermissionLevel.valueOf(tokens[2]);

                Account account;
                if (permissionLevel == PermissionLevel.ADMIN) {
                    account = new Admin(username, password);
                } else {
                    account = new Client(username, password);
                }
                accounts.add(account);
            }
        }
        scanner.close();

        return accounts;
    }

    @Override
    public Account get(String username, String password) throws IOException {
        List<Account> accounts = getAll();
        for (Account account: accounts) {
            boolean usernameMatches = account.getUsername().equalsIgnoreCase(username);
            boolean passwordMatches = account.getPassword().equals(password);

            if (usernameMatches && passwordMatches) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void add(Account account) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(path, true));
        printWriter.println(account.getUsername() + separator + account.getPassword() + separator + account.getPermissionLevel());

        printWriter.close();
    }

    @Override
    public void remove(String username) throws IOException {
        List<Account> accounts = getAll();
        Iterator<Account> itr = accounts.iterator();

        while (itr.hasNext()) {
            boolean usernameMatches = itr.next().getUsername().equalsIgnoreCase(username);

            if (usernameMatches){
                itr.remove();
            }
        }

        PrintWriter printWriter = new PrintWriter(new FileWriter(path));
        for (Account account : accounts) {
            printWriter.println(account);
        }

        printWriter.close();
    }
}
