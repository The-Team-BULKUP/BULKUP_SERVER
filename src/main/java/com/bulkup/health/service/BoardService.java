package com.bulkup.health.service;

import com.bulkup.health.config.CurrentUserParameter;
import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.dto.BoardDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.entity.community.Board;
import com.bulkup.health.repository.community.BoardRepository;
import com.bulkup.health.repository.community.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional()
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void createPost(Long accountId, BoardDto.Request.CreatePost req) {
        boardRepository.save(req.toEntity(accountId));
    }

    public BoardDto.Response.GetPosts getPosts() {
        List<Board> posts = boardRepository.findAll();
        return new BoardDto.Response.GetPosts(posts, posts.size());
    }

    public BoardDto.Response.GetPostById getPostById(Long id) {
        Board post = boardRepository.findById(id)
                .orElse(null);

        if (post == null) throw new CustomException(ErrorCode.UNCHECKED_ERROR); // TODO: fix custom error
        return new BoardDto.Response.GetPostById(post.getTitle(), post.getContent(), post.getWriter(), post.getComments());
    }

    public void createComment(Long accountId, Long boardId, BoardDto.Request.CreateComment req) {
        commentRepository.save(req.toEntity(accountId, boardId));
    }

//    public BoardDto.Response.GetComments getComments(Long boardId) {
//        boar
//    }
}
