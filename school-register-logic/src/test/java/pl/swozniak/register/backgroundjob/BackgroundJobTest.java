package pl.swozniak.register.backgroundjob;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BackgroundJobTest {

    @Mock
    BackgroundJobTemplate template;

    @InjectMocks
    BackgroundJob job;

    @Test
    void executeTask() {
        job.executeTask();
        verify(template).executeJob();
    }
}