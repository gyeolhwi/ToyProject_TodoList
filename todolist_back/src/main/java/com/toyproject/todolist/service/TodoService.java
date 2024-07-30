package com.toyproject.todolist.service;

import com.toyproject.todolist.dto.ReqUpdateTodoDto;
import com.toyproject.todolist.dto.TodoDto;

import java.util.List;

public interface TodoService {
     // insert 유저
     int addTodo(TodoDto.ReqDto dto);

     // select 리스트 유저의 text
     List<TodoDto.RespDto> getListAll(int userid);

     // update 수정(id,수정 할 text)
     int updateTodo(ReqUpdateTodoDto reqDto);

     // delete 삭제(todo_id)
     int deleteTodo(int todo_id);
}