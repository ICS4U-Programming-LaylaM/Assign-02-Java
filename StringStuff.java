import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* This program takes a file with a bunch of strings and
* returns a blowup version of it in an output file.
*
* @author Layla Michel
* @version 1.0
* @since 2022-04-07
*/

class StringStuff {
    /**
    * Default empty constructor.
    */
    private StringStuff() { }

    /**
    * Creating function to blowup strings.
    *
    * @param strings as string
    *
    * @return newString as string
    */
    public static String blowup(String strings) {
        String newString = "";
        int counter = 1;
        // Check if string is empty or not
        if (!"".equals(strings)) {
            for (int index = 0; index < strings.length(); index++) {
                final char chars = strings.charAt(index);

                // Check if the character at the index is a number
                final Boolean number = Character.isDigit(chars);

                if (number) {
                    if (counter < strings.length()) {
                        // Get the character at the next index
                        // if the current index is a number
                        final char nextIndex = strings.charAt(index + 1);
                        // Convert the next index to a string
                        final String nextIndexStr = String.valueOf(nextIndex);
                        // Repeat the next index a specific amount of time
                        final String nextIndexRepeat =
                            nextIndexStr.repeat(Character
                            .getNumericValue(chars));
                        // Add the repeated string to the new string
                        newString = newString + nextIndexRepeat;
                    }
                } else {
                    // Add the current character to the new string
                    newString = newString + String.valueOf(chars);
                }
                counter++;
            }
        }
        return newString;
    }

    /**
    * Creating main function.
    *
    * @param args nothing passed in
    * @throws IOException if no file is passed in
    */
    public static void main(final String[] args)
            throws IOException {
        // Declaring variables
        final String[] stringArray;
        final String[] blowupArray;
        int counter;
        String blowups = "";

        // Create a binary search object
        // StringStuff stringStuff = new StringStuff();

        final List<String> listOfStrings =
            new ArrayList<String>();

        BufferedReader bf = null;
        try {
            // Check if there are some arguments
            if (null != args[0]
                // Length > 4 because a.txt will be shortest filename
                && args[0].length() > 4
                // Check if arguments have the correct file extension
                && args[0].endsWith(".txt")) {
                bf = new BufferedReader(new FileReader(args[0]));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String line = bf.readLine();

        // Add file elements to list
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        // Create array of chars of the size of the list
        stringArray = listOfStrings.toArray(new String[0]);

        final List<String> blowupString =
            new ArrayList<String>();

        try {
            // Blowup the string for each element of the array
            for (counter = 0; counter < stringArray.length; counter++) {
                blowups = StringStuff.blowup(stringArray[counter]);
                blowupString.add(blowups);
            }

            blowupArray = blowupString.toArray(new String[0]);

            // Build a string containing the elements of the array
            final StringBuilder builder = new StringBuilder();
            for (counter = 0; counter < blowupArray.length; counter++) {
                builder.append(blowupArray[counter]);
                builder.append("\n");
            }

            // Create new file called "output.txt"
            final BufferedWriter writer = new BufferedWriter(new
                    FileWriter("/home/runner/Assign-02-Java/output.txt"));
            writer.write(builder.toString());
            writer.close();
            System.out.println("Blowup strings added to 'output.txt'");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
