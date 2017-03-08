package com.github.mawan5512.pmd.omdb;

public class MovieInfo {

    private final String Title;
    private final String Year;
    private final String Genre;
    private final String Runtime;
    private final String Director;
    private final String Actors;
    private final String Summary;
    private final String Release;
    private final String PicUrl;
    private final String ID;

    public MovieInfo(String title,String year,String genre,String runtime,String director,String actors, String summary, String release,String picUrl, String id ){
        Title = title;
        Year = year;
        Genre = genre;
        Runtime = runtime;
        Director = director;
        Actors = actors;
        Summary = summary;
        Release = release;
        PicUrl = picUrl;
        ID = id;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getGenre() {
        return Genre;
    }
    public String getRuntime(){
        return Runtime;
    }
    public String getDirector(){
        return Director;
    }
    public String getActors(){
        return Actors;
    }
    public String getSummary(){
        return Summary;
    }
    public String getRelease(){
        return Release;
    }
    public String getPicUrl(){
        return PicUrl;
    }
    public String getID(){return ID;}

}
