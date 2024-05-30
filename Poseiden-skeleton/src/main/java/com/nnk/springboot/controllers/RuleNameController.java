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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Controller
public class RuleNameController {

	@Autowired
	public RuleNameRepository ruleNameRepository;
	
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {

    	List<RuleName> ruleName = ruleNameRepository.findAll();
    	model.addAttribute("ruleNames", ruleName);
    	return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

    	if (ruleName.getName().isBlank()) {
    		result.rejectValue("name", null, "Name is mandatory");
		}
    	if (ruleName.getDescription().isBlank()) {
    		result.rejectValue("description", null, "Description is mandatory");
		}
    	if (ruleName.getJson().isBlank()) {
    		result.rejectValue("json", null, "Json is mandatory");
		}
    	if (ruleName.getTemplate().isBlank()) {
    		result.rejectValue("template", null, "Template is mandatory");
		}
    	if (ruleName.getSqlStr().isBlank()) {
    		result.rejectValue("sqlStr", null, "SqlStr is mandatory");
		}
    	if (ruleName.getSqlPart().isBlank()) {
    		result.rejectValue("sqlPart", null, "SqlPart is mandatory");
		}
    	if (result.hasErrors()) {
            return "ruleName/add";
		}
    	ruleNameRepository.save(ruleName);
    	return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    	RuleName ruleName = ruleNameRepository.getOne(id);
    	model.addAttribute("ruleName", ruleName);
    	return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	if (ruleName.getName().isBlank()) {
    		result.rejectValue("name", null, "Name is mandatory");
		}
    	if (ruleName.getDescription().isBlank()) {
    		result.rejectValue("description", null, "Description is mandatory");
		}
    	if (ruleName.getJson().isBlank()) {
    		result.rejectValue("json", null, "Json is mandatory");
		}
    	if (ruleName.getTemplate().isBlank()) {
    		result.rejectValue("template", null, "Template is mandatory");
		}
    	if (ruleName.getSqlStr().isBlank()) {
    		result.rejectValue("sqlStr", null, "SqlStr is mandatory");
		}
    	if (ruleName.getSqlPart().isBlank()) {
    		result.rejectValue("sqlPart", null, "SqlPart is mandatory");
		}
    	if (result.hasErrors()) {
            return "ruleName/update";
		}
    	ruleNameRepository.save(ruleName);
    	return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	ruleNameRepository.deleteById(id);
    	return "redirect:/ruleName/list";
    }
}
