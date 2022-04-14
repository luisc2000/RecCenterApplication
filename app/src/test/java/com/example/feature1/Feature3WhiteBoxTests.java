package com.example.feature1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import android.app.Application;
import org.junit.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Feature3WhiteBoxTests extends Application
{
    @Test
    public void testCorrectDate()
    {
        BookingPage.createCalendar();

        //Time Comparison against given class
        Calendar testCalendar = Calendar.getInstance();
        DateFormat testFormat = DateFormat.getDateInstance();
        Date testDay1 = testCalendar.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("MMM dd, yyyy");
        try {
            testDay1 = simple.parse(simple.format(testDay1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String testDay1_String = testFormat.format(testDay1);
        testCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date testDay2 = testCalendar.getTime();
        String testDay2_String = testFormat.format(testDay2);

        testCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date testDay3 = testCalendar.getTime();
        String testDay3_String = testFormat.format(testDay3);

        testCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date testDay4 = testCalendar.getTime();
        String testDay4_String = testFormat.format(testDay4);

        assertEquals(BookingPage.days.get(0), testDay1_String);
        assertEquals(BookingPage.days.get(1), testDay2_String);
        assertEquals(BookingPage.days.get(2), testDay3_String);
        assertEquals(BookingPage.days.get(3), testDay4_String);
    }

    /*
        Tests that the function mapCreate within BookingPage is working
     */
    @Test
    public void testMapCreator()
    {
        Map<String, Object> testCenter = new HashMap<>();
        BookingPage.mapCreate(testCenter, "Test");
        assertEquals(testCenter.get("name"), "Test");
        assertEquals(testCenter.get("1000-1200"), "5");
        assertEquals(testCenter.get("1200-1400"), "5");
        assertEquals(testCenter.get("1400-1600"), "5");
    }
    /*
    This test is to ensure the splicing method I use within method populateReminder is splitting strings. This includes the reminder feature.
     */
    @Test
    public void testPopulateReminder()
    {
        String capacityString = "3";
        String crom = "Cromwell|Apr 11, 2021|1000-1200";
        String[] cromSplit =crom.split("\\|");
        String lyon = "Lyon|Apr 13, 2021|1200-1500";
        String village = "Village|Apr 12, 2022|1400-1600";
        String[] villageSplit = village.split("\\|");
        String[] lyonSplit = lyon.split("\\|");
        String aqua = "Uytengsu|Apr 8, 2022|1400-1600";
        String[] aquaSplit = aqua.split("\\|");
        assertEquals(cromSplit[0], "Cromwell");
        assertEquals(cromSplit[1], "Apr 11, 2021");
        assertEquals(cromSplit[2], "1000-1200");
        assertEquals(lyonSplit[0], "Lyon");
        assertEquals(lyonSplit[1], "Apr 13, 2021");
        assertEquals(lyonSplit[2], "1200-1500");
        assertEquals(villageSplit[0], "Village");
        assertEquals(villageSplit[1], "Apr 12, 2022");
        assertEquals(villageSplit[2], "1400-1600");
        assertEquals(aquaSplit[0], "Uytengsu");
        assertEquals(aquaSplit[1], "Apr 8, 2022");
        assertEquals(aquaSplit[2], "1400-1600");
        assertEquals("Uytengsu Apr 8, 2022 1400-1600 capacity(3/5)", aquaSplit[0] + " " + aquaSplit[1] + " " + aquaSplit[2] + " capacity" + "(" +capacityString+ "/5)");
        assertEquals("Cromwell Apr 11, 2021 1000-1200 capacity(3/5)", cromSplit[0] + " " + cromSplit[1] + " " + cromSplit[2] + " capacity" + "(" +capacityString+ "/5)");
    }
    /**
     * Ensures the date function within shift is accurately checking if the dates are correctly aligned
     */
    @Test
    public void testDateBeforeComparison()
    {
        Calendar testCalendar = Calendar.getInstance();
        Date testDay1 = testCalendar.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("MMM dd, yyyy");
        try {
            testDay1 = simple.parse(simple.format(testDay1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BookingPage.today = testDay1;

        String res = "Cromwell|Apr 11, 2021|1000-1200";
        String[] str = res.split("\\|");
        SimpleDateFormat sdf =new SimpleDateFormat("MMM dd, yyyy");
        Date date1 = null;

        try {
            date1 = sdf.parse(str[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(date1.before(BookingPage.today));
        assertFalse(date1.after(BookingPage.today));
    }

    @Test
    public void testCreateNameConversions()
    {
        BookingPage.createNameConversions("Village", "Uytengsu", "Cromwell", "Lyon");
        assertEquals(BookingPage.namesMap.get("Village"),"Village_Center" );
        assertEquals(BookingPage.namesMap.get("Uytengsu"),"Aqua_Center" );
        assertEquals(BookingPage.namesMap.get("Cromwell"),"Cromwell_Center" );
        assertEquals(BookingPage.namesMap.get("Lyon"),"Lyon_Center" );
    }
}
