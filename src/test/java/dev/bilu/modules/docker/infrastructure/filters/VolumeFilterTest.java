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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VolumeFilterTest {

    @InjectMocks
    private VolumeFilter volumeFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the volume filter with the volume field filled")
    void mustAddVolumeFilter() {
        List<String> mockedVolumeList = Instancio.createList(String.class);
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::volumes), mockedVolumeList)
                .create();

        volumeFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withVolumeFilter(any());
        verify(mockedCmd).withVolumeFilter(mockedVolumeList);
    }

    @Test
    @DisplayName("Must not add the volume filter when the volume field is an empty list")
    void mustAddVolumeFilterWithEmptyList() {
        List<String> mockedVolumeList = new ArrayList<>();
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::volumes), mockedVolumeList)
                .create();

        volumeFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }

    @Test
    @DisplayName("Must not add the volume filter when the volume field is null")
    void mustAddVolumeFilterWithNull() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::volumes), null)
                .create();

        volumeFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
