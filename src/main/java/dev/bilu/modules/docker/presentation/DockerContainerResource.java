package dev.bilu.modules.docker.presentation;

import dev.bilu.modules.docker.application.GetContainerByIdUseCase;
import dev.bilu.modules.docker.application.ListContainersUseCase;
import dev.bilu.modules.docker.presentation.dto.ContainerResponse;
import dev.bilu.modules.docker.presentation.dto.ContainerSummaryResponse;
import dev.bilu.modules.docker.presentation.mappers.ContainerResponseMapper;
import dev.bilu.modules.docker.presentation.mappers.ContainerSummaryResponseMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/docker/container")
public class DockerContainerResource {

    @Inject
    ListContainersUseCase listContainersUseCase;

    @Inject
    GetContainerByIdUseCase getContainerByIdUseCase;

    @GET
    @Path("/list")
    public List<ContainerSummaryResponse> list() {
        return listContainersUseCase.execute().stream()
                .map(ContainerSummaryResponseMapper::from)
                .toList();
    }

    @GET
    @Path("/getById/{id}")
    public ContainerResponse getById(@PathParam("id") String id) {
        return ContainerResponseMapper.from(getContainerByIdUseCase.execute(id));
    }
}
