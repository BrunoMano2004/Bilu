package dev.bilu.modules.docker.application;

import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import dev.bilu.shared.usecase.UseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ListContainersUseCase implements UseCase<ContainerFilter, List<DockerContainer>> {

    private final DockerPort dockerPort;

    @Inject
    public ListContainersUseCase(DockerPort dockerPort) {
        this.dockerPort = dockerPort;
    }

    @Override
    public List<DockerContainer> execute(ContainerFilter filters) {
        return dockerPort.listContainers(filters);
    }
}
