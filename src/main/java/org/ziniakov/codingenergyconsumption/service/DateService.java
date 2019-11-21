package org.ziniakov.codingenergyconsumption.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateService {

    /**
     * encapsulates date creation for testability and less coupling
     *
     * @return current date
     */
    public Date getCurrentDate() {
        return new Date();
    }
}
