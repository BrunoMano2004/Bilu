package dev.bilu.modules.docker.application;

import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import dev.bilu.shared.usecase.UseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GetContainerByIdUseCase implements UseCase<String, DockerContainer> {

    @Inject
    DockerPort dockerPort;

    @Override
    public DockerContainer execute(String id) {
        return dockerPort.getContainerById(id).orElseThrow(() -> new NotFoundException("Container not found with id: " + id));
    }
}
