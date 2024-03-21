import java.util.Scanner;

public class StrPalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();
        
        if (isPalindrome(input)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
    }
    
    public static boolean isPalindrome(String str) {
        StringBuilder reversed = new StringBuilder(str);
        reversed.reverse(); 
        return str.equals(reversed.toString());
    }
}
