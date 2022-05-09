package finalkursproject.entity;

import java.util.Objects;

public class Registration {
    private int idRegistration;
    private String date;
    private double totalCost;
    private int idClient;
    private boolean status;

    public Registration() {
    }

    public Registration(String date, double totalCost, int idClient, boolean status) {
        this.date = date;
        this.totalCost = totalCost;
        this.idClient = idClient;
        this.status = status;
    }

    public Registration(int idRegistration, String date, double totalCost, int idClient, boolean status) {
        this.idRegistration = idRegistration;
        this.date = date;
        this.totalCost = totalCost;
        this.idClient = idClient;
        this.status = status;
    }

    public int getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(int idRegistration) {
        this.idRegistration = idRegistration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration registration = (Registration) o;
        return idRegistration == registration.idRegistration &&
                Double.compare(registration.totalCost, totalCost) == 0 &&
                idClient == registration.idClient &&
                status == registration.status &&
                Objects.equals(date, registration.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRegistration, date, totalCost, idClient, status);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "idRegistration=" + idRegistration +
                ", date='" + date + '\'' +
                ", totalCost=" + totalCost +
                ", idClient=" + idClient +
                ", status=" + status +
                '}';
    }
}
