package com.github.mawan5512.pmd.omdb;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class WebScrape {

    public static Object getInfo (String U) {

        int c;
        try {
            InputStream in = new URL(U).openStream();
            String Storage = "";
            while ((c = in.read()) != -1){
                //System.out.print((char) c);
                Storage = Storage + (char) c;
            }
            in.close();
            //System.out.print(Storage + "\n");
            String change = Storage.replace("\"","");
            String change1 = change.replace("{","");
            String change2 = change1.replace("}", "");
            String change3 = change2.replace(":", ",");
            System.out.print(change3);
            System.out.print("\n" + Title(change3) + "\n" + Year(change3) + "\n" + Genre(change3)
                    + "\n" + Runtime(change3) + "\n" + Director(change3) + "\n" + Actors(change3) + "\n" + Release(change3) +
                    "\n" + Poster(change3));
            System.out.print("\n" + Summary(change3));
            MovieInfo movie = new MovieInfo(Title(change3), Year(change3), Genre(change3)
                    ,Runtime(change3),Director(change3), Actors(change3), Summary(change3), Release(change3) , Poster(change3),ID(change3));
            return movie;
        }
        catch (Throwable err) {
            err.printStackTrace();
        }
        return null;
    }
    public static String Title (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Title = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Title")) {
                Title = Parse.next();
                return Title;
            }
        }

        return " ";
    }
    public static String Summary(String x){
        Scanner Parse = new Scanner(x).useDelimiter("Plot,");
        String Summary = "";
        String FinalSum = " ";
        if(Parse.hasNext()){
            Parse.next();
            Summary = Parse.next();
            Scanner Parse2 = new Scanner(Summary).useDelimiter(",Language");
            FinalSum = Parse2.next();

        }
        return FinalSum;
    }
    public static String Year (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Year = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Year")) {
                Year = Parse.next();
                return Year;
            }
        }

        return " ";
    }
    public static String Genre (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Genre = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Genre")) {
                Genre = Parse.next();
                return Genre;
            }
        }

        return " ";
    }
    public static String ID (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String ID = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("imdbID")) {
                ID = Parse.next() + " and "+ Parse.next();
                return ID;
            }
        }

        return " ";
    }
    public static String Runtime (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Runtime = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Runtime")) {
                Runtime = Parse.next();
                return Runtime;
            }
        }

        return " ";
    }
    public static String Director (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Director = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Director")) {
                Director = Parse.next();
                return Director;
            }
        }

        return " ";
    }
    public static String Actors (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Actors = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Actors")) {
                Actors = Parse.next() +" "+ Parse.next()+" "+ Parse.next();
                return Actors;
            }
        }

        return " ";
    }
    public static String Release (String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Release = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Released")) {
                Release = Parse.next();
                return Release;
            }
        }

        return " ";
    }
    public static String Poster(String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        String Poster = "";
        while (Parse.hasNext()) {
            if (Parse.next().contains("Poster")) {
                Poster = Parse.next() + ":" + Parse.next();
                return Poster;
            }
        }
        return " ";


    }

}
