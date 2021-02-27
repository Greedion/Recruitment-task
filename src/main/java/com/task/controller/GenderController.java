package com.task.controller;

import com.task.model.MatchWithVariantModel;
import com.task.model.TokenModel;
import com.task.service.CheckGenderService;
import com.task.service.GetFileContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
class GenderController {

    private final CheckGenderService checkGenderService;
    private final GetFileContentService getFileContentService;

    GenderController(final CheckGenderService checkGenderService, final GetFileContentService getFileContentService) {
        this.checkGenderService = checkGenderService;
        this.getFileContentService = getFileContentService;
    }

    @PostMapping()
    public ResponseEntity<String> checkGender(@Valid @RequestBody MatchWithVariantModel model) {
        return checkGenderService.selectVariant(model);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<TokenModel> getAllTokens(){
        return getFileContentService.getContentFromFile();
    }

}
