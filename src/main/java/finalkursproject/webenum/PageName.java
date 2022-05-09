package finalkursproject.webenum;

public enum PageName {
    INDEX("/front/jsp/index.jsp"),
    ERROR("/front/jsp/error.jsp"),
    SERVICES("/front/jsp/common/service.jsp"),
    REGISTRATIONS("/front/jsp/common/registration.jsp"),
    STATISTICS("/front/jsp/admin/statistic.jsp"),
    PROFILE("/front/jsp/client/profile.jsp"),
    CLIENTS("/front/jsp/admin/clientlist.jsp"),
    ADMINS("/front/jsp/admin/adminlist.jsp"),
    REVIEWS("/front/jsp/common/reviews.jsp");


    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
