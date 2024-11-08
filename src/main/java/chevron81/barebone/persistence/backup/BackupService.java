package chevron81.barebone.persistence.backup;

public interface BackupService {
    void backupDatabase();

    void cleanOldBackups();
}