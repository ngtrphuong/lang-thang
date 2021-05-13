package com.langthang.services;

import com.langthang.dto.CommentDTO;

import java.util.List;

public interface ICommentServices {

    CommentDTO addNewComment(int postId, String content, String accEmail);

    CommentDTO modifyComment(int commentId, String content, String accEmail);

    int deleteComment(int commentId, String accEmail);

    List<CommentDTO> getAllCommentOfPost(int postId, String accEmail);

    int likeOrUnlikeComment(int commentId, String accEmail);
}