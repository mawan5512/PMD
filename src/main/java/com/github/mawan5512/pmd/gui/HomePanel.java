package com.github.mawan5512.pmd.gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HomePanel extends JPanel {

    private Object ImageI0;

    public HomePanel() throws IOException {

        //setBackground(Color.RED);
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));

        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");


        p1.add(imageOnButton(one, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTM3NTg2NDQzOF5BMl5BanBnXkFtZTcwNjc2NzQzOQ@@._V1_SX300.jpg"));
        p1.add(imageOnButton(two, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAzOTM1MzE0OF5BMl5BanBnXkFtZTYwNDE0Nzc4._V1_SX300.jpg"));
        p1.add(imageOnButton(three, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY4MTUxMjQ5OV5BMl5BanBnXkFtZTcwNTUyMzg5Ng@@._V1_SX300.jpg"));
        //add(p1);

        JPanel p2 = new JPanel();

        p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));

        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");


        p2.add(imageOnButton(b1, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg"));
        p2.add(imageOnButton(b2, "https://images-na.ssl-images-amazon.com/images/M/MV5BNDg1NTU2OWEtM2UzYi00ZWRmLWEwMTktZWNjYWQ1NWM1OThjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"));
        p2.add(imageOnButton(b3, "https://images-na.ssl-images-amazon.com/images/M/MV5BOTgxMDQwMDk0OF5BMl5BanBnXkFtZTgwNjU5OTg2NDE@._V1_SX300.jpg"));
        p2.add(imageOnButton(b4, "https://images-na.ssl-images-amazon.com/images/M/MV5BYmEwNjNlZTUtNzkwMS00ZTlhLTkyY2MtMjM2MzlmODQyZGVhXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_SX300.jpg"));
        p2.add(imageOnButton(b5, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTgyODYwMjM4OV5BMl5BanBnXkFtZTcwNzM3MjA1OA@@._V1_SX300.jpg"));
        //p2.add(imageOnButton(b6, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg"));



        JPanel p3 = new JPanel();

        p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));

        JButton c1 = new JButton("1");
        JButton c2 = new JButton("2");
        JButton c3 = new JButton("3");
        JButton c4 = new JButton("4");
        JButton c5 = new JButton("5");
        JButton c6 = new JButton("6");

        p3.add(imageOnButton(c1, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjc4MTUxN2UtMmU1NC00MjQyLTk3YTYtZTQ0YzEzZDc0Njc0XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"));
        p3.add(imageOnButton(c2, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY4MTUxMjQ5OV5BMl5BanBnXkFtZTcwNTUyMzg5Ng@@._V1_SX300.jpg"));
        p3.add(imageOnButton(c3, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjI4NDQwMDM0N15BMl5BanBnXkFtZTcwMzY1ODMwNA@@._V1_SX300.jpg"));
        p3.add(imageOnButton(c4, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTg0MTU5ODkwM15BMl5BanBnXkFtZTYwMzgxNzY3._V1_SX300.jpg"));
        p3.add(imageOnButton(c5, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYzOTc2NzU3N15BMl5BanBnXkFtZTcwNjY3MDE3NQ@@._V1_SX300.jpg"));
        //p3.add(imageOnButton(c6, "http://ia.media-imdb.com/images/M/MV5BMjUxOTA4OTc5Ml5BMl5BanBnXkFtZTgwNDY1Njk0NzE@._V1_SX300.jpg"));


        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4,BoxLayout.X_AXIS));

        JButton d1 = new JButton();
        JButton d2 = new JButton();
        JButton d3 = new JButton();
        JButton d4 = new JButton();
        JButton d5 = new JButton();
        JButton d6 = new JButton();



        d3 = imageOnButton(d3, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTU2NjA1ODgzMF5BMl5BanBnXkFtZTgwMTM2MTI4MjE@._V1_SX300.jpg");
        p4.add(d3);
        // LibraryPanel.movieDetails(d3, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTU2NjA1ODgzMF5BMl5BanBnXkFtZTgwMTM2MTI4MjE@._V1_SX300.jpg");
//p4.add(Box.createRigidArea(new Dimension(200, 200)));
        p4.setBackground(Color.RED);
        p4.add(imageOnButton(d2, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg"));
        p4.add(imageOnButton(d3, "https://images-na.ssl-images-amazon.com/images/M/MV5BOWU0ZGE4YjMtNmY5Yy00M2RhLWE0ZGQtZjViMTBhZTE5MWM4XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_SX300.jpg"));
        p4.add(imageOnButton(d4, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTU5MDg0NTQ1N15BMl5BanBnXkFtZTcwMjA4Mjg3Mg@@._V1_SX300.jpg"));
        p4.add(imageOnButton(d5, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQxNDQwNjQzOV5BMl5BanBnXkFtZTcwNTQxNDYyMQ@@._V1_SX300.jpg"));
        //p4.add(imageOnButton(d6, "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY5MDMzODUyOF5BMl5BanBnXkFtZTcwMTQ3NTMyNA@@._V1_SX300.jpg"));


        JPanel p5 = new JPanel();
        p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
        p5.setBackground(Color.gray);
        p5.add(Box.createRigidArea(new Dimension(0, 10)));
        p5.add(p1);
        p5.add(Box.createRigidArea(new Dimension(0, 150)));
        p5.add(p2);
        p5.add(Box.createRigidArea(new Dimension(0, 50)));
        p5.add(p3);
        p5.add(Box.createRigidArea(new Dimension(0, 50)));
        p5.add(p4);

        add(p5);

        JScrollPane sp = new JScrollPane(p5);
        sp.setPreferredSize(new Dimension(1360, 670));
        add(sp);




    }

    private static JButton imageOnButton(JButton B, String sourceUrl) throws MalformedURLException
    {
        ImageIcon image =  new ImageIcon (new ImageIcon (new URL(sourceUrl)).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        try{
            B = new JButton(image);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //String s = "red";
        B.setBackground(Color.gray);
        B.setOpaque(true);
        B.setBorderPainted(false);
        return B;

    }

}
