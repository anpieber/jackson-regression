package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setDateFormat(DATE_FORMAT);

    String input = "{\"date\": \"2014-01-02\"}";

    @Test
    public void testApp() throws Exception {
        assertThat(
                OBJECT_MAPPER.writeValueAsString(
                        OBJECT_MAPPER.readValue(
                                input, DateTestClass.class
                        )
                ),
                is(input)
        );
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
