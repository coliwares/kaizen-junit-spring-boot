package com.example.app.junit.junitspringboot.models;

public class Bank {
    private Long id;
    private String name;
    private int transferQty;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTransferQty() {
        return transferQty;
    }
    public void setTransferQty(int transferQty) {
        this.transferQty = transferQty;
    }
    public Bank() {
    }
    public Bank(Long id, String name, int transferQty) {
        this.id = id;
        this.name = name;
        this.transferQty = transferQty;
    }
    @Override
    public String toString() {
        return "Bank [id=" + id + ", name=" + name + ", transferQty=" + transferQty + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + transferQty;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bank other = (Bank) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (transferQty != other.transferQty)
            return false;
        return true;
    }

    
    

}
