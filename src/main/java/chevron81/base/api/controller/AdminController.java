package chevron81.base.api.controller;

import chevron81.base.admin.LogConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
@SuppressWarnings("unused")
public class AdminController {

    Logger logger = LogManager.getLogger(AdminController.class);

    @GetMapping()
    public String admin(final Model model) {
        model.addAttribute("message", "Loglevel Configuration.");
        model.addAttribute("logConfig", new LogConfig());

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Map<String, LoggerConfig> loggers = context.getConfiguration().getLoggers();
        model.addAttribute("loggers", loggers);
        List<Level> logLevels = Arrays.asList(Level.values());
        model.addAttribute("logLevels", logLevels);

        return "admin";
    }

    @PostMapping()
    public String setLogLevel(@ModelAttribute final LogConfig logConfig, final Model model) {
        final Level logLevel = Level.toLevel(logConfig.getLevel().toUpperCase());
        if ("ALL".equalsIgnoreCase(logConfig.getLoggerName())) {
            Configurator.setAllLevels("", logLevel);
            model.addAttribute("message", "Log level for ALL Logger switched to " + logLevel + " .");
        } else {
            Configurator.setLevel(logConfig.getLoggerName(), logLevel);
            model.addAttribute("message", "Log level for " + logConfig.getLoggerName() + " switched to " + logLevel + " .");
        }
        model.addAttribute("logConfig", new LogConfig());
        return "admin";
    }
}