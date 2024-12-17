package chevron81.base.persistence.backup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.datasource.platform", havingValue = "mysql")
@Primary
public class BackupServiceMySql extends BackupServiceBase implements BackupService {

    private static final Logger LOGGER = LogManager.getLogger(BackupServiceMySql.class);

    @Override
    public void backupDatabase() {
        LOGGER.info("MySql backup service: No specific backup implementation provided.");
    }
    
}