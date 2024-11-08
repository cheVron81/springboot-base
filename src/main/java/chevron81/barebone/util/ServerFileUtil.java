package chevron81.barebone.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerFileUtil {

    public static List<Path> readFilesFromDirectoryPrefixPostfix(final String directoryPath, final String filePrefix, final String fileExtension) {
        final Logger LOGGER = LogManager.getLogger(ServerFileUtil.class);
        List<Path> returnValue = null;
        try (final Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
            returnValue = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().startsWith(filePrefix) && path.getFileName().endsWith(fileExtension))
                    .collect(Collectors.toList());
        } catch (final IOException e) {
            LOGGER.error("Failed to read files from directory: {}", directoryPath, e);
        }
        return returnValue;
    }

}
