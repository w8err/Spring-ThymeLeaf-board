package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class boardController {

    // 게시판 서비스 연결
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")         // 어떤 url로 접근할거냐? // localhost:8080/board/write
    public String boardWriteForm() {

        return "Example";
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(Board board) {

    boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute("list", boardService.boardList());

        return "boardlist";

    }

    @GetMapping("board/view") // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }
}

