package com.pantigoso.app.springbootdemo.Controller;

import com.pantigoso.app.springbootdemo.Dao.IPersonDao;
import com.pantigoso.app.springbootdemo.Dao.IPersonaImpl;
import com.pantigoso.app.springbootdemo.Model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonaController {

    @Autowired
    private IPersonaImpl personaDao;

    @GetMapping("/list")
    public String listado(Map<String,Object>model){
        String titulo ="Usuarios";
        model.put("person", personaDao.findAll());
        model.put("title",titulo);

        return "persona/listado";
    }
    @GetMapping("/form")
    public String crear(Model model){

        Persona person= new Persona();
        model.addAttribute("title","Formulario de Usuario");
        model.addAttribute("persona",person);
        return "persona/formulario";
    }
    @PostMapping("/form")
    public String save(Persona persona , Map<String , Object>model){
        personaDao.save(persona);
        return "redirect:/person/list";
    }
}
