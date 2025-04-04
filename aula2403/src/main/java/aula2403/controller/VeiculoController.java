package aula2403.controller;

import aula2403.model.dao.VeiculoDao;
import aula2403.model.entity.Veiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("veiculo")
public class VeiculoController {

    VeiculoDao dao = new VeiculoDao();

    @GetMapping("/form")
    public String form(Veiculo veiculo){
        return "/veiculo/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("veiculos", dao.buscarVeiculos());
        return new ModelAndView("/veiculo/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Veiculo veiculo){
        dao.save(veiculo);
        return new ModelAndView("redirect:/veiculo/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("veiculo", dao.buscarVeiculo(id));
        return new ModelAndView("/veiculo/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Veiculo veiculo) {
        dao.update(veiculo);
        return new ModelAndView("redirect:/veiculo/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        dao.remove(id);
        return new ModelAndView("redirect:/veiculo/list");
    }

}
