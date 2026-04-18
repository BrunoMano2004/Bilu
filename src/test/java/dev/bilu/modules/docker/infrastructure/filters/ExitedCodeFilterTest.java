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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExitedCodeFilterTest {

    @InjectMocks
    private ExitedCodeFilter exitedCodeFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the exited code filter if the field is filled")
    void mustAddTheExitedCodeFilter() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::exitedCode), 0)
                .create();

        exitedCodeFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withExitedFilter(any());
        verify(mockedCmd).withExitedFilter(eq(0));
    }

    @Test
    @DisplayName("Must not add the exited code filter if the field is not filled")
    void mustSkipTheFilter() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::exitedCode), null)
                .create();

        exitedCodeFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
