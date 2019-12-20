package ch.yaro.bls.rest.pojos;


import java.util.LinkedList;

/**
 * This class is a simple POJO representation for a "Zugkomposition" item stored in the "zugkompositionen"
 * app in the 'zugkomposition' repository.<br/>
 * Mainly contains a list (LinkedList!) of {@link Wagen}.
 */
public class Zugkomposition {

    public static final String WORKSPACE = "zugkompositionen";
    public static final String NODETYPE = "zugkomposition";
    public static final String BASEPATH = "/";

    public static final String NAME = "name";
    public static final String WAEGEN = "waegen";

    private String name;
    private LinkedList<Wagen> wagenList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Wagen> getWagenList() {
        return wagenList;
    }

    public void setWagenList(LinkedList<Wagen> wagenList) {
        this.wagenList = wagenList;
    }
}
