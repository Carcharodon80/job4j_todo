package ru.job4j.todo.util;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

public final class CorrectorTimeOfCreateTask {
    private CorrectorTimeOfCreateTask() {
    }

    /**
     * Корректирует время создания задач с учетом часового пояса User.
     * Если User - Гость, или у User нет часового пояса - берется часовой пояс по умолчанию.
     */
    public static void correctTime(List<Task> tasks, HttpSession session) {
        ZoneId userTimeZoneId;
        User user = UtilUser.getUserFromSession(session);
        if (user.getTimezone() == null) {
            userTimeZoneId = TimeZone.getDefault().toZoneId();
        } else {
            userTimeZoneId = ZoneId.of(user.getTimezone().getID());
        }
        for (Task task : tasks) {
            task.setCreated(task.getCreated().atZone(ZoneId.of("UTC"))
                    .withZoneSameInstant(userTimeZoneId).toLocalDateTime());
        }
    }
}
