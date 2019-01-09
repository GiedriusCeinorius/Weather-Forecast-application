package com.gce.weatherforecastapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Coordinates implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double lon;
    private Double lat;

    public Coordinates(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Coordinates() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof Coordinates) {
            Coordinates c = (Coordinates) obj;
            int first = Double.compare(getLat(), c.getLat());
            int second = Double.compare(getLon(), c.getLon());
            if (first == 0 && second == 0) {
                equals = true;
            } else {
                equals = false;
            }
        }
        return equals;
    }
}
