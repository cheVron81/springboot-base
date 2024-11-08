package chevron81.barebone.persistence.backup;

import chevron81.barebone.util.ServerFileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

@Service
public class DefaultBackupService implements BackupService {

    private static final Logger LOGGER = LogManager.getLogger(DefaultBackupService.class);
    public static final String BACKUP_FILE_EXTENSION = ".sql";

    @Value("${database.backup.enabled}")
    @SuppressWarnings("unused")
    protected String isBackupEnabled;

    @Value("${spring.datasource.url}")
    @SuppressWarnings("unused")
    protected String jdbcUrl;

    @Value("${spring.datasource.username}")
    @SuppressWarnings("unused")
    protected String user;

    @Value("${spring.datasource.password}")
    @SuppressWarnings("unused")
    protected String password;

    @Value("${spring.datasource.platform}")
    @SuppressWarnings("unused")
    protected String databasePlattform;

    @Value("${database.backup.pattern}")
    @SuppressWarnings("unused")
    protected String backupPattern;

    @Value("${database.backup.keep}")
    @SuppressWarnings("unused")
    protected Integer backupKeeping;

    @Value("${database.backup.path}")
    @SuppressWarnings("unused")
    protected String backupFilePath;


    @Override
    public void backupDatabase() {
        LOGGER.info("Default backup service: No specific backup implementation provided.");
    }


    @Override
    public void cleanOldBackups() {
        final File backupDir = new File(this.backupFilePath).getParentFile();
        final String backupPrefix = this.backupFilePath.substring(this.backupFilePath.lastIndexOf("/") + 1);
        LOGGER.info("Scanning for old backups in: {}", backupDir.getAbsolutePath());
        final List<Path> backupFiles = ServerFileUtil.readFilesFromDirectoryPrefixPostfix(backupDir.getAbsolutePath(), backupPrefix, DefaultBackupService.BACKUP_FILE_EXTENSION);
        if (backupFiles != null && backupFiles.size() > this.backupKeeping) {
            backupFiles.sort(Comparator.comparingLong(path -> {
                try {
                    return Files.getLastModifiedTime(path).toMillis();
                } catch (final IOException e) {
                    throw new RuntimeException("Failed to get last modified time for path: " + path, e);
                }
            }));
            for (int i = this.backupKeeping; i < backupFiles.size(); i++) {
                final boolean isDeleted = backupFiles.get(i).toFile().delete();
                if (isDeleted) {
                    LOGGER.info("Deleted old backup: {}", backupFiles.get(i));
                } else {
                    LOGGER.error("Failed to delete old backup: {}", backupFiles.get(i));
                }
            }
        } else {
            LOGGER.info("No old backups to delete");
        }
    }
}