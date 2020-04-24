package com.pantigoso.app.springbootdemo.Controller;

import com.pantigoso.app.springbootdemo.Dao.IPersonDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(name = "/persona")
public class PersonaController {


    private IPersonDao personDao;

    @GetMapping(name = "/listado")
    public String listado(Map<String,Object>map){
        String titulo ="Usuarios";
        map.put("person",personDao.findAll());
        map.put("title",titulo);

        return "persona/listado";
    }
}
