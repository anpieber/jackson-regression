package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest {

    String input = "{\"date\":\"2014-01-02\"}";

    @Test
    public void workingDateTest() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        ObjectMapper OBJECT_MAPPER = new ObjectMapper().setDateFormat(format);
        assertThat(
                OBJECT_MAPPER.writeValueAsString(
                        OBJECT_MAPPER.readValue(
                                input, DateTestClass.class
                        )
                ),
                is(input)
        );
    }

    @Test
    public void notWorkingDateTest() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));
        ObjectMapper OBJECT_MAPPER = new ObjectMapper().setDateFormat(format);
        assertThat(
                OBJECT_MAPPER.writeValueAsString(
                        OBJECT_MAPPER.readValue(
                                input, DateTestClass.class
                        )
                ),
                is(input)
        );
    }

    @Test
    public void conversationOfTextToDateAndBackShouldWorkForViennaTimezone() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));
        assertThat(format.format(format.parse("2015-01-01")), is(("2015-01-01")));
    }

    @Test
    public void conversationOfTextToDateAndBackShouldWorkForDefaultTimezone() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assertThat(format.format(format.parse("2015-01-01")), is(("2015-01-01")));
    }

    public static class DateTestClass {
        private Date date;

        public DateTestClass() {
        }

        public DateTestClass(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

}
