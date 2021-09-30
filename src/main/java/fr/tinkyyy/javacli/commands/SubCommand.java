package fr.leka.cli.commands;

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
