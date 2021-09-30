package fr.tinkyyy.javacli.utils;

import fr.tinkyyy.javacli.commands.argument.ArgumentInteger;
import org.slf4j.Logger;

import java.awt.*;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * Check RGB color
     *
     * @param logger the logger
     * @param r      the r
     * @param g      the g
     * @param b      the b
     * @return the boolean
     */
    public static boolean checkColor(Logger logger, String r, String g, String b) {
        ArgumentInteger R = new ArgumentInteger(r);
        ArgumentInteger G = new ArgumentInteger(g);
        ArgumentInteger B = new ArgumentInteger(b);

        try {
            new Color(R.getValue(), G.getValue(), B.getValue());
            return true;
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }
}
