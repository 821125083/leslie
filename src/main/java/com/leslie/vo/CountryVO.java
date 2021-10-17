package com.leslie.vo;

import com.leslie.pojo.Country;
import org.springframework.cache.annotation.EnableCaching;

import java.io.Serializable;
import java.util.Date;

public class CountryVO extends Country  {

    private Long confirm;

    private Long input;

    private Long severe;

    private Long heal;

    private Long dead;

    private Long suspect;

    private Date lastUpdateTime;

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getConfirm() {
        return confirm;
    }

    public void setConfirm(Long confirm) {
        this.confirm = confirm;
    }

    public Long getInput() {
        return input;
    }

    public void setInput(Long input) {
        this.input = input;
    }

    public Long getSevere() {
        return severe;
    }

    public void setSevere(Long severe) {
        this.severe = severe;
    }

    public Long getHeal() {
        return heal;
    }

    public void setHeal(Long heal) {
        this.heal = heal;
    }

    public Long getDead() {
        return dead;
    }

    public void setDead(Long dead) {
        this.dead = dead;
    }

    public Long getSuspect() {
        return suspect;
    }

    public void setSuspect(Long suspect) {
        this.suspect = suspect;
    }
}
