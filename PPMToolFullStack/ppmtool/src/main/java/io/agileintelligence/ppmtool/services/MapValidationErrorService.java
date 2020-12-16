package io.agileintelligence.ppmtool.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {
    
    public ResponseEntity<?> MapValidationService(BindingResult result) {
        /*BindingResult: Serves as result holder for a DataBinder
          getFieldErrors: extraxt error from returned error list response*/
        if(result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            /*Filter to return only the field (variable) and the errormessage 
              out of the JSON response (Must return 3 entries: description, projectName, 
              projectIdentifier)*/
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
