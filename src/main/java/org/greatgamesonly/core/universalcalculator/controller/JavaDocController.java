package org.greatgamesonly.core.universalcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaDocController {


    @GetMapping("/2y19F09NuVw3vgMvVq1dWx0xQu9-7WVhUJJz0sUrcNMtB6toF5lV3l7m/swagger-ui")
    private String serveJavaDoc() {
        return "index";
    }

}
