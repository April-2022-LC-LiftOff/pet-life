package org.launchcode.PetLife.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@MappedSuperclass
public class AbstractEntityNameDate extends AbstractEntity{

    @Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters.")
    private String name;

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date;

    public AbstractEntityNameDate(@Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters.") String name, String date) {
        this.name = name;
        this.date = LocalDate.parse(date);
    }

    public AbstractEntityNameDate() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return dateFormatter.format(date);
    }

    public LocalDate getLocalDate() {
        return this.date;
    }

    public void setDate(String date) {
        if (date == null) {
            this.date = LocalDate.now();
        } else {
            this.date = LocalDate.parse(date);
        }
    }
}
