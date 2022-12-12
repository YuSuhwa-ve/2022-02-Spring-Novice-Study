package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.CommentResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        System.out.println("리다이렉션 1");
        model.addAttribute("posts", postsService.findAllDesc());
        System.out.println("리다이렉션 2");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }


        return "index";
    }



    @GetMapping("/posts/save")
    public String postsSave(@LoginUser SessionUser sessionUser, Model model) {
        if(sessionUser!=null){
            model.addAttribute("userEmail",sessionUser.getEmail());
        }
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }

    @GetMapping("/posts/detail/{id}")
    public String getDetail(@PathVariable Long id, @LoginUser SessionUser sessionUser, Model model){
        PostsResponseDto dto = postsService.findById(id);

        List<CommentResponseDto> comments = dto.getComments();

        if(comments !=null && !comments.isEmpty()){
            model.addAttribute("comments",comments);
        }
        model.addAttribute("post",dto);
        return "posts-detail";

    }

}
