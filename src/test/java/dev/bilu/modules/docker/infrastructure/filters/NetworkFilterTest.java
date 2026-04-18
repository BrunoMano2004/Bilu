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

import java.util.ArrayList;
import java.util.List;

import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NetworkFilterTest {

    @InjectMocks
    private NetworkFilter networkFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the network filter when the network field is filled")
    void mustAddNetworkFilter() {
        List<String> mockedNetworkList = Instancio.createList(String.class);
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::networks), mockedNetworkList)
                .create();

        networkFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withNetworkFilter(any());
        verify(mockedCmd).withNetworkFilter(eq(mockedNetworkList));
    }

    @Test
    @DisplayName("Must not add the network filter when the network field is an empty list")
    void mustNotAddNetworkFilterWithEmptyList() {
        List<String> mockedNetworkList = new ArrayList<>();
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::networks), mockedNetworkList)
                .create();

        networkFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }

    @Test
    @DisplayName("Must not add the network filter when the network field is null")
    void mustNotAddNetworkFilterWithNull() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::networks), null)
                .create();

        networkFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
