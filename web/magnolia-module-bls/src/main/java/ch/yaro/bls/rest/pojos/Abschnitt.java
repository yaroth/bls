/**
 * This file Copyright (c) 2014 Magnolia International
 * Ltd.  (http://www.magnolia-cms.com). All rights reserved.
 *
 *
 * This file is licensed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */
package ch.yaro.bls.rest.pojos;


import java.util.ArrayList;

/**
 * This class is a simple POJO representation for a "Abschnitt" item stored in the
 * 'Strecken' app in the 'Strecke' repository.<br/>
 * Is used to define whether the {@link Strecke} is reserved till the next {@link Stop}.
 * Contains a waggon list of {@link WagenReservation} listing which {@link Wagen} has
 * which {@link Seat} reserved on this specific Abschnitt.
 */
public class Abschnitt {

    public static final String WORKSPACE = "strecken";
    public static final String NODETYPE = "mgnl:contentNode";
    public static final String BASEPATH = "/";

    public static final String STOP_ID = "stopID";
    public static final String STOP_DURATION = "stopduration";
    public static final String TRIP_DURATION = "tripduration";

    private String stopName;
    private int stopDuration;
    private int tripDuration;
    private boolean isReservedTillNextStop = false;
    private ArrayList<WagenReservation> waggonReservationList = new ArrayList<>();


    public ArrayList<WagenReservation> getWaggonReservationList() {
        return waggonReservationList;
    }

    public void setWaggonReservationList(ArrayList<WagenReservation> waggonReservationList) {
        this.waggonReservationList = waggonReservationList;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }


    public int getStopDuration() {
        return stopDuration;
    }

    public void setStopDuration(int stopDuration) {
        this.stopDuration = stopDuration;
    }

    public int getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(int tripDuration) {
        this.tripDuration = tripDuration;
    }

    public boolean isReservedTillNextStop() {
        return isReservedTillNextStop;
    }

    public void setReservedTillNextStop(boolean reservedTillNextStop) {
        isReservedTillNextStop = reservedTillNextStop;
    }

    public boolean containsWaggon(Integer waggonNumber) {
        for (WagenReservation wr : waggonReservationList) {
            if (wr.getWaggonNumber().equals(waggonNumber)) return true;
        }
        return false;
    }

    public WagenReservation getWagenReservation(Integer waggonNumber) {
        for (WagenReservation wr : waggonReservationList) {
            if (wr.getWaggonNumber().equals(waggonNumber)) return wr;
        }
        return null;
    }
}
