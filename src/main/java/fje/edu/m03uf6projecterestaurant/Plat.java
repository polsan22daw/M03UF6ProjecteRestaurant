package fje.edu.m03uf6projecterestaurant;

import java.util.ArrayList;

public class Plat {
    private int id;
    private String nom;
    private String descripcio;
    private double preu;
    private int kcal;
    private ArrayList<Ingredient> ingredients;
    private String urlIMG;

    public Plat(int id, String nom, String descripcio, double preu, int kcal, ArrayList<Ingredient> ingredients, String urlIMG) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.preu = preu;
        this.kcal = kcal;
        this.ingredients = ingredients;
        this.urlIMG = urlIMG;
    }




}
