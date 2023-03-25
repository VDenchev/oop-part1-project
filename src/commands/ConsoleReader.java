package commands;

import contracts.Reader;
import java.util.Scanner;

public class ConsoleReader implements Reader{
    private Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
