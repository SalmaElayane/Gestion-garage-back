package com.cigma.gg.controllers.bachoffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public record FactureImprimerDto(String fullName, String cin, String matricule, String suivi, Date dateDebut,
                                 Date dateFin,
                                 String montant, String reference, Date date) implements Serializable {

    /**
     * @param fullName
     * @param cin
     * @param matricule
     * @param suivi
     * @param dateDebut
     * @param dateFin
     * @param montant
     * @param reference
     * @param date
     */
    public FactureImprimerDto(String fullName, String cin, String matricule, String suivi, Date dateDebut,
                              Date dateFin, String montant, String reference, Date date) {
        this.fullName = fullName;
        this.cin = cin;
        this.matricule = matricule;
        this.suivi = suivi;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.reference = reference;
        this.date = date;
    }

    @Override
    public String fullName() {
        return fullName;
    }

    @Override
    public String cin() {
        return cin;
    }

    @Override
    public String suivi() {
        return suivi;
    }

    @Override
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date dateDebut() {
        return dateDebut;
    }

    @Override
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date dateFin() {
        return dateFin;
    }

    @Override
    public String montant() {
        return montant;
    }

    @Override
    public String reference() {
        return reference;
    }

    @Override
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date date() {
        return date;
    }

    @Override
    public String matricule() {
        return matricule;
    }
}
