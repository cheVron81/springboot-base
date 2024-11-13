package chevron81.barebone.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Health {

    AppStatusEnum status;
    String freeMemory;
    String totalMemory;
    String maxMemory;
    String availableProcessors;

    public Health() {

        final Runtime runtime = Runtime.getRuntime();

        // Berechne den freien Speicher
        final long freeMemory = runtime.freeMemory();
        final long totalMemory = runtime.totalMemory();
        final long maxMemory = runtime.maxMemory();
        final int availableProcessors = runtime.availableProcessors();

        this.status = AppStatusEnum.RUNNING;
        this.freeMemory = freeMemory / (1024 * 1024) + " MB";
        this.totalMemory = totalMemory / (1024 * 1024) + " MB";
        this.maxMemory = maxMemory / (1024 * 1024) + " MB";
        this.availableProcessors = Integer.toString(availableProcessors);
    }
}
