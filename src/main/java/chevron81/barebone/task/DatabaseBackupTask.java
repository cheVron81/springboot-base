package chevron81.barebone.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Script;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

@Component
@SuppressWarnings("unused")
public class DatabaseBackupTask {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseBackupTask.class);

    @Value("${database.backup.enabled}")
    @SuppressWarnings("unused")
    private String isBackupEnabled;

    @Value("${spring.datasource.url}")
    @SuppressWarnings("unused")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    @SuppressWarnings("unused")
    private String user;

    @Value("${spring.datasource.password}")
    @SuppressWarnings("unused")
    private String password;

    @Value("${database.backup.path}")
    @SuppressWarnings("unused")
    private String backupFilePath;
    private final String BACKUP_FILE_EXTENSION = ".sql";

    @Scheduled(cron = "${database.backup.cron}")
    public void backupDatabase() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        final String backupFile = this.backupFilePath + "-" + sdf.format(new Date()) + this.BACKUP_FILE_EXTENSION;
        try (final Connection conn = DriverManager.getConnection(this.jdbcUrl, this.user, this.password)) {
            Script.process(conn, backupFile, "", "");
            System.out.println("Backup created: " + backupFile);
            this.cleanOldBackups();
        } catch (final SQLException e) {
            LOGGER.error("Failed to create backup", e);
        }
    }

    private void cleanOldBackups() {
        final File backupDir = new File(this.backupFilePath).getParentFile();
        final File[] backupFiles = backupDir.listFiles((dir, name) -> name.startsWith(backupDir.getName()) && name.endsWith(this.BACKUP_FILE_EXTENSION));

        if (backupFiles != null && backupFiles.length > 5) {
            Arrays.sort(backupFiles, Comparator.comparingLong(File::lastModified).reversed());
            for (int i = 5; i < backupFiles.length; i++) {
                if (!backupFiles[i].delete()) {
                    System.err.println("Failed to delete old backup: " + backupFiles[i].getName());
                }
            }
        }
    }
}