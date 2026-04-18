package dev.bilu.modules.docker.application;

import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import kotlin.collections.builders.ListBuilder;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListContainersUseCaseTest {

    @Mock
    private DockerPort dockerPort;

    @InjectMocks
    private ListContainersUseCase useCase;

    @Test
    @DisplayName("Must return a list of containers successfully")
    void mustReturnAListOfContainers() {
        List<DockerContainer> mockedContainers = Instancio.createList(DockerContainer.class);
        ContainerFilter filters = Instancio.create(ContainerFilter.class);
        when(dockerPort.listContainers(filters)).thenReturn(mockedContainers);

        List<DockerContainer> useCaseContainers = useCase.execute(filters);

        assertThat(useCaseContainers).isNotEmpty();
        assertThat(useCaseContainers).isEqualTo(mockedContainers);
        verify(dockerPort, times(1)).listContainers(filters);
        verify(dockerPort).listContainers(eq(filters));
    }

    @Test
    @DisplayName("Must return a empty list when no one is found")
    void mustReturnAEmptyList() {
        ContainerFilter filters = Instancio.create(ContainerFilter.class);
        when(dockerPort.listContainers(filters)).thenReturn(new ListBuilder<>());

        List<DockerContainer> useCaseContainers = useCase.execute(filters);

        assertThat(useCaseContainers).isEmpty();
        assertThat(useCaseContainers).isEqualTo(new ListBuilder<>());
        verify(dockerPort, times(1)).listContainers(filters);
        verify(dockerPort).listContainers(eq(filters));
    }
}
