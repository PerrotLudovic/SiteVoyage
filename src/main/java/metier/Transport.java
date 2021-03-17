package metier;

public enum Transport {
	  train (100, 10), bus (50, 20), avion(170,5), bateau(90,15);

    private double prix;
    private int duree;

    private Transport(double prix, int duree) {
        this.prix = prix;
        this.duree = duree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

}
