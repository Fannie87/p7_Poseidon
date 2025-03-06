package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurveService {

	@Autowired
	public CurvePointRepository curvePointRepository;

	public String home(Model model) {
		List<CurvePoint> curvePoint = curvePointRepository.findAll();
		model.addAttribute("curvePoints", curvePoint);
		return "curvePoint/list";
	}


	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

		if (curvePoint.getCurveId() == null) {
			result.rejectValue("curveId", null, "Must not be null");
		}
		if (curvePoint.getTerm() == null) {
			result.rejectValue("term", null, "Term is mandatory");
		}
		if (curvePoint.getValue() == null) {
			result.rejectValue("value", null, "Value is mandatory");
		}
		if (result.hasErrors()) {
			return "curvePoint/add";
		}
		curvePointRepository.save(curvePoint);
		return "redirect:/curvePoint/list";
	}

	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointRepository.getReferenceById(id);
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		if (curvePoint.getCurveId() == null) {
			result.rejectValue("curveId", null, "Must not be null");
		}
		if (curvePoint.getTerm() == null) {
			result.rejectValue("term", null, "Term is mandatory");
		}
		if (curvePoint.getValue() == null) {
			result.rejectValue("value", null, "Value is mandatory");
		}
		if (result.hasErrors()) {
			return "curvePoint/update";
		}

		curvePointRepository.save(curvePoint);
		return "redirect:/curvePoint/list";
	}

	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		curvePointRepository.deleteById(id);
		return "redirect:/curvePoint/list";
	}
}
