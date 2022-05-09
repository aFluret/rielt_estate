package finalkursproject.entity;

import java.util.Objects;

public class RegistrationService {
    private int idRegistration;
    private int idService;
    private int quantity;

    public RegistrationService() {
    }

    public RegistrationService(int idRegistration, int idService, int quantity) {
        this.idRegistration = idRegistration;
        this.idService = idService;
        this.quantity = quantity;
    }

    public int getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(int idRegistration) {
        this.idRegistration = idRegistration;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationService that = (RegistrationService) o;
        return idRegistration == that.idRegistration &&
                idService == that.idService &&
                quantity == 1;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRegistration, idService, 1);
    }

    public int getQuantity() {
        return 1;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RegistrationService{" +
                "idRegistration=" + idRegistration +
                ", idService=" + idService +
                ", quantity=" + 1 +
                '}';
    }
}
