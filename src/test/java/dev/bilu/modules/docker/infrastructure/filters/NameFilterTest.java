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
public class NameFilterTest {

    @InjectMocks
    private NameFilter nameFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the name filter when the name field is filled")
    void mustAddTheNameFilter() {
        List<String> mockedNameList = Instancio.createList(String.class);
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::names), mockedNameList)
                .create();

        nameFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withNameFilter(any());
        verify(mockedCmd).withNameFilter(eq(mockedNameList));
    }

    @Test
    @DisplayName("Must not add the name filter when the name field is an empty list")
    void mustNotAddTheNameFilterWithEmptyList() {
        List<String> mockedNameList = new ArrayList<>();
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::names), mockedNameList)
                .create();

        nameFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }

    @Test
    @DisplayName("Must not add the name filter when the name field is null")
    void mustNotAddTheNameFilterWithNull() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::names), null)
                .create();

        nameFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
