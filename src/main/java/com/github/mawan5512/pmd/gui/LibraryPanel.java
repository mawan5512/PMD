package com.github.mawan5512.pmd.gui;

import com.github.mawan5512.pmd.PMD;
import com.github.mawan5512.pmd.database.Add;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryPanel extends JPanel {

    //private String[] URLs;
    private JPanel panel;
    private JPanel buttonPanel;
    private JButton[] B;
    private JButton[] C;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private final PMD pmd;

    public LibraryPanel(PMD pmd)
        {
            this.pmd = pmd;
            String[]URLs = {"https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BNDg1NTU2OWEtM2UzYi00ZWRmLWEwMTktZWNjYWQ1NWM1OThjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BOTgxMDQwMDk0OF5BMl5BanBnXkFtZTgwNjU5OTg2NDE@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BYmEwNjNlZTUtNzkwMS00ZTlhLTkyY2MtMjM2MzlmODQyZGVhXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTgyODYwMjM4OV5BMl5BanBnXkFtZTcwNzM3MjA1OA@@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMjIyODA2NDg4NV5BMl5BanBnXkFtZTcwMjg4NDAwMw@@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTU0MzU4ODI3Ml5BMl5BanBnXkFtZTgwMzQzODk5NDE@._V1_SX300.jpg"};



            panel = new JPanel();
            JPanel north = new JPanel();
            buttonPanel = new JPanel();
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
            String languages[]={"Sort","Title","Genre","Runtime"};
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

                        try {
                            pmd.addToLibrary(pmd.getOmdbMovieSearcher().getInfo(pmd.getOmdbMovieSearcher().search(numStr).getCurrentPage().getResultList().get(0).getId()).get());
                        } catch (IOException e2) {
                            System.err.println("search/getinfo throws IOE");
                        }

                        //if(JOptionPane.YES_OPTION)
                        JOptionPane.showMessageDialog(null, "your movie has been added");
                        again = JOptionPane.showConfirmDialog(null, "Do Another?");

                    }
                    while(again == JOptionPane.YES_OPTION);

                }
            });

            //for buttonPanel
            B = new JButton[9];
            for(int i = 0; i < 9; ++i)
            {
                B[i] = new JButton();
                B[i] = imageOnButton(B[i], URLs[i]);
                buttonPanel.add(B[i]);
                movieDetails(B[i], URLs[i]);
            }

//for bottom buttons
            C = new JButton[4];

            C[0] = new JButton("<<");
            C[1] = new JButton("<");
            C[2] = new JButton(">");
            C[3] = new JButton(">>");
            for(int i = 0; i < 4; ++i)
            {
                south.add(C[i]);
                nextPage(C[i], URLs);
            }


            //put it all together
            panel.add(Box.createRigidArea(new Dimension(0, 0)));
            panel.add(north);
            panel.add(Box.createRigidArea(new Dimension(0, 100)));
            panel.add(buttonPanel);
            panel.add(Box.createRigidArea(new Dimension(0, 50)));
            panel.add(south);

            add(panel);






        }

    private static JButton imageOnButton(JButton B, String sourceURL)
    {
        ImageIcon image;
        try {
            image = new ImageIcon(new ImageIcon(new URL(sourceURL)).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
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

    private static JLabel imageOnLabel(JLabel L, String sourceURL) throws MalformedURLException
    {
        ImageIcon image =  new ImageIcon (new ImageIcon (new URL(sourceURL)).getImage().getScaledInstance(325, 425, Image.SCALE_DEFAULT));
        try{
            L = new JLabel(image);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //String s = "red";
        L.setBackground(Color.LIGHT_GRAY);
        L.setOpaque(true);
        //B.setBorderPainted(false);
        return L;

    }

    public void movieDetails(JButton B, String sourceURL)
    {
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    remove(panel);
                    JPanel full = new JPanel();
                    JPanel picpanel = new JPanel();
                    JPanel infopanel = new JPanel();

                    full.setLayout(new GridLayout(1, 3, 5, 0));
                    picpanel.setLayout(new BoxLayout(picpanel, BoxLayout.Y_AXIS));
                    infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.Y_AXIS));

                    full.setBackground(Color.blue);
                    picpanel.setBackground(Color.blue);
                    infopanel.setBackground(Color.blue);

                    full.setPreferredSize(new Dimension(1360, 670));

                    JLabel piclabel = new JLabel();
                    piclabel = imageOnLabel(piclabel, sourceURL);
                    JButton back = new JButton("back to my library");

                    picpanel.add(Box.createRigidArea(new Dimension(0, 30)));
                    picpanel.add(back);
                    picpanel.add(Box.createRigidArea(new Dimension(50, 100)));
                    picpanel.add(piclabel);

                    full.add(picpanel);
                    full.add(infopanel);

                    add(full);

                    back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            remove(full);
                            repaint();
                            add(panel);


                        }
                    });

                } catch (MalformedURLException ex) {
                    Logger.getLogger(LibraryPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    //need an array for URL's
    public void nextPage(JButton jb, String[] sourceURL)
    {

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    buttonPanel.removeAll();
                    for(int i = 0; i < 8; ++i)
                    {
                        B[i] = imageOnButton(B[i], sourceURL[i + 1]);
                        movieDetails(B[i], sourceURL[i + 1]);
                    }
                    B[8] = imageOnButton(B[8], sourceURL[0]);
                    movieDetails(B[0], sourceURL[0]);

                    //move inside previous loop
                    for(int i = 0; i < 9; ++i)
                    {
                        buttonPanel.add(B[i]);
                    }


                    buttonPanel.updateUI();
            }
        });
    }

}
