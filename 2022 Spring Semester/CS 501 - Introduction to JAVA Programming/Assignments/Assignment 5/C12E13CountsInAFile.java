import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class C12E13CountsInAFile {

    private static boolean confirmDialog() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to try again?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return response != JOptionPane.NO_OPTION;
    }

    public static void main(String [] args) {
        boolean repeat = true;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("\\"));

        while(repeat) {
            int result = fileChooser.showOpenDialog(null);

            BufferedReader br = null;

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getName();
                String currentLine;
                try {
                    br = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getPath()));

                    int lineNum = 0;
                    int wordCount = 0;
                    int charCount = 0;

                    while ((currentLine = br.readLine()) != null) {
                        lineNum++;

                        String[] word = currentLine.split("[\r \n\t,;:.]");
                        for (String s : word) {
                            if (s.length() > 0) {
                                wordCount++;
                                charCount += s.length();
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Number of Lines : "
                                    +lineNum+"\nNumber of Words : "
                                    +wordCount+"\nNumber of Characters : "
                                    +charCount,"Information of "+filename
                            ,JOptionPane.INFORMATION_MESSAGE);

                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
                finally{
                    try {
                        if (br != null)
                            br.close();
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                }
            }
            else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Select a text file !!!.");
            }
            else if (result == JFileChooser.ERROR_OPTION) {
                JOptionPane.showMessageDialog(null, "Error !!!");
            }
            repeat = confirmDialog();
        }
    }
}

