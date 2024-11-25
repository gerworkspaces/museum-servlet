package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.museum.MuseumRequest;
import net.blwsmartware.museum.dto.request.museum.MuseumUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.museum.MuseumResponse;

import java.util.List;

public interface MuseumService {
    MuseumResponse createMuseum(MuseumRequest request);
    MuseumResponse getMuseumByID(int id);
    DataResponse<MuseumResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy);
    DataResponse<MuseumResponse> getListMuseumByCategory(int id,Integer pageNumber, Integer pageSize, String sortBy);
    MuseumResponse updateMuseum(int id, MuseumUpdate update);
    void delete(int id);
}
