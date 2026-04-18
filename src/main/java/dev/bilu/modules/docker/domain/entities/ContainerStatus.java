package dev.bilu.modules.docker.domain.entities;

public enum ContainerStatus {
    CREATED("created"),
    RESTARTING("restarting"),
    RUNNING("running"),
    PAUSED("paused"),
    EXITED("exited");

    final String value;

    ContainerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
