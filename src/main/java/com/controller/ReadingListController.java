package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Book;
import com.jpa.ReadingListRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

	private ReadingListRepository readingListRepository;

	//自動裝配持久化對象
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}

	@GetMapping(value = "/{reader}")
	public String readersBooks(@PathVariable("reader") String reader, Model model) {

		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}

	@PostMapping(value = "/{reader}")
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}

}