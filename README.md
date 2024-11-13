# springboot-base

a small springboot microservice barebone

Functions:

- H2 flatfile database
- Backup to sql file by cron or context update
- Healthcheck Rest Service
- Admin Rest Service

Configuration

custom application.properties elements

```
Databse automated backup mechanism 
creates a sql file backup of the database
default false
database.backup.enabled
```

```
Cron expression for time based database backup:
database.backup.cron
default 0 0 3 * * ? (every day at 3:00 AM)
```

```
Number of most actual backup files to keep
default 10
database.backup.keep
```

```
Path and filename for the backup files
default ./data/backup
database.backup.path
```

```
pattern for middle part of the backup file name
default yyyyMMdd_HHmmss
database.backup.pattern
```