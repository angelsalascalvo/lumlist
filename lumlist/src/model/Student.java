/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author rutil
 */
public class Student {
    private int id ;
    private String name;
    private String surname;
    private String username;
    private String passwd;
    private Date birthDate;
    private boolean available;
    private int phone;
    private String email;
    private String observations;
    private String linkedin;
    private String github;

    /*
     * CONSTRUCTOR PARAMETRIZADO
     */
    public Student(int id, String name, String surname, String username, String passwd, Date birthDate,
            boolean available, int phone, String email, String observations, String linkedin, String github) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.passwd = passwd;
        this.birthDate = birthDate;
        this.available = available;
        this.phone = phone;
        this.email = email;
        this.observations = observations;
        this.linkedin = linkedin;
        this.github = github;
    }

    /**
     * CONSTRUCTOR POR DEFECTO
     */
    public Student() {
    	this.id=-1;
	}

	/*
    * METODOS GET Y SET
    */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}