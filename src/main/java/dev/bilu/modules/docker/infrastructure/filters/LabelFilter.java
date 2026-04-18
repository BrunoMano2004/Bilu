package dev.bilu.modules.docker.infrastructure.filters;

import com.github.dockerjava.api.command.ListContainersCmd;
import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabelFilter implements ListContainerStrategy {
    @Override
    public void apply(ListContainersCmd cmd, ContainerFilter filters) {

        if (filters.labels() != null && !filters.labels().isEmpty()) {
            cmd.withLabelFilter(filters.labels());
        }
    }
}
