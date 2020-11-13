package com.leslie.pojo;

/**
 * 国家
 */
public class Country {

    private Integer id;

    private String provinceName;

    private Integer countryId;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", provinceName='" + provinceName + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
