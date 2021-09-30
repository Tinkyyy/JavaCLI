package fr.leka.cli.commands.parentcommands;

import fr.leka.cli.commands.Command;
import fr.leka.cli.commands.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The type Help command.
 */
public class HelpCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommand.class);

    private final CommandManager commandManager;

    private final String name;
    private final String description;
    private final String usage;
    private final String[] aliases;

    /**
     * Instantiates a new Help command.
     *
     * @param commandManager the command manager
     * @param name           the name
     * @param description    the description
     * @param usage          the usage
     * @param aliases        the aliases
     */

    public HelpCommand(CommandManager commandManager, String name, String description, String usage, String... aliases) {
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
        if (commandManager.getCommandsSize() == 0)
            return;

        StringBuilder stringBuilder = new StringBuilder("Here is the list of available commands: \n");

        commandManager.apply(command -> fillBuilder(command, stringBuilder));

        LOGGER.info(stringBuilder.substring(0, stringBuilder.toString().length() - 2));

    }

    private void fillBuilder(Command command, StringBuilder stringBuilder) {
        stringBuilder.append("- ").append(command.getName()).append("\n\t");
        stringBuilder.append("Description: ").append(command.getDescription()).append("\n\t");

        if (command.getAliases().length > 0)
            stringBuilder.append("Aliases: ").append(String.join(", ", command.getAliases())).append("\n\t");

        stringBuilder.append("Usage: ").append(command.getUsage()).append("\n\n");
    }
}
