package ch.yaro.bls.rest.pojos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Incoming request containing departure time, departure Stop and destination Stop.
 * Is used to query for {@link TrainService}s fitting the request.*/
public class TrainServiceRequest {

    /** Time MUST be of format 'HH:mm' */
    private String time;
    private String from;
    private String to;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString(){
        return "From: " + from + " to: " + to + ", departure @" + time;
    }

    /** We can only check if the time is valid.
     * Checks on from and to are only 'not null' */
    @JsonIgnore
    public boolean isValid() {
        String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches() && from != null && to != null;
    }
}
