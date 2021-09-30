package fr.tinkyyy.javacli.commands;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * The type Command dispatcher.
 */
public class CommandDispatcher extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDispatcher.class);

    private final CommandManager manager;
    private final LineReader lineReader;

    /**
     * Instantiates a new Command dispatcher.
     *
     * @param lineReader the line reader
     * @param manager    the manager
     */
    public CommandDispatcher(LineReader lineReader, CommandManager manager) {
        this.manager = manager;
        this.lineReader = lineReader;
    }

    /**
     * Instantiates a new Command dispatcher.
     * @return {@void}
     */
    @Override
    public void run() {
        while (true) {
            String line = lineReader.readLine("> ").toLowerCase();
            String[] part = line.split(" ");
            String[] args = Arrays.copyOfRange(part, 1, part.length);
            String commandName = part[0];

            boolean worked = manager.applyTo(command -> command.execute(Arrays.asList(args)), commandName);

            if (!worked)
                LOGGER.error("Invalid Command, type help for help !");

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
