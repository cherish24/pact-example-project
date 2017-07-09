package wizardsApplication.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wizardsApplication.Entities.Wizard;

@RestController
public class WizardsController {

    @RequestMapping(value = "/harryyy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Wizard getHarryPotter() {
        return new Wizard("Harry Potter", 1.7, 60);
    }

    @RequestMapping(value = "/killHarry", method = RequestMethod.POST)
    public void killHarryPotter() {
        //update db
    }

}
