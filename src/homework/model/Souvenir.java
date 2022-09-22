package homework.model;

import java.io.Serializable;
import java.util.Date;

public class Souvenir implements Serializable{

    private String name;
    private Manufacturer manufacturer;
    private Date releaseDate;
    private double price;

    public Souvenir(String name, Manufacturer manufacturer, Date releaseDate, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", manufacturer=" + manufacturer.getName() +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
