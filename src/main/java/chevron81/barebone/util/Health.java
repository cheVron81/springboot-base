package chevron81.barebone.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Health {
    public final static String RUNNING = "RUNNING";

    String status;
    String freeMemory;
    String totalMemory;
    String maxMemory;

    public Health() {

        final Runtime runtime = Runtime.getRuntime();

        // Berechne den freien Speicher
        final long freeMemory = runtime.freeMemory();
        final long totalMemory = runtime.totalMemory();
        final long maxMemory = runtime.maxMemory();

        this.status = Health.RUNNING;
        this.freeMemory = freeMemory / (1024 * 1024) + " MB";
        this.totalMemory = totalMemory / (1024 * 1024) + " MB";
        this.maxMemory = maxMemory / (1024 * 1024) + " MB";
    }
}
