package com.jojoldu.book.springboot.domain.comments;

import com.jojoldu.book.springboot.domain.comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentsRepository extends JpaRepository<Comments,Long>{
}

