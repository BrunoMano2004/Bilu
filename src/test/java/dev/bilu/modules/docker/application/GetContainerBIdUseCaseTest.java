package dev.bilu.modules.docker.application;

import dev.bilu.modules.docker.domain.entities.DockerContainer;
import dev.bilu.modules.docker.domain.port.DockerPort;
import jakarta.ws.rs.NotFoundException;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Select.field;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetContainerBIdUseCaseTest {

    @Mock
    private DockerPort dockerPort;

    @InjectMocks
    private GetContainerByIdUseCase useCase;

    @Test
    @DisplayName("Must return a container with an existing id")
    void mustReturnAContainerWithExistingId() {
        //ARRANGE
        String id = Instancio.create(String.class);
        DockerContainer container = Instancio.of(DockerContainer.class)
                .set(field(DockerContainer::getId), id)
                .create();

        when(dockerPort.getContainerById(id)).thenReturn(Optional.of(container));

        //ACT
        DockerContainer dockerContainer = useCase.execute(id);

        //ASSERTION
        assertThat(dockerContainer).isNotNull();
        assertThat(dockerContainer.getId()).isEqualTo(id);
        verify(dockerPort, times(1)).getContainerById(id);
        verify(dockerPort).getContainerById(eq(id));
    }

    @Test
    @DisplayName("Must throw in a NotFoundException when the id of the container does not exists")
    void mustThrowAExceptionWhenTheContainerIdDoesNotExists() {
        //ARRANGE
        String id = Instancio.create(String.class);

        when(dockerPort.getContainerById(id)).thenReturn(Optional.empty());

        //ACT + ASSERT
        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Container not found with id: " + id);
        verify(dockerPort, times(1)).getContainerById(id);
        verify(dockerPort).getContainerById(eq(id));
    }

    @Test
    @DisplayName("Must call the port with the id provided")
    void mustCallThePortWithTheIdProvided() {
        //ARRANGER
        String id = Instancio.create(String.class);
        DockerContainer dockerContainer = Instancio.of(DockerContainer.class)
                .set(field(DockerContainer::getId), id)
                .create();

        when(dockerPort.getContainerById(id)).thenReturn(Optional.of(dockerContainer));

        //ACT
        useCase.execute(id);

        //ASSERTION
        verify(dockerPort, times(1)).getContainerById(id);
        verify(dockerPort).getContainerById(eq(id));
        verifyNoMoreInteractions(dockerPort);
    }
}
