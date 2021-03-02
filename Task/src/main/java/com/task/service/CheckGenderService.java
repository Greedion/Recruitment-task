package com.task.service;

import com.task.exception.ExceptionArchive;
import com.task.exception.ServiceOperationException;
import com.task.model.MatchWithVariantModel;
import com.task.Presentation;
import logic.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckGenderService {

    private final Logger logger = LoggerFactory.getLogger(CheckGenderService.class);
    private final Algorithm algorithm;

    CheckGenderService(final Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public ResponseEntity<String> selectVariant(MatchWithVariantModel model) {
        if (model.getVariant() == null || model.getVariant().isEmpty()) {
            logger.error(ExceptionArchive.CGS_INVALID_VARIANT_EXCEPTION);
            throw new ServiceOperationException(ExceptionArchive.CGS_INVALID_VARIANT_EXCEPTION);
        } else if (model.getMatch() == null || model.getMatch().isEmpty()) {
            logger.error(ExceptionArchive.CGS_INVALID_MATCH_EXCEPTION);
            throw new ServiceOperationException(ExceptionArchive.CGS_INVALID_MATCH_EXCEPTION);
        } else {
            try {
                int variant = Integer.parseInt(model.getVariant());
                return switch (variant) {
                    case 1 -> ResponseEntity.ok(Presentation.generateMessage(algorithm.variantOne(model.getMatch())));
                    case 2 -> ResponseEntity.ok(Presentation.generateMessage(algorithm.variantTwo(model.getMatch())));
                    default -> throw new ServiceOperationException(ExceptionArchive.CGS_ILLEGAL_STATE_EXCEPTION + variant);
                };
            } catch (NumberFormatException e) {
                logger.error(ExceptionArchive.CGS_NUMBER_FORMAT_EXCEPTION, e.getMessage());
              throw  new ServiceOperationException(ExceptionArchive.CGS_NUMBER_FORMAT_EXCEPTION + e.getMessage());
            }
        }
    }
}
