package dev.bilu.modules.docker.infrastructure.repository;

import com.github.dockerjava.api.DockerClient;
import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import dev.bilu.modules.docker.infrastructure.mappers.DockerContainerMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DockerRepository implements DockerPort {

    @Inject
    DockerClient docker;

    @Override
    public List<DockerContainer> listContainers() {
        return docker.listContainersCmd().exec().stream()
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
