package com.practice.blog.services;

import com.practice.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commmentDto,Integer postId);
	void deleteComment(Integer commentId);
	
}
