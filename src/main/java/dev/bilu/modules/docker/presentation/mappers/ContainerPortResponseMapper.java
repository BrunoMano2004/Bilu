package dev.bilu.modules.docker.presentation.mappers;

import dev.bilu.modules.docker.domain.entities.DockerContainerPort;
import dev.bilu.modules.docker.presentation.dto.ContainerPortResponse;

public class ContainerPortResponseMapper {

    public static ContainerPortResponse from(DockerContainerPort dockerContainerPort) {
        return new ContainerPortResponse(
                dockerContainerPort.getPrivatePort(),
                dockerContainerPort.getPublicPort(),
                dockerContainerPort.getType()
        );
    }
}
