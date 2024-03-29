package com.leslie.vo;

import com.leslie.pojo.City;
import com.leslie.pojo.Province;

import java.util.List;

public class ProvinceVO extends Province {

    private Integer totalCured;

    private Integer totalDeath;

    private Integer totalIncrease;

    private Integer totalDoubtful;

    private Integer totalConfirmed;

    public Integer getTotalCured() {
        return totalCured;
    }

    public void setTotalCured(Integer totalCured) {
        this.totalCured = totalCured;
    }

    public Integer getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(Integer totalDeath) {
        this.totalDeath = totalDeath;
    }

    public Integer getTotalIncrease() {
        return totalIncrease;
    }

    public void setTotalIncrease(Integer totalIncrease) {
        this.totalIncrease = totalIncrease;
    }

    public Integer getTotalDoubtful() {
        return totalDoubtful;
    }

    public void setTotalDoubtful(Integer totalDoubtful) {
        this.totalDoubtful = totalDoubtful;
    }

    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(Integer totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}