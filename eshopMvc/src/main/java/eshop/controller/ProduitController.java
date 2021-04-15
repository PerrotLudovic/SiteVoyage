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

import eshop.entity.Produit;
import eshop.repositories.ProduitRepository;

	@Controller
	@RequestMapping("/produit")
	
	public class ProduitController {
		@Autowired
		private ProduitRepository produitRepository;

		@GetMapping({ "", "/" })
		public ModelAndView list() {
			ModelAndView modelAndView = new ModelAndView("produit/list");
			modelAndView.addObject("produits", produitRepository.findAll());
			return modelAndView;
		}

		@GetMapping("/edit")
		public ModelAndView edit(@RequestParam Integer id) {
			Optional<Produit> opt = produitRepository.findById(id);
			if (opt.isPresent()) {
				return goEdit(opt.get());
			}
			return goEdit(new Produit());
		}

		@GetMapping("/delete")
		public ModelAndView delete(@RequestParam Integer id) {
			produitRepository.deleteById(id);
			return new ModelAndView("redirect:/produit");
		}

		@GetMapping("/add")
		public ModelAndView add() {
			return goEdit(new Produit());
		}

		@PostMapping("/save")
		public ModelAndView save(@Valid @ModelAttribute Produit produit, BindingResult br) {
			System.out.println(br);
			if (br.hasErrors()) {
				return goEdit(produit);
			}
			produitRepository.save(produit);
			return new ModelAndView("redirect:/produit");
		}

		private ModelAndView goEdit(Produit produit) {
			ModelAndView modelAndView = new ModelAndView("produit/edit", "produit", produit);
			return modelAndView;
		}

		
	}