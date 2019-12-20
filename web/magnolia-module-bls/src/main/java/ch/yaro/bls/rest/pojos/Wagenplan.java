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


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a simple POJO representation for a "Wagenplan" item stored in the "wagenplaene" app
 * in the 'category' workspace.<br/>
 */
public class Wagenplan extends NodeItem {

    public static final String WORKSPACE = "category";
    public static final String NODETYPE = "wagenplan";
    public static final String BASEPATH = "/wagenplaene";

    public static final String CODE = "code";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String WAGENTYP = "wagentyp";

    private String code;
    private String description;
    private String imageLink;
    @JsonIgnore
    private List<String> wagentypIDs;
    private List<String> wagentypen;
    private List<Seat> seats = new ArrayList<>();

    public List<String> getWagentypIDs() {
        return wagentypIDs;
    }

    public void setWagentypIDs(List<String> wagentypIDs) {
        this.wagentypIDs = wagentypIDs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<String> getWagentypen() {
        return wagentypen;
    }

    public void setWagentypen(List<String> wagentypen) {
        this.wagentypen = wagentypen;
    }

    public Seat getSeatByNumber(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getId().equals(seatNumber)) return seat;
        }
        return null;
    }
}
