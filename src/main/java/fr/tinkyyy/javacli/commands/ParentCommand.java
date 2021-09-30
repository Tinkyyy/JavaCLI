package fr.tinkyyy.javacli.commands;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * The interface Parent command.
 */
public interface ParentCommand extends Command {

    /**
     * Gets sub commands.
     *
     * @return the sub commands
     */
    Set<SubCommand> getSubCommands();

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    default Optional<SubCommand> findByName(String name) {
        String lowerCaseName = name.toLowerCase();
        return getSubCommands()
                .stream()
                .filter(command -> command.getName().equalsIgnoreCase(lowerCaseName) || Arrays.stream(command.getAliases())
                        .anyMatch(alias -> alias.equalsIgnoreCase(lowerCaseName)))
                .findFirst();
    }
}
