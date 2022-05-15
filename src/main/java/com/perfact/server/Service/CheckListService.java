package com.perfact.server.Service;

import com.perfact.server.domain.checklist.CheckList;
import com.perfact.server.domain.dto.checklist.ListCreateDto;
import com.perfact.server.domain.dto.checklist.ListSearchDto;
import com.perfact.server.domain.dto.checklist.ListUpdateDto;
import com.perfact.server.domain.jpa.CheckListRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckListService {
    private final CheckListRepository checkListRepository;

    @Transactional
    public Long saveList(ListCreateDto listCreateDto){
        return checkListRepository.save(listCreateDto.toEntity()).getId();
    }

    //특정 작성자가 작성한 체크리스트만 어떻게 가지고 올것인지?
    @Transactional
    public List<ListSearchDto>searchAllList(Long user_id){
        List<ListSearchDto> checkLists = checkListRepository.findByUserId(user_id);
        List<CheckList> searchList = new ArrayList<>();
        for (int i = 0; i < checkLists.size(); i++) {
            Long check_id = checkLists.get(i).getId();
            searchList.add(checkListRepository.findById(check_id)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다." + check_id)));
        }
        return searchList.stream().map(ListSearchDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        CheckList list = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 리스트가 존재하지 않습니다."));

        checkListRepository.delete(list);
    }

    @Transactional
    public Long update(Long id, ListUpdateDto listUpdateDto) {
        CheckList checkList = checkListRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 항목이 존재하지 않습니다."));

        checkList.update(listUpdateDto.getContent(),
                listUpdateDto.getStatus());

        return id;
    }
}
