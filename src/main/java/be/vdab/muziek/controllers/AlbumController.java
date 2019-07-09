package be.vdab.muziek.controllers;

import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// enkele imports
@Controller
@RequestMapping("albums")
class AlbumController {
    private final AlbumService albumService;
    //CONSTRUCTORS
    AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }
    @GetMapping("{id}")
    public ModelAndView album(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("album");
        albumService.findById(id).ifPresent(album -> modelAndView.addObject(album));
        return modelAndView;
    }
}
