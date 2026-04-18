package dev.bilu.modules.docker.infrastructure.filters;

import com.github.dockerjava.api.command.ListContainersCmd;
import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListAllFilterTest {

    @InjectMocks
    private ListAllFilter listAllFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the list all filter when the list all field is filled")
    void mustAddTheListAllFilter() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::listAll), true)
                .create();

        listAllFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withShowAll(any());
        verify(mockedCmd).withShowAll(true);
    }

    @Test
    @DisplayName("Must not add the list all filter when the list all field is false")
    void mustNotAddTheListAllFilter() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::listAll), false)
                .create();

        listAllFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
