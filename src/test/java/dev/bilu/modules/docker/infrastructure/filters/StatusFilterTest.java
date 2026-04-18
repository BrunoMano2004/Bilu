package dev.bilu.modules.docker.infrastructure.filters;

import com.github.dockerjava.api.command.ListContainersCmd;
import dev.bilu.modules.docker.domain.entities.ContainerFilter;
import dev.bilu.modules.docker.domain.entities.ContainerStatus;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatusFilterTest {

    @InjectMocks
    private StatusFilter statusFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the status filter when the status filter is filled")
    void mustAddStatusFilter() {
        List<ContainerStatus> mockedContainerStatusList = Instancio.createList(ContainerStatus.class);
        List<String> mockedContainerStatusStringList = mockedContainerStatusList.stream()
                .map(ContainerStatus::getValue)
                .toList();
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::status), mockedContainerStatusList)
                .create();

        statusFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withStatusFilter(any());
        verify(mockedCmd).withStatusFilter(eq(mockedContainerStatusStringList));
    }

    @Test
    @DisplayName("Must not add the status filter when the status field is null")
    void mustNotAddStatusFilterWithNull() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::status), null)
                .create();

        statusFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
