package com.kingwan.community.service;

import com.kingwan.community.dto.PaginationDTO;
import com.kingwan.community.dto.QuestionDTO;
import com.kingwan.community.mapper.QuestionMapper;
import com.kingwan.community.mapper.UserMapper;
import com.kingwan.community.model.Question;
import com.kingwan.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwan on 2020/8/7.
 * 说明：
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public PaginationDTO getList(Integer page, Integer size) {
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.getList(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }
}
