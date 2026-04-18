package dev.bilu.modules.docker.presentation;

import dev.bilu.modules.docker.application.GetContainerByIdUseCase;
import dev.bilu.modules.docker.application.ListContainersUseCase;
import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import dev.bilu.modules.docker.presentation.dto.ContainerFilterRequest;
import dev.bilu.modules.docker.presentation.dto.ContainerResponse;
import dev.bilu.modules.docker.presentation.dto.ContainerSummaryResponse;
import dev.bilu.modules.docker.presentation.mappers.ContainerResponseMapper;
import dev.bilu.modules.docker.presentation.mappers.ContainerSummaryResponseMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/docker/container")
public class DockerContainerResource {

    private final ListContainersUseCase listContainersUseCase;

    private final GetContainerByIdUseCase getContainerByIdUseCase;

    @Inject
    public DockerContainerResource(ListContainersUseCase listContainersUseCase, GetContainerByIdUseCase getContainerByIdUseCase) {
        this.listContainersUseCase = listContainersUseCase;
        this.getContainerByIdUseCase = getContainerByIdUseCase;
    }

    @GET
    @Path("/list")
    public List<ContainerSummaryResponse> list(@Valid @BeanParam ContainerFilterRequest requestFilters) {
        ContainerFilter filters = new ContainerFilter(
                requestFilters.names,
                requestFilters.listAll,
                requestFilters.ids,
                requestFilters.volumes,
                requestFilters.networks,
                requestFilters.exitCode,
                requestFilters.status,
                requestFilters.labels
        );
        return listContainersUseCase.execute(filters).stream()
                .map(ContainerSummaryResponseMapper::from)
                .toList();
    }

    @GET
    @Path("/getById/{id}")
    public ContainerResponse getById(@PathParam("id") String id) {
        return ContainerResponseMapper.from(getContainerByIdUseCase.execute(id));
    }
}
