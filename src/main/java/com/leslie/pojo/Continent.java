package com.leslie.pojo;

import java.util.List;

/**
 * 大洲
 */
public class Continent {
    private String continentName;

    private Integer id;

    private List<Country> country;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "continentName='" + continentName + '\'' +
                ", id=" + id +
                ", country=" + country +
                '}';
    }
}
