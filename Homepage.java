import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Homepage extends JFrame implements ActionListener {
  
    JButton caeser1;
    JButton aes1;
    JButton utf1;
    JButton quitButton;


    public Homepage() {
        super("Encyption-Decryption");

        caeser1 = new JButton("Caeser Method");
        aes1 = new JButton("AES_Method");
        utf1 = new JButton("UTF-8 Method");
        quitButton = new JButton("Quit");

        caeser1.addActionListener(this);
        aes1.addActionListener(this);
        utf1.addActionListener(this);
        quitButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(caeser1);
        panel.add(aes1);
        panel.add(utf1);
        panel.add(quitButton);

        getContentPane().add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
   public void actionPerformed(ActionEvent e) 
    {  
      
       if (e.getSource() == caeser1) {         
          CaesarCipher pro=new CaesarCipher();
        } 
        else if (e.getSource() == aes1) {           
            aesUI pro=new aesUI();   
        }else if (e.getSource() == utf1) {         
           UtfSwing pro= new UtfSwing(); 
        }else if (e.getSource() == quitButton) {
            System.exit(0);
         }
   }


   
  

    public static void main(String[] args) {
        Homepage project = new Homepage();
    }
}