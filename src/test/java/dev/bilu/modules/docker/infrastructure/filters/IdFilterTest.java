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
public class IdFilterTest {

    @InjectMocks
    private IdFilter idFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the id filter if the field is filled")
    void mustAddIdFilter() {
        List<String> mockedIdList = Instancio.createList(String.class);
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::ids), mockedIdList)
                .create();

        idFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withIdFilter(any());
        verify(mockedCmd).withIdFilter(eq(mockedIdList));
    }

    @Test
    @DisplayName("Must not add the id filter if the field is a empty list")
    void mustNotAddIdFilterWithEmptyList() {
        List<String> mockedIdList = new ArrayList<>();
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::ids), mockedIdList)
                .create();

        idFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }

    @Test
    @DisplayName("Must not add the id filter if the field is not filled")
    void mustNotAddIdFilterWithNullField() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::ids), null)
                .create();

        idFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
