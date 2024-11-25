package net.blwsmartware.museum.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.dto.request.museum.MuseumRequest;
import net.blwsmartware.museum.dto.request.museum.MuseumUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.museum.MuseumResponse;
import net.blwsmartware.museum.entity.Category;
import net.blwsmartware.museum.entity.Museum;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.AppRuntimeException;
import net.blwsmartware.museum.mapper.MuseumMapper;
import net.blwsmartware.museum.repository.CategoryRepository;
import net.blwsmartware.museum.repository.MuseumRepository;
import net.blwsmartware.museum.service.MuseumService;
import net.blwsmartware.museum.util.DataResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MuseumServiceImp implements MuseumService {

    MuseumRepository museumRepository;
    CategoryRepository categoryRepository;
    MuseumMapper museumMapper;

    @Override
    public MuseumResponse createMuseum(MuseumRequest request) {
        Museum museum = museumMapper.toMuseum(request);
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.MUSEUM_NOT_FOUND));
        museum.setCategory(category);
        return museumMapper.toMuseumResponse( museumRepository.save(museum) );
    }

    @Override
    public MuseumResponse getMuseumByID(int id) {
        return museumMapper.toMuseumResponse(museumRepository.findById(id)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.MUSEUM_NOT_FOUND)));    }

    @Override
    public DataResponse<MuseumResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Museum> pageOfMuseum = museumRepository.findAll(pageable);
        List<Museum> museumList = pageOfMuseum.getContent();
        List<MuseumResponse> museumResponses = museumList.stream().map(museumMapper::toMuseumResponse).toList();
        return DataResponseUtils.convertPageInfo(pageOfMuseum,museumResponses);
    }

    @Override
    public DataResponse<MuseumResponse> getListMuseumByCategory(int id, Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Museum> pageOfMuseum = museumRepository.findByCategoryId(id,pageable);
        List<Museum>  museumList = pageOfMuseum.getContent();
        List<MuseumResponse> museumResponses = museumList.stream().map(museumMapper::toMuseumResponse).toList();
        return DataResponseUtils.convertPageInfo(pageOfMuseum,museumResponses);
    }

    @Override
    public MuseumResponse updateMuseum(int id, MuseumUpdate update) {
        Museum old = museumRepository.findById(id)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.MUSEUM_NOT_FOUND));
        return museumMapper.toMuseumResponse( museumRepository.save(old) );
    }

    @Override
    public void delete(int id) {
        museumRepository.deleteById(id);
    }
}
