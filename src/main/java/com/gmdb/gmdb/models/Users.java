package com.gmdb.gmdb.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    private String userName;
    private String joiningDate;

    public Users(){}

    public Users(String userName, String joiningDate){
        this.userName = userName;
        this.joiningDate = joiningDate;
    }
}
