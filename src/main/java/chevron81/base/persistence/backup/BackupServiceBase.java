package chevron81.base.persistence.backup;

import chevron81.base.util.ServerFileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class BackupServiceBase implements BackupService {

    private static final Logger LOGGER = LogManager.getLogger(BackupServiceBase.class);
    public static final String BACKUP_FILE_EXTENSION = ".sql";

    @Value("${database.backup.enabled:false}")
    protected String isBackupEnabled;

    @Value("${spring.datasource.url}")
    protected String jdbcUrl;

    @Value("${spring.datasource.username}")
    protected String user;

    @Value("${spring.datasource.password}")
    protected String password;

    @Value("${spring.datasource.platform}")
    protected String databasePlattform;

    @Value("${database.backup.pattern:yyyyMMdd_HHmmss}")
    protected String backupPattern;

    @Value("${database.backup.keep:10}")
    protected int backupKeeping;

    @Value("${database.backup.path:./data/backup}")
    protected String backupFilePath;


    @Override
    public void backupDatabase() {
        BackupServiceBase.LOGGER.info("Default backup service: No specific backup implementation provided.");
    }


    @Override
    public void cleanOldBackups() {
        final String backupDir = new File(this.backupFilePath).getParentFile().getAbsolutePath();
        final String backupPrefix = this.backupFilePath.substring(this.backupFilePath.lastIndexOf("/") + 1);
        BackupServiceBase.LOGGER.info("Scanning for old backups in: {}", backupDir);
        final List<Path> backupFiles = ServerFileUtil.readFilesFromDirectoryPrefixPostfix(backupDir, backupPrefix, BackupServiceBase.BACKUP_FILE_EXTENSION);

        if (backupFiles != null && backupFiles.size() > this.backupKeeping) {
            ServerFileUtil.sortFilesByLastModified(backupFiles);
            final List<Path> filesToDelete = backupFiles.subList(this.backupKeeping, backupFiles.size());
            ServerFileUtil.deleteFiles(filesToDelete);
        } else {
            BackupServiceBase.LOGGER.info("No old backups to delete");
        }
    }


}