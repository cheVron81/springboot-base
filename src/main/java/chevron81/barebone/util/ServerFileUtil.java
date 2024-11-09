package chevron81.barebone.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerFileUtil {

    private static final Logger LOGGER = LogManager.getLogger(ServerFileUtil.class);

    public static void sortFilesByLastModified(final List<Path> files) {
        files.sort(Comparator.comparingLong(path -> {
            try {
                return Files.getLastModifiedTime(path).toMillis();
            } catch (final IOException e) {
                throw new RuntimeException("Failed to get last modified time for path: " + path, e);
            }
        }));
    }

    public static void deleteFiles(final List<Path> filesToDelete) {
        filesToDelete.forEach(path -> {
            final boolean isDeleted = path.toFile().delete();
            if (isDeleted) {
                LOGGER.info("Deleted File: {}", path);
            } else {
                LOGGER.error("Failed to delete File: {}", path);
            }
        });
    }

    public static List<Path> readFilesFromDirectoryPrefixPostfix(final String directoryPath, final String filePrefix, final String fileExtension) {
        try (final Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> hasPrefixAndExtension(path, filePrefix, fileExtension))
                    .collect(Collectors.toList());
        } catch (final IOException e) {
            LOGGER.error("Failed to read files from directory: {}", directoryPath, e);
            return List.of();
        }
    }

    private static boolean hasPrefixAndExtension(final Path path, final String prefix, final String extension) {
        final String fileName = path.getFileName().toString();
        return fileName.startsWith(prefix) && fileName.endsWith(extension);
    }
}