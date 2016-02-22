package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surfing on 2/17/2016.
 */
public class Address {
    private String id;
    private String addressId;
    private String street;
    private String box;
    private String postCode;
    private String city;
    private String country;
    private List<Client> clients;

    public Address() {
        clients = new ArrayList<>();
    }

    public Address(String id, String addressId, String street, String box, String postCode, String city, String country) {
        this();
        this.id = id;
        this.addressId = addressId;
        this.street = street;
        this.box = box;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public List<Client> getClients(){
        return clients;
    }

    public void removeClient(Client client){
        clients.remove(client);
    }

    @Override
    public String toString() {
        return street + ", " + box + ", " + postCode + ", " + city + ", " + country;
    }
}
