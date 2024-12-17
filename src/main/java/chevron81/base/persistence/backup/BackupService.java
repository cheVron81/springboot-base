package chevron81.base.persistence.backup;

public interface BackupService {
    void backupDatabase();

    void cleanOldBackups();
}