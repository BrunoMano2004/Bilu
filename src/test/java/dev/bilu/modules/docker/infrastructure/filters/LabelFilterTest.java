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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LabelFilterTest {

    @InjectMocks
    private LabelFilter labelFilter;

    @Mock
    private ListContainersCmd mockedCmd;

    @Test
    @DisplayName("Must add the label filter when the label field is filled")
    void mustAddTheLabelFilter() {
        List<String> mockedLabelList = Instancio.createList(String.class);
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::labels), mockedLabelList)
                .create();

        labelFilter.apply(mockedCmd, mockedContainerFilter);

        verify(mockedCmd, times(1)).withLabelFilter(mockedLabelList);
        verify(mockedCmd).withLabelFilter(eq(mockedLabelList));
    }

    @Test
    @DisplayName("Must not add the label filter when the label field is null")
    void mustNotAddTheLabelFilterWithNull() {
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::labels), null)
                .create();

        labelFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }

    @Test
    @DisplayName("Must not add the label filter when the label field is filled with an empty list")
    void mustNotAddTheLabelFilterWithEmptyList() {
        List<String> mockedLabelList = new ArrayList<>();
        ContainerFilter mockedContainerFilter = Instancio.of(ContainerFilter.class)
                .set(field(ContainerFilter::labels), mockedLabelList)
                .create();

        labelFilter.apply(mockedCmd, mockedContainerFilter);

        verifyNoInteractions(mockedCmd);
    }
}
