package eshop.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eshop.entity.Client;
import eshop.repositories.ClientRepository;

	@Controller
	@RequestMapping("/client")
	
	public class ClientController {
		@Autowired
		private ClientRepository clientRepository;

		@GetMapping({ "", "/" })
		public ModelAndView list() {
			ModelAndView modelAndView = new ModelAndView("client/list");
			modelAndView.addObject("clients", clientRepository.findAll());
			return modelAndView;
		}

		@GetMapping("/edit")
		public ModelAndView edit(@RequestParam Integer id) {
			Optional<Client> opt = clientRepository.findById(id);
			if (opt.isPresent()) {
				return goEdit(opt.get());
			}
			return goEdit(new Client());
		}

		@GetMapping("/delete")
		public ModelAndView delete(@RequestParam Integer id) {
			clientRepository.deleteById(id);
			return new ModelAndView("redirect:/client");
		}

		@GetMapping("/add")
		public ModelAndView add() {
			return goEdit(new Client());
		}

		@PostMapping("/save")
		public ModelAndView save(@Valid @ModelAttribute Client client, BindingResult br) {
			System.out.println(br);
			if (br.hasErrors()) {
				return goEdit(client);
			}
			clientRepository.save(client);
			return new ModelAndView("redirect:/client");
		}

		private ModelAndView goEdit(Client client) {
			ModelAndView modelAndView = new ModelAndView("client/edit", "client", client);
			return modelAndView;
		}

		
	}