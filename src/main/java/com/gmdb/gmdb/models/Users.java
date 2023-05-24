package com.gmdb.gmdb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    private String userName;
    private String joiningDate;

    
    public Users(long id,String userName, String joiningDate){
        this.id = id;
        this.userName = userName;
        this.joiningDate = joiningDate;
    }
    public Users(){}
}
