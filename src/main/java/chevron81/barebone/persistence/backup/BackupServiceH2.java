package chevron81.barebone.persistence.backup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Script;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@ConditionalOnProperty(name = "spring.datasource.platform", havingValue = "h2")
@Primary
public class BackupServiceH2 extends BackupServiceBase implements BackupService {

    private static final Logger LOGGER = LogManager.getLogger(BackupServiceH2.class);


    @Override
    public void backupDatabase() {
        final boolean doBackup = Boolean.parseBoolean(this.isBackupEnabled);

        if (doBackup) {
            final SimpleDateFormat sdf = new SimpleDateFormat(this.backupPattern);
            final String backupFile = this.backupFilePath + "-" + sdf.format(new Date()) + BackupServiceBase.BACKUP_FILE_EXTENSION;
            try (final Connection conn = DriverManager.getConnection(this.jdbcUrl, this.user, this.password)) {
                Script.process(conn, backupFile, "", "");
                LOGGER.info("DATABASE BACKUP created: {}", backupFile);
            } catch (final SQLException e) {
                LOGGER.error("Failed to create database backup", e);
            }
        } else {
            LOGGER.info("DATABASE BACKUP is disabled");
        }
    }

}
