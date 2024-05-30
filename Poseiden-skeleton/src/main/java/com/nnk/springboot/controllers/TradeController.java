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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Controller
public class TradeController {

	@Autowired
	public TradeRepository tradeRepository;
	
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	List<Trade> trade = tradeRepository.findAll();
    	model.addAttribute("trades", trade);
    	return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

    	if (trade.getAccount().isBlank()) {
    		result.rejectValue("account", null, "Account is mandatory");
		}
    	if (trade.getType().isBlank()) {
    		result.rejectValue("type", null, "Type is mandatory");
		}
    	if (trade.getBuyQuantity()== null) {
    		result.rejectValue("buyQuantity", null, "The quantity bought is mandatory");
		}
    	if (result.hasErrors()) {
            return "trade/add";
		}
    	tradeRepository.save(trade);
    	return "redirect:/trade/list";
    }
    

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Trade trade = tradeRepository.getOne(id);
    	model.addAttribute("trade", trade);
    	return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	if (trade.getTradeId()==null) {
    		result.rejectValue("tradeId", null, "Must not be null");
    	}
    	if (trade.getAccount().isBlank()) {
    		result.rejectValue("account", null, "Account is mandatory");
		}
    	if (trade.getType().isBlank()) {
    		result.rejectValue("type", null, "Type is mandatory");
		}
    	if (trade.getBuyQuantity()== null) {
    		result.rejectValue("buyQuantity", null, "The quantity bought is mandatory");
		}
    	if (result.hasErrors()) {
            return "trade/update";
		}
    	tradeRepository.save(trade);
    	return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	tradeRepository.deleteById(id);
    	return "redirect:/trade/list";
    }
}
