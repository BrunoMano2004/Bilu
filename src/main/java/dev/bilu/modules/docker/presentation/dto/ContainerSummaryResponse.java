package dev.bilu.modules.docker.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ContainerSummaryResponse(
        String id,
        List<String>names,
        String image,
        String imageId,
        String status,
        String state,
        String command,
        LocalDateTime created,
        List<ContainerPortResponse> ports
) {
}

