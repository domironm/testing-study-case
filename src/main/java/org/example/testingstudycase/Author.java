package org.example.testingstudycase;

import jakarta.persistence.Embeddable;

@Embeddable
public class Author {
    private String name;
    private String mail;

    public Author() {}

    public Author(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

