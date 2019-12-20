/**
 * This file Copyright (c) 2014 Magnolia International
 * Ltd.  (http://www.magnolia-cms.com). All rights reserved.
 * <p>
 * <p>
 * This file is licensed under the MIT License (MIT)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ch.yaro.bls.rest.pojos;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is a simple POJO representation for a "Reservation" item stored in the "reservationen" app.<br/>
 */
public class Reservation extends NodeItem {

    public static final String WORKSPACE = "reservation";
    public static final String NODETYPE = "reservation";
    public static final String BASEPATH = "/";

    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String DATEOFBIRTH = "dateOfBirth";
    public static final String ZUGSERVICEID = "zugserviceID";
    public static final String WAGENNUMBER = "wagenNumber";
    public static final String SITZNUMBER = "sitzNumber";
    public static final String FROMID = "fromID";
    public static final String TOID = "toID";

    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String zugserviceID;
    private String wagenNumber;
    private String sitzNumber;
    private String departure;
    private String destination;
    @JsonIgnore
    private String fromID;
    @JsonIgnore
    private String toID;



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getZugserviceID() {
        return zugserviceID;
    }

    public void setZugserviceID(String zugserviceID) {
        this.zugserviceID = zugserviceID;
    }

    public String getWagenNumber() {
        return wagenNumber;
    }

    public void setWagenNumber(String wagenNumber) {
        this.wagenNumber = wagenNumber;
    }

    public String getSitzNumber() {
        return sitzNumber;
    }

    public void setSitzNumber(String sitzNumber) {
        this.sitzNumber = sitzNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFromID() {
        return fromID;
    }

    public void setFromID(String fromID) {
        this.fromID = fromID;
    }

    public String getToID() {
        return toID;
    }

    public void setToID(String toID) {
        this.toID = toID;
    }

    @Override
    public String toString(){
        return "First: " + firstname + ", last: " + lastname + ", trainservice: " + zugserviceID + ", wagen nb: " + wagenNumber + ", seat nb: " + sitzNumber + ", from: " + departure + ", to: " + destination;
    }

}
