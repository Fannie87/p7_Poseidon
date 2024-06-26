package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {
	@Autowired
	public BidListRepository bidListRepository;

	public String home(Model model) {
		List<BidList> bidList = bidListRepository.findAll();
		model.addAttribute("bidLists", bidList);
		return "bidList/list";
	}

	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		if (bid.getAccount().isBlank()) {
			result.rejectValue("account", null, "Account is mandatory");
		}
		if (bid.getType().isBlank()) {
			result.rejectValue("type", null, "Type is mandatory");
		}
		if (bid.getBidQuantity() == null) {
			result.rejectValue("bidQuantity", null, "The bid quantity is mandatory");
		}
		if (result.hasErrors()) {
			return "bidList/add";
		}

		bidListRepository.save(bid);
		return "redirect:/bidList/list";
	}

	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidListRepository.getOne(id);
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {

		if (bidList.getAccount().isBlank()) {
			result.rejectValue("account", null, "Please field the account");
		}
		if (bidList.getType().isBlank()) {
			result.rejectValue("type", null, "Please field the type");
		}
		if (bidList.getBidQuantity() == null) {
			result.rejectValue("bidQuantity", null, "Please field the bid quantity");
		}
		if (result.hasErrors()) {
			return "bidList/update";
		}

		bidListRepository.save(bidList);

		return "redirect:/bidList/list";
	}

	
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	bidListRepository.deleteById(id);
        return "redirect:/bidList/list";
    }
}
