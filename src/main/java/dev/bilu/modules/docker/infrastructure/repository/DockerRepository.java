package dev.bilu.modules.docker.infrastructure.repository;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListContainersCmd;
import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import dev.bilu.modules.docker.infrastructure.filters.ListContainerStrategy;
import dev.bilu.modules.docker.infrastructure.mappers.DockerContainerMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DockerRepository implements DockerPort {

    private final DockerClient docker;
    private final Instance<ListContainerStrategy> filterStrategies;

    @Inject
    public DockerRepository(DockerClient docker, Instance<ListContainerStrategy> filterStrategies) {
        this.docker = docker;
        this.filterStrategies = filterStrategies;
    }

    @Override
    public List<DockerContainer> listContainers(ContainerFilter filters) {
        ListContainersCmd cmd = docker.listContainersCmd();
        filterStrategies.forEach(filter -> filter.apply(cmd, filters));

        return cmd.exec()
                .stream()
                .map(DockerContainerMapper::from)
                .toList();
    }

    @Override
    public Optional<DockerContainer> getContainerById(String id) {
        Collection<String> idFilter = new ArrayList<>();
        idFilter.add(id);
        return docker.listContainersCmd().withIdFilter(idFilter).exec()
                .stream()
                .map(DockerContainerMapper::from)
                .findFirst();
    }
}
