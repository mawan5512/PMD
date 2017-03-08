package com.github.mawan5512.pmd.omdb;

public class MovieInfo {

    String Title;
    String Year;
    String Genre;
    String Runtime;
    String Director;
    String Actors;
    String Summary;
    String Release;
    String PicUrl;
    String ID;

    MovieInfo(String title,String year,String genre,String runtime,String director,String actors, String summary, String release,String picUrl, String id ){
        String Title = title;
        String Year = year;
        String Genre = genre;
        String Runtime = runtime;
        String Director = director;
        String Actors = actors;
        String Summary = summary;
        String Release = release;
        String PicUrl = picUrl;
        String ID = id;
    }

    String getTitle() {
        return Title;
    }

    String getYear() {
        return Year;
    }

    String getGenre() {
        return Genre;
    }
    String getRuntime(){
        return Runtime;
    }
    String getDirector(){
        return Director;
    }
    String getActors(){
        return Actors;
    }
    String getSummary(){
        return Summary;
    }
    String getRelease(){
        return Release;
    }
    String getPicUrl(){
        return PicUrl;
    }
    String getID(){return ID;}

}
