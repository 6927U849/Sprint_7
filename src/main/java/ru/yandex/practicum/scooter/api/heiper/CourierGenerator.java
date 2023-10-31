package ru.yandex.practicum.scooter.api.heiper;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;


public class CourierGenerator {
    public static CreateCourierRequest getRandomCourier() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);

        return new CreateCourierRequest(login, password, firstName);
    }

}
