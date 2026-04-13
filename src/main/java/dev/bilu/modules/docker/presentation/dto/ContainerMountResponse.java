package dev.bilu.modules.docker.presentation.dto;

public record ContainerMountResponse(
        String name,
        String source,
        String destination,
        String driver,
        String mode,
        Boolean rw,
        String propagation
) {
}
