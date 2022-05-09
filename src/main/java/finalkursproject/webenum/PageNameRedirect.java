package finalkursproject.webenum;

public enum PageNameRedirect {
    INDEX("/rielt-torg.by/index"),
    ERROR("/rielt-torg.by/error"),
    FIND_BY_TYPE("/rielt-torg.by/find_by_type"),
    REGISTRATIONS_CLIENT("/rielt-torg.by/show_registration_client"),
    REGISTRATIONS_ADMIN("/rielt-torg.by/show_registration_admin"),
    CLIENTS("/rielt-torg.by/show_client"),
    ADMINS("/rielt-torg.by/show_admin"),
    PROFILE("/rielt-torg.by/client_profile"),
    REVIEWS("/rielt-torg.by/show_reviews");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
