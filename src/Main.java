import commands.ConsoleReader;
import commands.ConsoleWriter;
import commands.guest.Login;
import contracts.Command;
import contracts.Reader;
import contracts.Writer;
import roles.Guest;
import singleton.LoggedInUser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoggedInUser user = LoggedInUser.getInstance();
        user.setUser(new Guest());
        Reader reader = new ConsoleReader(new Scanner(System.in));
        Writer writer = new ConsoleWriter();
        Command test = new Login(user);
        test.execute(null, reader, writer);

        writer.writeLine(user.getPermissionLevel().toString());
    }

}
