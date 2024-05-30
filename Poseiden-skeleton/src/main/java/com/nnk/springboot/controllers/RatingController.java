package com.nnk.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Controller
public class RatingController {

	@Autowired
	public RatingRepository ratingRepository;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		List<Rating> rating = ratingRepository.findAll();
		model.addAttribute("ratings", rating);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		if (rating.getMoodysRating().isBlank()) {
			result.rejectValue("moodysRating", null, "Moody Rating is mandatory");
		}
		if (rating.getSandPRating().isBlank()) {
			result.rejectValue("sandPRating", null, "Sand rating is mandatory");
		}
		if (rating.getFitchRating().isBlank()) {
			result.rejectValue("fitchRating", null, "Fitch Rating is mandatory");
		}
		if (rating.getOrderNumber() == null) {
			result.rejectValue("orderNumber", null, "Must not be null");
		}
		if (result.hasErrors()) {
			return "rating/add";
		}
		
		ratingRepository.save(rating);
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingRepository.getOne(id);
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		if (rating.getId() == null) {
			result.rejectValue("id", null, "Must not be null");
		}
		if (rating.getMoodysRating().isBlank()) {
			result.rejectValue("moodysRating", null, "Moody Rating is mandatory");
		}
		if (rating.getSandPRating().isBlank()) {
			result.rejectValue("sandPRating", null, "Sand rating is mandatory");
		}
		if (rating.getFitchRating().isBlank()) {
			result.rejectValue("fitchRating", null, "Fitch Rating is mandatory");
		}
		if (rating.getOrderNumber() == null) {
			result.rejectValue("orderNumber", null, "Must not be null");
		}
		if (result.hasErrors()) {
			return "rating/update";
		}
		ratingRepository.save(rating);

		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		ratingRepository.deleteById(id);
		return "redirect:/rating/list";
	}
}
