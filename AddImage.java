//Java Program to Add Image in Jframe - copied
import javax.swing.*;
import java.awt.*;

public class AddImage extends JFrame {
    public AddImage(){

    }
    public static void main(String[] args) {
        AddImage p1 = new AddImage();

        JFrame frame = new JFrame(); //JFrame Creation       
        frame.setTitle("Add Image"); //Add the title to frame
        frame.setLayout(null); //Terminates default flow layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        frame.setBounds(100, 200, 350, 300); //Sets the position of the frame
        
        Container c = frame.getContentPane(); //Gets the content layer

        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("/workspace/FinalProjectGUI/yahtzeejpg.jpeg")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
 
        c.add(label); //Adds objects to the container
        frame.setVisible(true); // Exhibit the frame

        p1.button(frame);
        JLabel label1 = new JLabel("OOOOOOOOOOOO");
        //Set the position of the text, relative to the icon:
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setHorizontalTextPosition(JLabel.CENTER);


        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
}
