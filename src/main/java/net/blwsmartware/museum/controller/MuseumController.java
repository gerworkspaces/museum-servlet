package net.blwsmartware.museum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.constant.PagePrepare;
import net.blwsmartware.museum.dto.request.museum.MuseumRequest;
import net.blwsmartware.museum.dto.request.museum.MuseumUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.dto.response.museum.MuseumResponse;
import net.blwsmartware.museum.service.MuseumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/museum")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class MuseumController {

    MuseumService museumService;

    @PostMapping
    public ResponseEntity<MessageResponse<MuseumResponse>> createMuseum(@RequestBody @Valid MuseumRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<MuseumResponse>builder()
                        .result(museumService.createMuseum(request))
                        .build()
                );
    }
    @GetMapping
    public ResponseEntity<MessageResponse<DataResponse<MuseumResponse>>> getAllPageable(
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy) {

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<DataResponse<MuseumResponse>>builder()
                    .result(museumService.getAll(pageNumber,pageSize,sortBy))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<MuseumResponse>> getMuseum(@PathVariable int id){

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<MuseumResponse>builder()
                    .result(museumService.getMuseumByID(id))
                .build());
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<MessageResponse<DataResponse<MuseumResponse>>> getByCategory(
            @PathVariable int id,
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<DataResponse<MuseumResponse>>builder()
                        .result(museumService.getListMuseumByCategory(id,pageNumber,pageSize,sortBy))
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<MuseumResponse>> updateMuseum(@RequestBody @Valid MuseumUpdate museumUpdate, @PathVariable int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<MuseumResponse>builder()
                    .result(museumService.updateMuseum(id,museumUpdate))
                    .build()
                );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMuseum(@PathVariable int id) {

        museumService.delete(id);

        return ResponseEntity.noContent().build();

    }





}
