package com.crovate.kitchensink;

/**
 *
 * @author plakhani
 * @author umansoor
 */
public class Validator {

    /**
     * Checks each individual token to ensure it meets the following criteria:
     * <ul>
     * <li> Length must not exceed 255 characters
     * <li> Must contain only lower or upper case alphabets or numbers.
     * <li> Must begin with an alphabet. (followed by other alphabets or numbers)
     * <ul>
     * @param token
     * @return 
     */
    private boolean checkToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        
        
        if (token.length() > 255) {
            return false;
        }
        

        return token.matches("[a-zA-Z]+[0-9]?+");
    }

    /**
     * This function is the public API to validate a string. TODO: Rules.
     * 
     * @param str
     * @return 
     */
    public boolean validate(String str) {

        // Must not end with a colon. This is done because split silently discards
        // the last colon.
        if (str.endsWith(":")) {
            return false;
        }

        // Split the String into tokens.
        String[] tokens = str.split(":");

        // Must have atleast single token
        if (tokens.length < 1) {
            return false;
        }

        // Must begin with the string "alarm"
        if (!tokens[0].equals("alarm")) {
            return false;
        }

        for (int i = 0; i < tokens.length; i++) {
            if (checkToken(tokens[i]) == false) {
                return false;
            }
        }

        return true;
    }

    public void printUtility(String str) {
        boolean b = validate(str);

        if (b) {
            System.out.println("Correct Sequence\t" + str);
        } else {
            System.out.println("Wrong Sequence\t" + str);
        }
    }

    /**
     * Application Entry Point.s
     * @param args 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Validator j = new Validator();

        j.printUtility("alarm:abc:4abc");
        j.printUtility("alarm:abc.");

        j.printUtility("alarm:abc4");
        j.printUtility("alarm:abc.4");

        j.printUtility("alarm:abc.com");
        j.printUtility("alarm:abc:com:");




    }
}
