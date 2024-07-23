import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CaesarCipher {
    private static final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private JFrame frame;
    private JPanel panel;
    private JLabel label1, label2, label3;
    private JTextField textField1, textField2, textField3;
    private JButton encryptButton, decryptButton;
    
    public CaesarCipher() {
        String data =   JOptionPane.showInputDialog( "Enter text to be encrypted and decrypted" ); 
        String keys =JOptionPane.showInputDialog( "Enter key" );
    
        int key = Integer.parseInt( keys );
        JOptionPane.showMessageDialog( null, "The Encrypted message is "+encrypt(data, key)); 
        JOptionPane.showMessageDialog( null, "The Decrypted message is "+decrypt(encrypt(data, key), key)); 

    }
    
    public static String encrypt(String plainText, int shiftKey) {
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            int charPosition = -1;
            char replaceVal;
            int keyVal = -1;
            char val = plainText.charAt(i);

            if (Character.isUpperCase(val)) {
                charPosition = ALPHABET_UPPER.indexOf(val);
                if (charPosition != -1) {
                    keyVal = (shiftKey + charPosition) % 26;
                    replaceVal = ALPHABET_UPPER.charAt(keyVal);
                } else {
                    replaceVal = plainText.charAt(i);
                }           
            } else {
                charPosition = ALPHABET_LOWER.indexOf(val);
                if (charPosition != -1) {
                    keyVal = (shiftKey + charPosition) % 26;
                    replaceVal = ALPHABET_LOWER.charAt(keyVal);
                } else {
                    replaceVal = plainText.charAt(i);
                }
            }       
            cipherText += replaceVal;        
        }
        return cipherText;
    }
    public static String decrypt(String cipherText, int shiftKey)
{
    String plainText = "";
    for (int i = 0; i < cipherText.length(); i++)
    {
        int charPosition = -1;
        char replaceVal;
        int keyVal = -1;
        char val = cipherText.charAt(i);

        if(Character.isUpperCase(val)) {
            charPosition = ALPHABET_UPPER.indexOf(val);
            if(charPosition != -1) {
                keyVal = (charPosition - shiftKey) % 26;
                if (keyVal < 0) {
                    keyVal = ALPHABET_UPPER.length() + keyVal;
                }
                replaceVal = ALPHABET_UPPER.charAt(keyVal);
            } else {
                replaceVal = cipherText.charAt(i);
            }           
        } else {
            charPosition = ALPHABET_LOWER.indexOf(val);
            if(charPosition != -1) {
                keyVal = (charPosition - shiftKey) % 26;
                if (keyVal < 0) {
                    keyVal = ALPHABET_LOWER.length() + keyVal;
                }
                replaceVal = ALPHABET_LOWER.charAt(keyVal);
            } else {
                replaceVal = cipherText.charAt(i);
            }
        }
        plainText += replaceVal;
    }
    return plainText;
}

public static void main(String[] args)
{ 
     CaesarCipher aesEncDe = new CaesarCipher();
}
}