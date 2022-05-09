package finalkursproject.command;

public enum CommandName {
    WRONG_REQUEST("all"),
    SIGN_IN("all"),
    SIGN_UP("all"),
    INDEX("all"),
    CHANGE_LOCALE("all"),
    FIND_BY_TYPE("all"),
    SIGN_OUT("all"),
    ADD_SERVICE_BASKET("client"),
    REMOVE_SERVICE_BASKET("client"),
    SHOW_REGISTRATION_CLIENT("client"),
    PAY_FOR_REGISTRATION("client"),
    CLIENT_PROFILE("client"),
    DELETE_SERVICE("admin"),
    ADD_SERVICE_ACTION("admin"),
    SHOW_CLIENT("admin"),
    BAN_CLIENT("admin"),
    SHOW_ADMIN("admin"),
    DELETE_ADMIN("admin"),
    ADD_ADMIN("admin"),
    SHOW_REGISTRATION_ADMIN("admin"),
    EDIT_PROFILE("client"),
    STATISTIC("admin"),
    CHANGE_PASSWORD("all"),
    ADD_ACCOUNT("client"),
    DELETE_ACCOUNT("client"),
    EDIT_SERVICE("admin"),
    CLOSE_REGISTRATION("admin"),
    ADD_REVIEW("client"),
    SHOW_REVIEWS("all");

    private String role;

    CommandName(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
