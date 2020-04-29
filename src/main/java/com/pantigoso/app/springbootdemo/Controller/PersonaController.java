package com.pantigoso.app.springbootdemo.Controller;

import com.pantigoso.app.springbootdemo.Dao.IPersonDao;
import com.pantigoso.app.springbootdemo.Dao.IPersonaImpl;
import com.pantigoso.app.springbootdemo.Model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String save(@Valid Persona persona , BindingResult result, Map<String,Object>model, RedirectAttributes flash,
                       SessionStatus session){

        String titulo = "Formulario de usuario";
        if(result.hasErrors()){
            model.put("title",titulo);
            return "persona/formulario";
        }
        personaDao.save(persona);
        session.setComplete();
        String mensaje = (persona.getId() != null) ? "Usuario editado con exito" : "Usuario creado con exito";
        flash.addFlashAttribute("suc",mensaje);

        return "redirect:/person/list";
    }
    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value="id")Long id, Map<String,Object>model, RedirectAttributes flash){

        Persona person = null;
        if(id > 0){
            person = personaDao.find(id);
            if(person == null){
                flash.addFlashAttribute("error","El ID del usuario no existe en la BD");
                return "redirect:/person/list";
            }
        }else{
            flash.addFlashAttribute("error","El ID del usuario no puede ser 0");
            return "redirect:/person/list";
        }
        model.put("title","Editar Usuario");
        model.put("persona",person);
        return "persona/formulario";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value="id")Long id, RedirectAttributes flash){
        if(id > 0){
            personaDao.delete(id);
            flash.addFlashAttribute("warning","El usuario fue eliminado con exito");
        }
        return "redirect:/person/list";
    }
}
