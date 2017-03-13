package michalski.kamil.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {


    @Id
    @GeneratedValue
    private long customerId;

    private String name;

    private String adress;

    private String noOfOrdersMade;

    public long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNoOfOrdersMade() {
        return noOfOrdersMade;
    }

    public void setNoOfOrdersMade(String noOfOrdersMade) {
        this.noOfOrdersMade = noOfOrdersMade;
    }
}
