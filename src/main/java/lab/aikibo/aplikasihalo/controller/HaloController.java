package lab.aikibo.aplikasihalo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tamami on 31/08/17.
 */
@Controller
public class HaloController {

    private static final Logger logger = Logger.getLogger(HaloController.class);

    @RequestMapping("/halo")
    public void halo(Model model, @RequestParam(required = false) String nama) {
        model.addAttribute("waktu", new Date());
        if(nama!= null && !nama.isEmpty()) {
            model.addAttribute("msg", "Selamat datang, " + nama);
        }
    }

}
