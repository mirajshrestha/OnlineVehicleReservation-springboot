package com.ovr.onlinevehicle.reservation.helper;

import com.ovr.onlinevehicle.reservation.model.Vehicles;

public class VehicleWithScore {
    private final Vehicles vehicle;
    private final int score;

    public VehicleWithScore(Vehicles vehicle, int score) {
        this.vehicle = vehicle;
        this.score = score;
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public int getScore() {
        return score;
    }
}
