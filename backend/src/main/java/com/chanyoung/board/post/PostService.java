package com.chanyoung.board.post;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDtos.Response> findAll() {
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(PostDtos.Response::from)
                .toList();
    }

    public PostDtos.Response findOne(Long id) {
        return PostDtos.Response.from(getPost(id));
    }

    @Transactional
    public PostDtos.Response create(PostDtos.CreateRequest request) {
        Post post = new Post(request.title(), request.content(), request.author());
        return PostDtos.Response.from(postRepository.save(post));
    }

    @Transactional
    public PostDtos.Response update(Long id, PostDtos.UpdateRequest request) {
        Post post = getPost(id);
        post.update(request.title(), request.content());
        postRepository.flush();
        return PostDtos.Response.from(post);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.delete(getPost(id));
    }

    private Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "post not found: " + id));
    }
}
