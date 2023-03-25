package commands;

import contracts.Writer;

public class ConsoleWriter implements Writer {
    @Override
    public void writeLine(String msg) {
        System.out.println(msg);
    }
}
