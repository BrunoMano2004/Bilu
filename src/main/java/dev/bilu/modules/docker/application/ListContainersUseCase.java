package dev.bilu.modules.docker.application;

import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import dev.bilu.shared.usecase.QueryUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ListContainersUseCase implements QueryUseCase<List<DockerContainer>> {

    @Inject
    DockerPort dockerPort;

    @Override
    public List<DockerContainer> execute() {
        return dockerPort.listContainers();
    }
}
