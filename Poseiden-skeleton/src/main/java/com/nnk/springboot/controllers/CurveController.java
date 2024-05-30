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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Controller
public class CurveController {
	
	@Autowired
	public CurvePointRepository curvePointRepository;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
    	List<CurvePoint> curvePoint = curvePointRepository.findAll();
    	model.addAttribute("curvePoints", curvePoint);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

    	if (curvePoint.getCurveId()==null) {
    		result.rejectValue("curveId", null, "Must not be null");
    	}
    	if (curvePoint.getTerm()==null) {
    		result.rejectValue("term", null, "Term is mandatory");
		}
    	if (curvePoint.getValue()==null) {
    		result.rejectValue("value", null, "Value is mandatory");
		}
    	if (result.hasErrors()) {
            return "curvePoint/add";
		}
    	curvePointRepository.save(curvePoint);
    	return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	CurvePoint curvePoint = curvePointRepository.getOne(id);
    	model.addAttribute("curvePoint", curvePoint);
    	return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
    	if (curvePoint.getCurveId()==null) {
    		result.rejectValue("curveId", null, "Must not be null");
    	}
    	if (curvePoint.getTerm()==null) {
    		result.rejectValue("term", null, "Term is mandatory");
		}
    	if (curvePoint.getValue()==null) {
    		result.rejectValue("value", null, "Value is mandatory");
		}
    	if (result.hasErrors()) {
            return "curvePoint/update";
		}
    	
    	curvePointRepository.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	curvePointRepository.deleteById(id);
    	return "redirect:/curvePoint/list";
    }
}
