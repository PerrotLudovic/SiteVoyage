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

import eshop.entity.Fournisseur;
import eshop.repositories.FournisseurRepository;

	@Controller
	@RequestMapping("/fournisseur")
	
	public class FournisseurController {
		@Autowired
		private FournisseurRepository fournisseurRepository;

		@GetMapping({ "", "/" })
		public ModelAndView list() {
			ModelAndView modelAndView = new ModelAndView("fournisseur/list");
			modelAndView.addObject("fournisseurs", fournisseurRepository.findAll());
			return modelAndView;
		}

		@GetMapping("/edit")
		public ModelAndView edit(@RequestParam Integer id) {
			Optional<Fournisseur> opt = fournisseurRepository.findById(id);
			if (opt.isPresent()) {
				return goEdit(opt.get());
			}
			return goEdit(new Fournisseur());
		}

		@GetMapping("/delete")
		public ModelAndView delete(@RequestParam Integer id) {
			fournisseurRepository.deleteById(id);
			return new ModelAndView("redirect:/fournisseur");
		}

		@GetMapping("/add")
		public ModelAndView add() {
			return goEdit(new Fournisseur());
		}

		@PostMapping("/save")
		public ModelAndView save(@Valid @ModelAttribute Fournisseur fournisseur, BindingResult br) {
			System.out.println(br);
			if (br.hasErrors()) {
				return goEdit(fournisseur);
			}
			fournisseurRepository.save(fournisseur);
			return new ModelAndView("redirect:/fournisseur");
		}

		private ModelAndView goEdit(Fournisseur fournisseur) {
			ModelAndView modelAndView = new ModelAndView("fournisseur/edit", "fournisseur", fournisseur);
			return modelAndView;
		}

		
	}