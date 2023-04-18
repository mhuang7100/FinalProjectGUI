//Java Program to Add Image in Jframe - copied
import javax.swing.*;
import java.awt.*;
import java.awt.Button;  
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  

public class AddImage extends JFrame {
    public AddImage(){

    }
    public static void main(String[] args) {
        AddImage p1 = new AddImage();
        p1.createBoard();
    }

    public void button(JFrame frame){
        JButton btn = new JButton("button");
        btn.setBounds(1700, 200, 100, 20);
        frame.add(btn);

        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setMargin(new Insets(4, 4, 4, 4));
        btn.setOpaque(false);
    }
    public void createBoard(){
        // Frame
        JFrame frame = new JFrame();        
        frame.setTitle("Add Image"); 
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);

        // labels
        ImageIcon icon = new ImageIcon("/workspace/FinalProjectGUI/yahtzeeLEFT.jpg");
        ImageIcon icon2 = new ImageIcon("/workspace/FinalProjectGUI/yahtzeeTop.jpg");
        //left image
        JLabel left = new JLabel(icon);
        Dimension size1 = left.getPreferredSize(); //Gets the size of the image
        left.setBounds(0, 0, size1.width, size1.height); //Sets the location of the image
        // top image
        JLabel top = new JLabel(icon2);
        Dimension size2 = top.getPreferredSize(); //Gets the size of the image
        top.setBounds(250, 0, size2.width, size2.height); //Sets the location of the image
        // buttons
        int x = size1.width;
        int y = 112;
        int BWidth = 41;
        int BLength = 53;
        for (int i = 0; i < 6; i++){
            Button button1 = new Button("score");  
            button1.setBounds(x, y, BLength, BWidth); 
            frame.add(button1);
            y += BWidth;
        }

        // total/bonus 1st section
        y += 128;

        int BWidth2 = 35;
        for (int i = 0; i < 7; i++){
            Button button1 = new Button("score");  
            button1.setBounds(x, y, BLength, BWidth2); 
            frame.add(button1);
            y += BWidth2;
        }

        // yahtzee bonus checkmarks
        y += 46;
        



        frame.add(left);
        frame.add(top);
  
        //p1.button(frame);

        frame.setVisible(true);
    }
}
