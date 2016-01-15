package com.wnm.wellnewme.information;

/**
 * Created by tayo on 11/10/2015.
 */
public class databaseInformation {

    public databaseInformation(){

    }
    public String date_of_birth,gender,done1,percent;

    public String done2;
    public String done3;
    public String done4;
    public String done5;

    public String diet1,diet2,diet3,diet4;

    public String drug1,drug2,drug3,drug4,drug5;

    public String family1, family2, family3;

    public String medical1,medical2,medical3,medical4;

    public databaseInformation(String date, String gender, String done1, String percent,
                               String diet1,String diet2,String diet3,String diet4,
                               String done_2,
                               String drug1,String drug2,String drug3,String drug4, String drug5, String done_3,
                               String family1,String family2,String family3,String done_4,
                               String medical1,String medical2,String medical3,String medical4,String done_5){
        this.date_of_birth = date;
        this.gender = gender;
        this.done1 = done1;
        this.percent = percent;
        this.done2 = done_2;

        this.diet1 = diet1;
        this.diet2 = diet2;
        this.diet3 = diet3;
        this.diet4 = diet4;

        this.drug1 = drug1;
        this.drug2 = drug2;
        this.drug3 = drug3;
        this.drug4 = drug4;
        this.drug5 = drug5;
        this.done3 = done_3;

        this.family1 = family1;
        this.family2 = family2;
        this.family3 = family3;
        this.done4 = done_4;

        this.medical1 = medical1;
        this.medical2 = medical2;
        this.medical3 = medical3;
        this.medical4 = medical4;
        this.done5 = done_5;
    }

    public databaseInformation(String diet1,String diet2,String diet3,String diet4,String done2){
        this.done2 = done2;
        this.diet1 = diet1;
        this.diet2 = diet2;
        this.diet3 = diet3;
        this.diet4 = diet4;
    }

    public databaseInformation(String drug1,String drug2,String drug3,String drug4, String drug5, String done3){
        this.drug1 = drug1;
        this.drug2 = drug2;
        this.drug3 = drug3;
        this.drug4 = drug4;
        this.drug5 = drug5;
        this.done3 = done3;
    }
}
