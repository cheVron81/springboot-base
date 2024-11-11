package chevron81.barebone.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@SuppressWarnings("unused")
public class AdminController {

    @GetMapping()
    public String admin(final Model model) {
        model.addAttribute("message", "Auf dieser Seite können Sie administrative Aufgaben durchführen.");


        return "home";
    }


}
