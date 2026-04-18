package dev.bilu.modules.docker.infrastructure.filters;

import com.github.dockerjava.api.command.ListContainersCmd;
import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import dev.bilu.modules.docker.domain.entities.ContainerStatus;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatusFilter implements ListContainerStrategy {
    @Override
    public void apply(ListContainersCmd cmd, ContainerFilter filters) {

        if (filters.status() != null && !filters.status().isEmpty()) {
            cmd.withStatusFilter(
                    filters.status().stream()
                            .map(ContainerStatus::getValue)
                            .toList()
            );
        }
    }
}
