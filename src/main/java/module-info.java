module com.bichotitas.votacionesmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.bichotitas.votacionesmvc to javafx.fxml;
    exports com.bichotitas.votacionesmvc;
    exports com.bichotitas.votacionesmvc.views;
    opens com.bichotitas.votacionesmvc.views to javafx.fxml;
}