package chevron81.barebone.task;

import chevron81.barebone.persistence.backup.BackupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class DatabaseBackupTask {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseBackupTask.class);

    @Autowired
    BackupService backupService;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        this.backupDatabase();
    }

    @Scheduled(cron = "${database.backup.cron}")
    public void backupDatabase() {
        LOGGER.info("Task started: Database backup");
        final long start = System.currentTimeMillis();
        this.backupService.backupDatabase();
        this.backupService.cleanOldBackups();
        final long end = System.currentTimeMillis();
        LOGGER.info("Task duration: {} ms", end - start);
        LOGGER.info("Task finished: Database backup");
    }

}