package chevron81.barebone.api.controller;

import chevron81.barebone.admin.LogConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@SuppressWarnings("unused")
public class AdminController {

    @GetMapping()
    public String admin(final Model model) {
        model.addAttribute("message", "Auf dieser Seite können Sie administrative Aufgaben durchführen.");
        model.addAttribute("logConfig", new LogConfig());
        return "admin";
    }

    @PostMapping()
    public String setLogLevel(@ModelAttribute final LogConfig logConfig, final Model model) {
        final Level logLevel = Level.toLevel(logConfig.getLevel().toUpperCase());
        if ("ALL".equalsIgnoreCase(logConfig.getLoggerName())) {
            Configurator.setAllLevels("", logLevel);
            model.addAttribute("message", "Log level für alle Logger auf " + logLevel + " gesetzt.");
        } else {
            Configurator.setLevel(logConfig.getLoggerName(), logLevel);
            model.addAttribute("message", "Log level für " + logConfig.getLoggerName() + " auf " + logLevel + " gesetzt.");
        }
        model.addAttribute("logConfig", new LogConfig());
        return "admin";
    }
}