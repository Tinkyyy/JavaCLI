package fr.tinkyyy.javacli.commands.argument;

/**
 * The type Argument.
 *
 * @param <V> the type parameter
 */
public abstract class Argument<V> {

    /**
     * The Raw.
     */
    protected final String raw;
    /**
     * The Value.
     */
    protected V value;

    /**
     * Instantiates a new Argument.
     *
     * @param raw the raw
     */
    public Argument(String raw) {
        this.raw = raw;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public abstract V getValue();

    /**
     * Gets raw value.
     *
     * @return the raw value
     */
    public String getRawValue() {
        return raw;
    }
}
