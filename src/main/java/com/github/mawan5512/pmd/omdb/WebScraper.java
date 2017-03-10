package com.github.mawan5512.pmd.omdb;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class WebScraper {

    public static Optional<MovieInfo> getInfo (String jsonText) throws IOException {
            String change = jsonText.replace("\"", "");
            String change1 = change.replace("{", "");
            String change2 = change1.replace("}", "");
            String change3 = change2.replace(":", ",");

            Optional<String> error = getError(change3);
            if (error.isPresent()) {
                if (error.get().contains("Movie not found!"))
                    return Optional.empty();
                else
                    throw new OmdbResponseException(error.get());
            }

            MovieInfo movie = new MovieInfo(Title(change3), Year(change3), Genre(change3)
                    , Runtime(change3), Director(change3), Actors(change3), Summary(change3), Release(change3), Poster(change3), ID(change3));
            return Optional.of(movie);
    }

    public static Optional<String> getError(String x) {
        Scanner Parse = new Scanner(x).useDelimiter(",");
        while (Parse.hasNext()) {
            if (Parse.next().contains("Error")) {
                return Optional.of(Parse.next());
            }
        }

        return Optional.empty();
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
                ID = Parse.next();
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
