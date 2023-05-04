package fje.edu.m03uf6projecterestaurant;

public class Ingredient {
    private int id;
    private String nom;
    private int preu;

    public Ingredient(int id, String nom, int preu) {
        this.id = id;
        this.nom = nom;
        this.preu = preu;
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

    public int getPreu() {
        return preu;
    }

    public void setPreu(int preu) {
        this.preu = preu;
    }
}
