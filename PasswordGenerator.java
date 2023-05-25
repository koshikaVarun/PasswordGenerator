package passwordGen;

import java.security.SecureRandom; // Importing the SecureRandom class for generating random numbers
import java.util.InputMismatchException; // Importing the InputMismatchException class for handling invalid input
import java.util.Scanner; // Importing the Scanner class for user input

public class PasswordGenerator {
    // Define character sets
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[{]};:',<.>/?";

    private static final int MIN_LENGTH = 8; // Minimum length for the password
    private static final int MAX_LENGTH = 20; // Maximum length for the password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a new Scanner object to read user input

        try {
            System.out.print("Enter the desired password length: ");
            int length = scanner.nextInt(); // Read the user's input for the password length

            if (length < MIN_LENGTH || length > MAX_LENGTH) {
                System.out.println("Invalid password length. Please enter a value between " + MIN_LENGTH + " and " + MAX_LENGTH + ".");
                return; // Exit the program if the password length is invalid
            }

            System.out.println("Generated Password: " + generatePassword(length)); // Generate and display the password
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer."); // Display an error message for invalid input
        }
    }

    private static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(); // StringBuilder to build the password
        SecureRandom random = new SecureRandom(); // Create a new SecureRandom object for generating random numbers

        // At least one character from each category
        password.append(getRandomChar(LOWER_CASE, random)); // Append a random lowercase letter
        password.append(getRandomChar(UPPER_CASE, random)); // Append a random uppercase letter
        password.append(getRandomChar(NUMBERS, random)); // Append a random number
        password.append(getRandomChar(SPECIAL_CHARACTERS, random)); // Append a random special character

        // Generate remaining characters
        int remainingLength = length - 4; // Calculate the remaining length after adding the mandatory characters
        for (int i = 0; i < remainingLength; i++) {
            String characterSet = getRandomCharacterSet(); // Get a random character set
            password.append(getRandomChar(characterSet, random)); // Append a random character from the selected set
        }

        // Shuffle the password
        for (int i = 0; i < password.length(); i++) {
            int swapIndex = random.nextInt(password.length()); // Generate a random index to swap characters
            char temp = password.charAt(i); // Store the current character in a temporary variable
            password.setCharAt(i, password.charAt(swapIndex)); // Swap the current character with a random character
            password.setCharAt(swapIndex, temp); // Replace the random character with the temporary character
        }

        return password.toString(); // Convert the StringBuilder to a String and return the generated password
    }

    private static char getRandomChar(String characterSet, SecureRandom random) {
        int randomIndex = random.nextInt(characterSet.length()); // Generate a random index within the character set length
        return characterSet.charAt(randomIndex); // Return the character at the random index
    }

    private static String getRandomCharacterSet() {
        String allCharacters = LOWER_CASE + UPPER_CASE + NUMBERS + SPECIAL_CHARACTERS; // Combine all character sets
        SecureRandom random = new SecureRandom(); // Create a new SecureRandom object for generating random numbers

        int randomIndex = random.nextInt(allCharacters.length()); // Generate a random index within the combined character set length
        return allCharacters.substring(randomIndex, randomIndex + 1); // Return a substring containing a random character
    }
}

