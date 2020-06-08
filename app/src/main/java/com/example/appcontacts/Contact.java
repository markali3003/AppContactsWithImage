package com.example.appcontacts;

public class Contact {
   private int id ;
   private String name ;
    private int phone ;
    private byte[] image ;

    public Contact(String name, int phone, byte[] image) {
        this.name = name;
        this.phone = phone;
        this.image = image;
    }

    public Contact(int id, String name, int phone, byte[] image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    Contact(String name, int phone){
        this.name=name;
        this.phone=phone ;

    }

    public Contact(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName(){
        return name ;

    }

    public int getId() {
        return id;
    }

    public int getPhone(){
        return phone ;

    }
}
