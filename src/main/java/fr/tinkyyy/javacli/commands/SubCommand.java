package fr.tinkyyy.javacli.commands;

/**
 * The interface Sub command.
 */
public interface SubCommand extends Command {

    /**
     * Gets parent.
     *
     * @return the parent
     */
    ParentCommand getParent();

}
