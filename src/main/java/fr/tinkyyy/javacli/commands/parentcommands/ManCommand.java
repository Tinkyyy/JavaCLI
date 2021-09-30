package fr.tinkyyy.javacli.commands.parentcommands;

import fr.tinkyyy.javacli.commands.Command;
import fr.tinkyyy.javacli.commands.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * The type Man command.
 */
public class ManCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManCommand.class);

    private final CommandManager commandManager;
    private final String name;
    private final String description;
    private final String usage;
    private final String[] aliases;

    /**
     * Instantiates a new Man command.
     *
     * @param commandManager the command manager
     * @param name           the name
     * @param description    the description
     * @param usage          the usage
     * @param aliases        the aliases
     */
    public ManCommand(CommandManager commandManager, String name, String description, String usage, String... aliases) {
        this.commandManager = commandManager;
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public void execute(List<String> args) {
        if (args.size() != 1) {
            LOGGER.error("Syntax error ! Please use: " + usage);
            return;
        }

        boolean commandExists = commandManager.applyTo(this::checkCommand, args.get(0));

        if (!commandExists) {
            LOGGER.error("The command " + args.get(0) + " doesn't exist");
        }
    }

    private void checkCommand(Command command) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("man/" + command.getName() + ".man");
        if (inputStream != null) {
            printManFile(inputStream);
        } else
            LOGGER.error("No man found for this command");
    }

    private void printManFile(InputStream input) {

        if (input == null)
            return;

        try (InputStreamReader reader = new InputStreamReader(input);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            StringBuilder stringBuilder = new StringBuilder("\n");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            LOGGER.info(stringBuilder.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
