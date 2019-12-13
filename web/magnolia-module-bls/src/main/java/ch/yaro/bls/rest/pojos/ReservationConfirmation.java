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


/**
 * Needed to return a ReservationConfirmation when requesting a reservation.
 */
public class ReservationConfirmation extends Reservation {

    private String message;
    // TODO: do we need the qrCode???
    private String qrCode;


    public ReservationConfirmation(Reservation reservation) {
        super.setFirstname(reservation.getFirstname());
        super.setLastname(reservation.getLastname());
        super.setDateOfBirth(reservation.getDateOfBirth());
        super.setZugserviceID(reservation.getZugserviceID());
        super.setWagenNumber(reservation.getWagenNumber());
        super.setSitzNumber(reservation.getSitzNumber());
        super.setDeparture(reservation.getDeparture());
        super.setDestination(reservation.getDestination());
        this.message = "";
        this.qrCode = "";
    }

    public ReservationConfirmation(){};


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
