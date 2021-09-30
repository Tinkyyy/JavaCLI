package fr.tinkyyy.javacli;

import fr.tinkyyy.javacli.commands.parentcommands.HelpCommand;
import fr.tinkyyy.javacli.commands.parentcommands.ManCommand;
import fr.tinkyyy.javacli.commands.CommandDispatcher;
import fr.tinkyyy.javacli.commands.CommandManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.OSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JavaCLI {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaCLI.class);

    private CommandManager commandManager;

    public static void main(String[] args) {
        JavaCLI javaCLI = new JavaCLI();
		javaCLI.init();
    }

    private void init() {
        LineReader lineReader = initLineReader();

        commandManager = new CommandManager();
        registerCommands();
        new CommandDispatcher(lineReader, commandManager).start();
        LOGGER.debug("Command manager initialized !");

    }

    /**
     * Register CLI commands.
     * @return {@void}
     */

    private void registerCommands() {
        commandManager.register(new HelpCommand(commandManager, "help", "Display available commands.",
                "\"help\"", "helps", "info", "infos"));

        commandManager.register(new ManCommand(commandManager, "man", "An interface to the on-line reference manuals.",
                "\"man <command>\"", "manual"));

        LOGGER.debug("{} commands were successfully initialized!", commandManager.getCommandsSize());
    }


    /**
     * Initializes the CLI
     * @return {@link LineReader}
     */

    private LineReader initLineReader() {
        LineReader lineReader = null;
        try {
            Terminal terminal = TerminalBuilder.builder().dumb(true).build();
            Thread executeThread = Thread.currentThread();
            terminal.handle(Terminal.Signal.INT, signal -> executeThread.interrupt());

            lineReader = LineReaderBuilder.builder()
                    .variable(LineReader.SECONDARY_PROMPT_PATTERN, "%M%P > ")
                    .variable(LineReader.INDENTATION, 2)
                    .variable(LineReader.LIST_MAX, 100)
                    .option(LineReader.Option.INSERT_BRACKET, true)
                    .option(LineReader.Option.EMPTY_WORD_OPTIONS, false)
                    .option(LineReader.Option.USE_FORWARD_SLASH, true)
                    .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true)
                    .terminal(terminal)
                    .parser(new DefaultParser())
                    .build();

            if (OSUtils.IS_WINDOWS)
                lineReader.setVariable(LineReader.BLINK_MATCHING_PAREN, 0);

            LOGGER.debug("Line Reader initialized !");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lineReader;
    }
}
