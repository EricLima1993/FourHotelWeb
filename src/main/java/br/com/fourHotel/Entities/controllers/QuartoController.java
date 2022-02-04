package br.com.fourHotel.Entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fourHotel.Entities.services.QuartoService;

@Controller
@RequestMapping("/quarto")
public class QuartoController {

	@Autowired
	private QuartoService qs;
}
