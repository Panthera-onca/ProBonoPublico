package com.example.application.views.about;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

/**
 * Simple DTO class for the inbox list to demonstrate complex object data
 */
public class HealthGridItem {

	@NotNull
    private LocalDate date;
	@NotNull
    private String city;
	@NotNull
    private String country;
	@NotNull
    private String status;
	@NotNull
    private String theme;

    public HealthGridItem() {

    }

    public HealthGridItem(LocalDate date, String city, String country, String status, String theme) {
        this.date = date;
        this.city = city;
        this.country = country;
        this.status = status;
        this.theme = theme;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
