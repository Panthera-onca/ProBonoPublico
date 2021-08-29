package com.example.application.views.about;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * Simple DTO class for chart data-series.
 */
public class ChartSeries {

    @NotNull
    private String name;
    @NotNull
    private double[] data;

    public ChartSeries(String name, double... data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public double[] getData() {
        return data;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + Objects.hash(name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChartSeries other = (ChartSeries) obj;
		return Arrays.equals(data, other.data) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "ChartSeries [name=" + name + ", data=" + Arrays.toString(data) + ", getName()=" + getName()
				+ ", getData()=" + Arrays.toString(getData()) + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
    
    
}
