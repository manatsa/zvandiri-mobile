
package zw.org.zvandiri.business.util;

import org.apache.commons.codec.binary.Base64;

/**

 * @author Clive Gurure
 */

public final class StringUtils {
    
    public static String toCamelCase(String c) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(c)) {
            return c;
        }

        if (!c.trim().contains(" ")) {
           return capitalizeWord(c);
        }

        String[] arrayWords = c.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < arrayWords.length; i++ ) {
            sb.append(capitalizeWord(arrayWords[i].concat(" ")));
        }
        
        return sb.toString();
    }
    
    public static String toCamelCase3(String c) {
        if (c==null || org.apache.commons.lang3.StringUtils.isEmpty(c)) {
            return c;
        }

        if (!c.trim().contains("_")) {
           return capitalizeWord(c);
        }

        String[] arrayWords = c.split("_");
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < arrayWords.length; i++ ) {
            sb.append(capitalizeWord(arrayWords[i].concat(" ")));
        }
        
        return sb.toString();
    }

    private static String capitalizeWord(String word) {

        if (word != null && !word.trim().equals("")) {
            return word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase();
        } else {
            return word;
        }
    }
    
    public static String toCamelCase2(String inputString) {
       String result = "";
       if (inputString.isEmpty()) {
           return null;
       }
       char firstChar = inputString.charAt(0);
       char firstCharToUpperCase = Character.toUpperCase(firstChar);
       result = result + firstCharToUpperCase;
       for (int i = 1; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           char previousChar = inputString.charAt(i - 1);
           if (previousChar == ' ') {
               char currentCharToUpperCase = Character.toUpperCase(currentChar);
               result = result + currentCharToUpperCase;
           } else {
               char currentCharToLowerCase = Character.toLowerCase(currentChar);
               result = result + currentCharToLowerCase;
           }
       }
       return result;
   }

    public static String base64Encode(String plainText) {

        byte[] plainTextBytes = plainText.getBytes();
        byte[] encodedBytes = Base64.encodeBase64(plainTextBytes);
        return new String(encodedBytes);
    }
}
