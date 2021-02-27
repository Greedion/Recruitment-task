package com.task.dataaccessobject;

import com.task.service.Gender;
import java.io.BufferedReader;

public interface DataAccessInterface {

     BufferedReader streamFactory(Gender gender);
}
