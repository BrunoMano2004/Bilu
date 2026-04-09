package dev.bilu.modules.docker.presentation.dto;

public record ContainerPortResponse(
        Integer privatePort,
        Integer publicPort,
        String type
) {
}
