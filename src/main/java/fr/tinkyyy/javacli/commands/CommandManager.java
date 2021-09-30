package fr.tinkyyy.javacli.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * The type Command manager.
 */
public class CommandManager {

    private final List<Command> commands;

    /**
     * Instantiates a new Command manager.
     */
    public CommandManager() {
        this.commands = new ArrayList<>();
    }

    /**
     * Register.
     *
     * @param command the command
     */
    public void register(Command command) {
        this.commands.add(command);
    }

    /**
     * Gets commands size.
     *
     * @return the commands size
     */
    public int getCommandsSize() {
        return commands.size();
    }

    /**
     * Represents an operation that accepts
     * a single input argument and returns no result.
     *
     * @param func  the func
     * @param label the label
     * @return boolean boolean
     */
    public boolean applyTo(Consumer<Command> func, String label) {
        String toLowerCase = label.toLowerCase();
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(toLowerCase) || Arrays.stream(command.getAliases())
                    .anyMatch(alias -> alias.equalsIgnoreCase(toLowerCase))) {
                func.accept(command);
                return true;
            }
        }
        return false;
    }

    /**
     * Return every commands instance of use a getter of commands
     *
     * @param func the func
     */
    public void apply(Consumer<Command> func) {
        commands.forEach(func);
    }
}
