import java.security.*;
import java.security.spec.*;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.JOptionPane;

public class UtfSwing {

    private final String ALGO = "DES";
    private final String MODE = "ECB";
    private final String PADDING = "PKCS5Padding";
    private static int mode = 0;

    public static void main(String args[]) {
        UtfSwing me = new UtfSwing();
    }
    public UtfSwing()
    {   
        
        switch (mode) {
            case 0:
                encrypt();
                decrypt();
                break;
            case 1:
                decrypt();
                break;
            default:
                
                encrypt();
                decrypt();
        }
    }

    public void encrypt() {
        try {
            System.out.println("Start encryption ...");
            String input = getInputData();
            System.out.println("Input data : " + input);
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGO);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.init(56, random);
            Key sharedKey = keyGen.generateKey();
            Cipher c = Cipher.getInstance(ALGO + "/" + MODE + "/" + PADDING);
            System.out.println();
            c.init(Cipher.ENCRYPT_MODE, sharedKey);
            byte[] ciphertext = c.doFinal(input.getBytes());
            JOptionPane.showMessageDialog(null, "The encrypted data is:\n" + new String(ciphertext, "UTF8"),
                    "Encryption Result", JOptionPane.INFORMATION_MESSAGE);
            save(sharedKey.getEncoded(), "shared.key");
            save(ciphertext, "encrypted.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrypt() {
        try {
            byte[] encoded = load("shared.key");
            SecretKeyFactory kf = SecretKeyFactory.getInstance(ALGO);
            KeySpec ks = new DESKeySpec(encoded);
            SecretKey ky = kf.generateSecret(ks);
            byte[] ciphertext = load("encrypted.txt");
            Cipher c = Cipher.getInstance(ALGO + "/" + MODE + "/" + PADDING);
            c.init(Cipher.DECRYPT_MODE, ky);
            byte[] plainText = c.doFinal(ciphertext);
            JOptionPane.showMessageDialog(null, "The decrypted data is:\n" + new String(plainText, "UTF8"),
                    "Decryption Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getInputData() {
        String id = JOptionPane.showInputDialog(null, "Enter ID:");
        String name = JOptionPane.showInputDialog(null, "Enter Name:");
        String contact = JOptionPane.showInputDialog(null, "Enter Contact:");
        String tel = JOptionPane.showInputDialog(null, "Enter Telephone No:");
        final String rc = System.getProperty("line.separator");
        StringBuffer buf = new StringBuffer();
        buf.append(id);
        buf.append(rc);
        buf.append(name);
        buf.append(rc);
        buf.append(contact);
        buf.append(rc);
        buf.append(tel);
        return buf.toString();
    }

    private void save(byte[] buf, String file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(buf);
        fos.close();
    }

    private byte[] load(String file) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        fis.close();
        return buf;
    }
}