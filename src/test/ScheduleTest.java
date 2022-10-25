package test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import junit.framework.TestCase;
import schedule.MonthlySchedule;
import schedule.DailySchedule;
import schedule.ScheduleInterval;
import workData.DayOfWork;

public class ScheduleTest extends TestCase {
    public void testSlotsGenerated() {
        int appointmentDuration = 30;
        ScheduleInterval workTime = new ScheduleInterval(LocalTime.of(9, 0), LocalTime.of(17, 0));
        ScheduleInterval lunchTime = new ScheduleInterval(LocalTime.of(12, 0), LocalTime.of(13, 0));

        DayOfWork dayOfWork = new DayOfWork(appointmentDuration, workTime, lunchTime);

        DailySchedule day = new DailySchedule(dayOfWork);

        assertEquals(day.getFreeSlots().size(), 14);
        assertFalse(day.getFreeSlots().contains(lunchTime.getStart()));
    }

    public void testMonthlyScheduleGenerated() {
        DayOfWeek[] daysOfWeek = { DayOfWeek.THURSDAY, DayOfWeek.TUESDAY };

        MonthlySchedule schedule = new MonthlySchedule(daysOfWeek, LocalDate.of(2022, 10, 24));

        assertEquals(schedule.getDays().size(), 9);
        
        for(DailySchedule day : schedule.getDays()) {
            DayOfWeek dayOfWeek = day.getDate().getDayOfWeek();
            assertTrue(schedule.getWorkingDays().contains(dayOfWeek));
        }

        schedule.getDays();
    }
}
