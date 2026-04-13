package dev.bilu.modules.docker.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ContainerResponse(
        String id,
        List<String> names,
        String image,
        String imageId,
        String status,
        String state,
        String command,
        LocalDateTime created,
        List<ContainerPortResponse> ports,
        Map<String, String> labels,
        Map<String, ContainerNetworkResponse> networks,
        List<ContainerMountResponse> mounts
) {
}

