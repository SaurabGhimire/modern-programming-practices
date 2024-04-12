package project.project.utils.validation;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
     private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        return validateRegex(regex, phoneNumber);
    }

    private static boolean isValidZipCode(String zipCode){
        String regex = "\\d{5}(-\\d{4})?";
        return validateRegex(regex, zipCode);
    }

    static boolean validateRegex(String regex, String input){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static void validateField(JTextField textField, JLabel label, StringBuilder stringBuilder){
        if (textField.getText().isEmpty()) {
            textField.requestFocusInWindow();
            stringBuilder.append(label.getText().trim() + " is empty\n");
        }
    }

    public static void validatePhoneNumber(JTextField textField, JLabel label, StringBuilder stringBuilder){
        if(textField.getText().isEmpty()) return;
        if (isValidPhoneNumber(textField.getText())) {
            return;
        }
        textField.requestFocusInWindow();
        stringBuilder.append("Enter a valid " + label.getText().trim() + "\n");
    }

    public static void validateZipCode(JTextField textField, JLabel label,StringBuilder stringBuilder){
        if(textField.getText().isEmpty()) return ;
        if (isValidZipCode(textField.getText())) {
            return;
        }
        textField.requestFocusInWindow();
        stringBuilder.append("Enter a valid " + label.getText().trim() + "\n");
    }
}
