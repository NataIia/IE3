package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surfing on 2/17/2016.
 */
public class Client {
    private String id;
    private String clientId;
    private String name;
    private String firstName;
    private List<Address> addresses;

    public Client() {
        addresses = new ArrayList<>();
    }

    public Client(String id, String clientId, String name, String firstName) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
    }
}
