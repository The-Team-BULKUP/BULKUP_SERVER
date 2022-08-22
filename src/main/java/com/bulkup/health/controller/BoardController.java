package com.bulkup.health.controller;

import com.bulkup.health.config.CurrentUserParameter;
import com.bulkup.health.dto.BoardDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.service.BoardService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping()
    public BoardDto.Response.GetPosts getPost() {
        return this.boardService.getPosts();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@CurrentUserParameter Account account,@Validated BoardDto.Request.CreatePost req) {
        // TODO: make custom exception if account is null
        boardService.createPost(account.getId(), req);
    }

    @GetMapping("/{id}")
    public BoardDto.Response.GetPostById getPostById(@PathVariable("id") Long id) {
        return boardService.getPostById(id);
    }
}
