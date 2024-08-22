package search.allCountry.model;

public class Country {
    private final String countryName;
    private final int imageID;

    public Country(String countryName, int imageID) {
        this.countryName = countryName;
        this.imageID = imageID;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getImageID() {
        return imageID;
    }
}
