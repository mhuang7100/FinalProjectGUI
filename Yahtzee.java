import java.awt.FlowLayout;

import javax.swing.*;

public class Yahtzee{
    int begin;
    public Yahtzee(){
        begin = 0;
    }
    public static void main(String[] args){
        JPanel panel = new JPanel();
        JLabel label = new JLabel( new ImageIcon("/workspace/FinalProjectGUI/yahtzeejpg.jpeg") );
        label.setLayout( new FlowLayout() );
        JButton button1 = new JButton("Button1");
        label.add(button1);
        JButton button2 = new JButton("Button1");
        label.add(button2);
    }
}