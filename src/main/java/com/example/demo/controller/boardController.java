package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class boardController {

    // 게시판 서비스 연결
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")         // 어떤 url로 접근할거냐? // localhost:8080/board/write
    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(Board board, Model model) {

        boardService.write(board);

        model.addAttribute("message", "글 작성이 완료되었습니다.");

        model.addAttribute("searchUrl", "/board/list");

        return "message";
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

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model) {

        Board boardTemp = boardService.boardView(id);   // 기존의 내용을 가져온다.
        boardTemp.setTitle(board.getTitle());           // 입력한 타이틀을 덧씌운다.
        boardTemp.setContent(board.getContent());       // 입력한 콘텐츠를 덧씌운다.

        boardService.write(boardTemp);                  // 보드서비스 호출해서 boardTemp의 내용을 write한다.

        model.addAttribute("message", "글 작성이 완료되었습니다.");

        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }
}

