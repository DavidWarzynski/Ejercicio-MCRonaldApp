package es.neesis.mvcdemo.controller;

import es.neesis.mvcdemo.model.Empleado;
import es.neesis.mvcdemo.exceptions.BusinessException;
import es.neesis.mvcdemo.service.IPlantillaService;
import es.neesis.mvcdemo.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/plantilla")
public class PlantillaController {

    @Autowired
    private IPlantillaService plantillaService;

    @GetMapping
    public String inicializacion(Model model) {
        if (model.getAttribute("plantilla") != null) {
            model.addAttribute("plantilla", model.getAttribute("plantilla"));
        } else {
            model.addAttribute("plantilla", new ArrayList<>());
        }
        return "plantilla";
    }

    @GetMapping("/empleados")
    @ResponseBody
    public void getEmpleados(Model model) {
        model.addAttribute("plantilla", plantillaService.getEmpleados());
    }

    @PostMapping("/addEmpleado")
    @ResponseBody
    public void addEmpleado(@RequestBody Empleado empleado, Model model) {
        plantillaService.altaEmpleado(DTOMapper.empladoToDTO(empleado));
        model.addAttribute("plantilla", plantillaService.getEmpleados());
    }

    @DeleteMapping("/bajaEmpleado/{empleadoId}")
    @ResponseBody
    public void bajaEmpleado(@PathVariable Long empleadoId, Model model) throws BusinessException {
        plantillaService.bajaEmpleado(empleadoId);
        model.addAttribute("plantilla", plantillaService.getEmpleados());
    }

}
