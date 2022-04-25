import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class C6E18CheckPassword {

    private static String show;

    private static boolean confirmDialog() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to try again?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return response != JOptionPane.NO_OPTION;
    }

    private static boolean quitDialog() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return response != JOptionPane.YES_OPTION;
    }

    public static boolean checkPass(String password) {
        int count = 0;
        if (password.length() == 0)  {
            show = "Please enter a password !!!";
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            if (!Character.isLetter(password.charAt(i)) && !Character.isDigit(password.charAt(i))) {
                show = "Invalid Password !!! - Must consist of only letters and digits.";
                return false;
            }
        }

        if (password.length() < 8) {
            show = "Invalid Password !!! - Must have at least eight characters.";
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            if ((Character.isDigit(password.charAt(i))))
                count++;
        }

        if (count >= 2) {
            show = "Valid Password !!!";
            return true;
        }
        else {
            show = "Invalid Password !!! - Must contain at least two digits.";
            return false;
        }
    }

    public static void main(String[] args) {
        boolean repeat = true;
        while(repeat) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("<html>A password must have at least eight characters, consisting of only letters and digits."
                    +"<br>A password must contain at least two digits."
                    +"<br><br>Password : ");

            JPasswordField pass = new JPasswordField();
            panel.add(label);
            panel.add(pass);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);

            int result = JOptionPane.showConfirmDialog(null, panel, "Check Password:", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    char[] password = pass.getPassword();
                    if (checkPass(String.valueOf(password))) {
                        JOptionPane.showMessageDialog(null, show, "Message for user", JOptionPane.INFORMATION_MESSAGE);
                        repeat = confirmDialog();
                    }
                    else
                        throw new Exception(show);
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message for user", JOptionPane.WARNING_MESSAGE);
                }
            }
            else if(result == JOptionPane.CANCEL_OPTION)
                repeat = quitDialog();
        }
    }
}
