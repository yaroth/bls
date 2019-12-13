package ch.yaro.bls.rest.pojos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.jcr.RepositoryException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
public class TrainService extends NodeItem {

    public static final String WORKSPACE = "zugservices";
    public static final String NODETYPE = "zugservice";
    public static final String BASEPATH = "/";

    public static final String NAME = "name";
    public static final String DEPARTURE = "departure";
    public static final String STRECKE_ID = "strecke";
    public static final String ZUGKOMPOSITION_ID = "zugkomposition";


    private String name;
    private String departure;
    private String arrival;
    private String from;
    private String to;
    private boolean isNextDay = false;
    @JsonIgnore
    private String streckeID;
    private LinkedList<Stop> timetable;
    @JsonIgnore
    private String zugkompositionID;
    private LinkedList<Wagen> zugkomposition;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getStreckeID() {
        return streckeID;
    }

    public void setStreckeID(String streckeID) {
        this.streckeID = streckeID;
    }

    public String getZugkompositionID() {
        return zugkompositionID;
    }

    public void setZugkompositionID(String zugkompositionID) {
        this.zugkompositionID = zugkompositionID;
    }

    public LinkedList<Stop> getTimetable() {
        return timetable;
    }

    public void setTimetable(LinkedList<Stop> timetable) {
        this.timetable = timetable;
    }

    public LinkedList<Wagen> getZugkomposition() {
        return zugkomposition;
    }

    public void setZugkomposition(LinkedList<Wagen> zugkomposition) {
        this.zugkomposition = zugkomposition;
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

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    /** Checks for each TrainService if it fits the request, i.e. Departure and Destination are within timetable
     * and departure time @Departure station is AFTER request departure time.
     * Comparisons are done on lower case strings! */
    public boolean fitsRequest(TrainServiceRequest request) {
        LocalTime earliestDeparture = LocalTime.parse(request.getTime(), DateTimeFormatter.ofPattern("HH:mm"));
        String departureStopLower = request.getFrom().toLowerCase();
        String destinationStopLower = request.getTo().toLowerCase();
        boolean startStopFitsDeparture = false;
        for (Iterator stopIterator = timetable.iterator(); stopIterator.hasNext(); ) {
            Stop stop = (Stop) stopIterator.next();
            String currentStopLower = stop.getStopName().toLowerCase();
            if (!startStopFitsDeparture && currentStopLower.equals(departureStopLower)) {
                startStopFitsDeparture = true;
            }
            if (startStopFitsDeparture && currentStopLower.equals(destinationStopLower)) {
                return true;
            }
        }
        return false;
    }

    /** Returns the timetable starting at request departure Stop and ending at
     * request destination Stop. */
    public void adaptTimetableToRequest(TrainServiceRequest request) throws RepositoryException {
        String startStopLower = request.getFrom().toLowerCase();
        String endStopLower = request.getTo().toLowerCase();
        LinkedList<Stop> updatedTimetable = new LinkedList<>();
        boolean startAddingStops = false;
        for (Iterator stopIterator = timetable.iterator(); stopIterator.hasNext(); ) {
            Stop stop = (Stop) stopIterator.next();
            String stopLower = stop.getStopName().toLowerCase();
            if (!startAddingStops && stopLower.equals(startStopLower)) {
                stop.setTimeIN(null);
                startAddingStops = true;
                setFrom(stop.getStopName());
                int minutes = stop.getTimeOut().getMinute();
                String minutesPlaceholder = "";
                if (minutes < 10) minutesPlaceholder = "0";
                setDeparture(stop.getTimeOut().getHour() + ":" + minutesPlaceholder + minutes);
            }
            if (startAddingStops){
                updatedTimetable.add(stop);
            }
            if (stopLower.equals(endStopLower)) {
                stop.setTimeOut(null);
                setTo(stop.getStopName());
                int minutes = stop.getTimeIN().getMinute();
                String minutesPlaceholder = "";
                if (minutes < 10) minutesPlaceholder = "0";
                setArrival(stop.getTimeIN().getHour() + ":" + minutesPlaceholder + minutes);
                break;
            }
        }
        timetable = updatedTimetable;
    }

    public boolean isNextDay() {
        return isNextDay;
    }

    public void setNextDay(boolean nextDay) {
        isNextDay = nextDay;
    }
}
