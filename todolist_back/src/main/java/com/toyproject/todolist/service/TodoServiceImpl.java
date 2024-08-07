package com.toyproject.todolist.service;

import com.toyproject.todolist.dto.ReqUpdateTodoDto;
import com.toyproject.todolist.dto.RespTodoListDto;
import com.toyproject.todolist.dto.TodoDto;
import com.toyproject.todolist.entity.Todo;
import com.toyproject.todolist.repository.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public int addTodo(TodoDto.ReqDto dto) {
        System.out.println(dto);
        return todoMapper.save(dto.toEntity());
    }

    @Override
    public List<TodoDto.RespDto> getListAll(int userid) {
        return todoMapper.getListAll(userid).stream().map(Todo::toDto).collect(Collectors.toList());
    }

    // 전체 조회
    @Override
    public List<TodoDto.RespDto> getAllTodoList() {
        return todoMapper.getAllList().stream().map(Todo::toDto).collect(Collectors.toList());
    }
    //날짜조회
    @Override
    public List<RespTodoListDto> getAllTodoList(String month) {
        List<Todo> todoList = todoMapper.getAllTodoList(month);
        List<RespTodoListDto> respList = new ArrayList<>();

        for(Todo todo : todoList) {
            RespTodoListDto dto = RespTodoListDto.builder()
                    .todoId(todo.getTodo_id())
                    .todoText(todo.getTodo_text())
                    .todoChkId(todo.getTodo_chk_id())
                    .todoDate(todo.getTodo_date())
                    .build();
            respList.add(dto);
        }
        return respList;
    }

    // 해당날짜에 해당하는 모든 것 들고오기
    @Override
    public List<RespTodoListDto> getDateList(String today) {
        List<Todo> todoDate= todoMapper.getDateList(today);

        List<RespTodoListDto> respDateList = new ArrayList<>();
        for(Todo todo : todoDate) {
            RespTodoListDto respList = RespTodoListDto.builder()
                    .todoId(todo.getTodo_id())
                    .todoText(todo.getTodo_text())
                    .todoChkId(todo.getTodo_chk_id())
                    .todoDate(todo.getDate())
                    .build();
            respDateList.add(respList);
        }
        return respDateList;
    }

    @Override
    public int updateTodo(ReqUpdateTodoDto reqDto) {
        // Todo에 객체에 reqDto데이터들을 담음
        Todo todo = Todo.builder()
                .todo_id(reqDto.getTodoId())
                .todo_text(reqDto.getTodoText())
//                .todo_chk_id(reqDto.getTodoChkId())
//                .todo_date(reqDto.getTodoDate())
                .build();

        return todoMapper.update(todo);
    }

    // 체크 시에 업데이트 요청
    @Override
    public int updateChk(ReqUpdateTodoDto reqDto) {
        Todo todo = Todo.builder()
                .todo_id(reqDto.getTodoId())
                .todo_chk_id(reqDto.getTodoChkId())
                .build();

        return todoMapper.updateChk(todo);
    }

    @Override
    public int deleteTodo(int todoId) {
        return todoMapper.delete(todoId);
    }

    // 로그인 구현 후 사용 될 메소드 (테스트 안 했음)
    @Override
    public List<TodoDto.userRespDto> getListTodo(TodoDto.userReqDto dto) {
        return todoMapper.getList(dto.getUserId(), dto.getTodoDate()).stream().map(Todo::userToDto).collect(Collectors.toList());
    }

    @Override
    public int addUserTodo(TodoDto.userReqDto dto) {
        return todoMapper.add(dto.toInsertEntity());
    }

    @Override
    public int editUserTodo(TodoDto.userReqDto dto) {
        return todoMapper.edit(dto.toEditEntity());
    }

    @Override
    public int editChk(TodoDto.userReqDto dto) {
        return todoMapper.edit(dto.toEditEntity());
    }
}
