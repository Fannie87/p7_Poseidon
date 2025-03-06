package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController {

	@Autowired
	public RuleNameService ruleNameService;

	@RequestMapping("/ruleName/list")
	public String home(Model model) {

		return ruleNameService.home(model);
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

		return ruleNameService.validate(ruleName, result, model);
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		return ruleNameService.showUpdateForm(id, model);
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		return ruleNameService.updateRuleName(id, ruleName, result, model);
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		return ruleNameService.deleteRuleName(id, model);
	}
}
