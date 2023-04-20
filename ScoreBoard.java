//Java Program to Add Image in Jframe - copied
import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;  

public class ScoreBoard extends JFrame {
    private int totalScore;

    private int totalScUp;
    private int scOnes;
    private int scTwos;
    private int scThrees;
    private int scFours;
    private int scFives;
    private int scSixes;
    private int bonus;

    private int totalScLow;
    private int threeKind;
    private int fourKind;
    private int fullHouse;
    private int straightSm;
    private int straightLg;
    private int yahtzee;
    private int chance;


    public ScoreBoard(){
        totalScore = 0;
    }
    public static void main(String[] args) {
        ScoreBoard p1 = new ScoreBoard();
        p1.createBoard();
    }

    // creates a preset button
    public void button(JFrame frame){
        JButton btn = new JButton("button");
        btn.setBounds(0, 0, 100, 20);
        frame.add(btn);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setMargin(new Insets(4, 4, 4, 4));
        btn.setOpaque(false);
    }

    public void createBoard(){
        // Frame
        JFrame frame = new JFrame();        
        frame.setTitle("Yahtzee!"); 
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);

        final TextField tf=new TextField();  
        tf.setBounds(1000,500, 150,20);  
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
        
        ArrayList<Button> buttons = new ArrayList<Button>();
        for (int i = 0; i < 6; i++){
            Button button1 = new Button("score");  
            button1.setBounds(x, y, BLength, BWidth); 

            // action on click
            button1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        //tf.setText("Welcome to Javatpoint.");  
                        System.out.println("hi");
                }  
            }); 

            buttons.add(button1);
            frame.add(button1);
            y += BWidth;
        }

        // total/bonus 1st section
        JLabel totalScoreUp = new JLabel(Integer.toString(25));
        totalScoreUp.setBounds(x, y, BLength, BWidth); //Sets the location of the image
        frame.add(totalScoreUp);
        y += 128;

        int BWidth2 = 35;
        for (int i = 0; i < 7; i++){
            Button button1 = new Button("score");  
            button1.setBounds(x, y, BLength, BWidth2); 
            buttons.add(button1);
            frame.add(button1);
            y += BWidth2;
        }

        // yahtzee bonus checkmarks
        y += 46;
        
        frame.add(left);
        frame.add(top);
  
        DiceInterface.setFrame(frame);
        DiceInterface.createDice();
        //p1.button(frame);

        frame.setVisible(true);
    }


    
}
