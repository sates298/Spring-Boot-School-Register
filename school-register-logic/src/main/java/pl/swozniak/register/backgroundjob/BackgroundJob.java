package pl.swozniak.register.backgroundjob;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BackgroundJob {

    private final BackgroundJobTemplate template;

    public BackgroundJob(BackgroundJobTemplate template) {
        this.template = template;
    }

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void executeTask(){
        template.executeJob();
    }
}
