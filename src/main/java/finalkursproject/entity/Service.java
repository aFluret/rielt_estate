package finalkursproject.entity;

import java.util.Objects;

public class Service {
    private int idService;
    private String nameRu;
    private double cost;
    private String type;

    private String imagePath;
    private boolean exist;

    public Service() {
    }

    public Service(int idService, String nameRu, double cost, String type,  String imagePath, boolean exist) {
        this.idService = idService;
        this.nameRu = nameRu;
        this.cost = cost;
        this.type = type;

        this.imagePath = imagePath;
        this.exist = exist;
    }

    public Service( String nameRu, double cost, String type,  String imagePath, boolean exist) {
        this.nameRu = nameRu;
        this.cost = cost;
        this.type = type;

        this.imagePath = imagePath;
        this.exist = exist;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   /* public double getWeight() {
        return weight;
    }*/

 /*   public void setWeight(double weight) {
        this.weight = weight;
    }
*/
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return idService == service.idService &&
                Double.compare(service.cost, cost) == 0 &&
            //    Double.compare(service.weight, weight) == 0 &&
                exist == service.exist &&
                Objects.equals(nameRu, service.nameRu) &&
                Objects.equals(type, service.type) &&
                Objects.equals(imagePath, service.imagePath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idService,  nameRu, cost, type,  imagePath, exist);
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", nameRu='" + nameRu + '\'' +
                ", cost=" + cost +
                ", type='" + type + '\'' +
              //  ", weight=" + weight +
                ", imagePath='" + imagePath + '\'' +
                ", exist=" + exist +
                '}';
    }
}
