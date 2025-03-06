package com.nnk.springboot.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public String home(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}

	public String validate(@Valid User user, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			
			// au moins une lettre majuscule, au moins 8 caractères, au moins un chiffre et un symbole)
			String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(user.getPassword());
			
			if (!matcher.matches()) {
				result.rejectValue("password", null, "Must have at least: "
						+ " 1 capital letter,"
						+ " 8 characters,"
						+ " 1 digit, "
						+ " 1 of these symbols @#$%^&+=");
				return "/user/add";
			}
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(user);
			model.addAttribute("users", userRepository.findAll());
			return "redirect:/user/list";
		}
		return "user/add";
	}

	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setPassword("");
		model.addAttribute("user", user);
		return "user/update";
	}

	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/update";
		}
		// au moins une lettre majuscule, au moins 8 caractères, au moins un chiffre et un symbole)
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getPassword());
		
		if (!matcher.matches()) {
			result.rejectValue("password", null, "Must have at least: "
					+ " 1 capital letter,"
					+ " 8 characters,"
					+ " 1 digit, "
					+ " 1 of these symbols @#$%^&+=");
			return "/user/update";
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setId(id);
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "redirect:/user/list";
	}

	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		model.addAttribute("users", userRepository.findAll());
		return "redirect:/user/list";
	}
}
