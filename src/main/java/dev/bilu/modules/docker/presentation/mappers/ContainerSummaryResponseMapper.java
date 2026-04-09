package dev.bilu.modules.docker.presentation.mappers;

import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.presentation.dto.ContainerSummaryResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ContainerSummaryResponseMapper {

    public static ContainerSummaryResponse from(DockerContainer dockerContainer) {
        return new ContainerSummaryResponse(
                dockerContainer.getId(),
                dockerContainer.getNames(),
                dockerContainer.getImage(),
                dockerContainer.getImageId(),
                dockerContainer.getStatus(),
                dockerContainer.getState(),
                dockerContainer.getCommand(),
                LocalDateTime.ofInstant(Instant.ofEpochSecond(dockerContainer.getCreated()), ZoneId.systemDefault()),
                dockerContainer.getPorts().stream().map(ContainerPortResponseMapper::from).toList()
        );
    }
}
