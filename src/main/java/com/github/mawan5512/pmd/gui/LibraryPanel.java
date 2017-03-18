package com.github.mawan5512.pmd.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class LibraryPanel extends JPanel {

    public LibraryPanel()
    {
        JPanel panel = new JPanel();
        JPanel north = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel south = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        buttonPanel.setLayout(new GridLayout(3, 3, 200, 60));
        south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));

        setBackground(Color.LIGHT_GRAY);
        panel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        //for north
        final JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(20,20);
        JButton b=new JButton("Apply");
        //b.setBounds(200,100,75,20);
        String languages[]={"Sort","1","2","3","4"};
        final JComboBox cb=new JComboBox(languages);
        //cb.setBounds(50, 100,90,20);
        cb.setMaximumSize( cb.getPreferredSize() );

        north.add(label);
        north.add(cb);
        north.add(b);
        //north.add(Box.);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = "Programming language Selected: "
                        + cb.getItemAt(cb.getSelectedIndex());
                label.setText(data);
//setBackground(Color.GREEN);
            }
        });

        JButton a1 = new JButton("Filter");
        JButton a2 = new JButton("Add a Movie");

        north.add(a1);
        north.add(a2);

        a2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numStr;
                String result;

                String movie;
                int again;
//int again2;

//switch around so that when you exit it exits
                do
                {
                    numStr = JOptionPane.showInputDialog("Enter a movie title");
                    movie = numStr;

                    //if(JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "your movie has been added");
                    again = JOptionPane.showConfirmDialog(null, "Do Another?");

                }
                while(again == JOptionPane.YES_OPTION);

            }
        });

        //for buttonPanel
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        JButton b4 = new JButton();
        JButton b5 = new JButton();
        JButton b6 = new JButton();
        JButton b7 = new JButton();
        JButton b8 = new JButton();
        JButton b9 = new JButton();


        buttonPanel.add(imageOnButton(b1, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b2, "https://images-na.ssl-images-amazon.com/images/M/MV5BNDg1NTU2OWEtM2UzYi00ZWRmLWEwMTktZWNjYWQ1NWM1OThjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b3, "https://images-na.ssl-images-amazon.com/images/M/MV5BOTgxMDQwMDk0OF5BMl5BanBnXkFtZTgwNjU5OTg2NDE@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b4, "https://images-na.ssl-images-amazon.com/images/M/MV5BYmEwNjNlZTUtNzkwMS00ZTlhLTkyY2MtMjM2MzlmODQyZGVhXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b5, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTgyODYwMjM4OV5BMl5BanBnXkFtZTcwNzM3MjA1OA@@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b6, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b7, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjIyODA2NDg4NV5BMl5BanBnXkFtZTcwMjg4NDAwMw@@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b8, "https://images-na.ssl-images-amazon.com/images/M/MV5BN2RiM2I5YjUtY2E5YS00Zjk2LWIzNzgtNTg3MGVjYjlhNDg3XkEyXkFqcGdeQXVyNjQ2MjQ5NzM@._V1_SX300.jpg"));
        buttonPanel.add(imageOnButton(b9, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTU0MzU4ODI3Ml5BMl5BanBnXkFtZTgwMzQzODk5NDE@._V1_SX300.jpg"));


        //buttonPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));

        JButton c1 = new JButton("<<");
        JButton c2 = new JButton("<");
        JButton c3 = new JButton(">");
        JButton c4 = new JButton(">>");


        south.add(c1);
        south.add(c2);
        south.add(c3);
        south.add(c4);

        //put it all together
        panel.add(Box.createRigidArea(new Dimension(0, 0)));
        panel.add(north);
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(buttonPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(south);

        add(panel);
















        /*setLayout(new GridLayout(1,1));

        //JFrame frame = new JFrame("PMD");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel lib = new JPanel();
    JLabel imageLabel = new JLabel();
    JPanel imagePanel = new JPanel();
    imagePanel.add(imageLabel);
    imagePanel.setBackground(Color.WHITE);

    ListPanel imageList = new ListPanel(imageLabel);

    JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imageList, imagePanel);
    sp.setPreferredSize(new Dimension(1300, 4000));
    sp.setOneTouchExpandable(true);

    lib.add(sp);
    //lib.setPreferredSize(new Dimension(1000, 4000));
    add(lib);
    /*frame.getContentPane().add(sp);
    frame.pack();
    frame.setVisible(true);
    //add(frame);
    */

    }

    private static JButton imageOnButton(JButton B, String sourceUrl)
    {
        ImageIcon image;
        try {
            image = new ImageIcon(new ImageIcon(new URL(sourceUrl)).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        } catch (MalformedURLException impossible) {
            throw new AssertionError();
        }
        try{
            B = new JButton(image);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //String s = "red";
        B.setBackground(Color.LIGHT_GRAY);
        B.setOpaque(true);
        B.setBorderPainted(false);
        return B;

    }

}
