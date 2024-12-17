package chevron81.base.database;

import chevron81.base.persistence.backup.BackupServiceMySql;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatabaseBackupTests {

    @Autowired
    BackupServiceMySql backupServiceMySql;

    @Test
    void testDatabaseBackup() {
        this.backupServiceMySql.backupDatabase();
        this.backupServiceMySql.cleanOldBackups();
    }

}
