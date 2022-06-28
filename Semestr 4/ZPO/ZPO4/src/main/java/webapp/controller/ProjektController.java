package webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import webapp.model.Projekt;
import webapp.service.ProjektService;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@Controller("/projekty")
public class ProjektController {

    private ProjektService projektService;

    @Autowired
    public ProjektController(ProjektService projektService){
        this.projektService = projektService;
    }

    @GetMapping("/projektList")
    public String projektList(Model projekty, Pageable pageable) {
        projekty.addAttribute("projekty", projektService.getProjekty(pageable).getContent());
        return "projektList";
    }

    @GetMapping("/projektEdit")
    public String projektEdit(@RequestParam(required = false) Integer projektId, Model model) {
        if(projektId != null) {
            model.addAttribute("projekt", projektService.getProjekt(projektId).get());
        }else {
            Projekt projekt = new Projekt();
            model.addAttribute("projekt", projekt);
        }
        return "projektEdit";
    }

    @PostMapping(path = "/projektEdit")
    public String projektEditSave(@ModelAttribute @Valid Projekt projekt, BindingResult bindingResult) {
        // parametr BindingResult powinien wystąpić zaraz za parametrem opatrzonym adnotacją @Valid
        if (bindingResult.hasErrors()) {
            return "projektEdit"; // wracamy do okna edycji, jeżeli przesłane dane formularza zawierają błędy
        }
        try {
            projekt = projektService.setProjekt(projekt);
        } catch (HttpStatusCodeException e) {
            bindingResult.rejectValue(null, String.valueOf(e.getStatusCode().value()),
                    e.getStatusCode().getReasonPhrase());
            return "projektEdit";
        }
        return "redirect:/projektList"; // przekierowanie do listy projektów, po utworzeniu lub modyfikacji projektu
    }

    @PostMapping(params="cancel", path = "/projektEdit") // metoda zostanie wywołana, jeżeli przesłane
    public String projektEditCancel() { // żądanie będzie zawierało parametr 'cancel'
        return "redirect:/projektList";
    }

    @PostMapping(params="delete", path = "/projektEdit") // metoda zostanie wywołana, jeżeli przesłane
    public String projektEditDelete(@ModelAttribute Projekt projekt) {// żądanie będzie zawierało parametr 'delete'
        projektService.deleteProjekt(projekt.getProjektId());
        return "redirect:/projektList";
    }

}
