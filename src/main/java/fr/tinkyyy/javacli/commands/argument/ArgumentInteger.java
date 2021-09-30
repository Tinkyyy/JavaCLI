package fr.leka.cli.commands.argument;

import java.util.Optional;

/**
 * The type Argument integer.
 */
public class ArgumentInteger extends Argument<Integer> {

    /**
     * Instantiates a new Argument integer.
     *
     * @param raw the raw
     */
    public ArgumentInteger(String raw) {
        super(raw);
    }

    @Override
    public Integer getValue() {
        if (value != null)
            return value;

        value = getInteger(raw).orElse(getHexadecimal(raw).orElse(Integer.MIN_VALUE));

        return value;
    }

    private Optional<Integer> getInteger(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    private Optional<Integer> getHexadecimal(String value) {
        try {
            if (value.contains("0x"))
                return Optional.of(Integer.parseInt(value.replace("0x", ""), 16));
        } catch (NumberFormatException ignored) {

        }
        return Optional.empty();
    }
}
