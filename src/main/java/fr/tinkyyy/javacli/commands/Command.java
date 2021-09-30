package fr.tinkyyy.javacli.commands;

import java.util.List;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Get aliases string [ ].
     *
     * @return the string [ ]
     */
    String[] getAliases();

    /**
     * Gets usage.
     *
     * @return the usage
     */
    String getUsage();

    /**
     * Execute.
     *
     * @param args the args
     */
    void execute(List<String> args);
}
