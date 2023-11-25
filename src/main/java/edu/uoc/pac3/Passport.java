package edu.uoc.pac3;

import java.time.LocalDate;
import java.time.Period;

public class Passport {
    public static final String PASSPORT_NUMBER_ERROR = "Passport number cannot be null or empty.";
    public static final String ISSUE_DATE_ERROR = "Issue date cannot be null or before the current date.";
    public static final String EXPIRATION_DATE_ERROR = "Expiration date cannot be null or before the issue date, and must be within 10 years of the issue date.";
    public static final String VISA_TYPE_ERROR = "Visa type must be a correct value.";

    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private VisaType visaType;

    public Passport(String passportNumber, LocalDate issueDate, LocalDate expirationDate, VisaType visaType) {
        setPassportNumber(passportNumber);
        setIssueDate(issueDate);
        setExpirationDate(expirationDate);
        setVisaType(visaType);
    }

    // methods
    public String getPassportNumber() {
        return passportNumber;
    }

    private void setPassportNumber(String passportNumber) throws IllegalArgumentException {
        if (passportNumber == null || passportNumber.isEmpty() || passportNumber.startsWith(" ")) {
            throw new IllegalArgumentException(PASSPORT_NUMBER_ERROR);
        }

        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    private void setIssueDate(LocalDate issueDate) throws IllegalArgumentException {
        if (issueDate == null) {
            throw new IllegalArgumentException(ISSUE_DATE_ERROR);
        }

        LocalDate now = LocalDate.now();

        Period periode = Period.between(issueDate, now);
        int anys = periode.getYears();
        if (anys >= 10 || issueDate.isAfter(now)) {
            throw new IllegalArgumentException(ISSUE_DATE_ERROR);
        }
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    private void setExpirationDate(LocalDate expirationDate) throws IllegalArgumentException {
        if (expirationDate == null) {
            throw new IllegalArgumentException(EXPIRATION_DATE_ERROR);
        }

        Period period = Period.between(issueDate, expirationDate);
        int anys = period.getYears();

        if (anys > 10 || expirationDate.isBefore(issueDate)) {
            throw new IllegalArgumentException(EXPIRATION_DATE_ERROR);
        }

        this.expirationDate = expirationDate;
    }

    public VisaType getVisaType() {
        return visaType;
    }

    private void setVisaType(VisaType visaType) throws IllegalArgumentException {
        if (visaType == null) {
            throw new IllegalArgumentException(VISA_TYPE_ERROR);
        }

        this.visaType = visaType;
    }
}