package fje.edu.m03uf6projecterestaurant.classes;

import java.util.ArrayList;

public class Plat {
    private int id;
    private String nom;
    private String descripcio;
    private double preu;
    private ArrayList<Ingredient> ingredients;
    private String urlIMG;

    public Plat(int id, String nom, String descripcio, double preu, ArrayList<Ingredient> ingredients, String urlIMG) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.preu = preu;
        this.ingredients = ingredients;
        this.urlIMG = urlIMG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getUrlIMG() {
        return urlIMG;
    }

    public void setUrlIMG(String urlIMG) {
        this.urlIMG = urlIMG;
    }
}
