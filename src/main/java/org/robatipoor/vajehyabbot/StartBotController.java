package org.robatipoor.vajehyabbot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StartBotController
 */
@RestController
public class StartBotController {

    @RequestMapping("/")
    public String home() {
      return "Start Bot!";
    }
}
