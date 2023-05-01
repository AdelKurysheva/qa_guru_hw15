import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class KonturTest extends TestBase {
    @Test
    @DisplayName("Проверка страницы 'Кандидатам'")
    void testTextForConditions() {

        step("Открываем страницу поиска вакансий", () -> {
            open("/career");
            Thread.sleep(180);
        });
        step("Нажимаем 'Кандидатам'", () -> {
            $("a[href*='career/vacancies/conditions']").click();

        });
        step("Проверяем, что на странице содержится заголовок 'Как попасть на работу в Контур'", () -> {
            $(".content-head__title.content-head__title_size-medium").shouldHave(text("Как попасть на работу в Контур"));
        });
    }

    @Test
    @DisplayName("Выбор города в поиске вакансий")
    void selectCity() {

        step("Открываем страницу поиска вакансий", () -> {
            open("/career");
        });
        step("Выбираем 'Смотреть вакансии'", () -> {
            $("a[href*='https://kontur.ru/career/vacancies/city-5457?category=all']").click();
        });
        step("Открываем список городов", () -> {
            $("a[href*='#careerCityPopup']").click();
        });
        step("Выбираем город", () -> {
            $("a[href*='/career/vacancies/city-6240']").click();
        });
        step("Проверяем, что открыта страница с выбранным городом", () -> {
            $(".selectRegion").shouldHave(text("Санкт-Петербург"));
        });
    }

    @Test
    @DisplayName("Проверка наличия почтового адреса на странице 'Студентам'")
    void testEmail() {

        step("Открываем страницу поиска вакансий", () -> {
            open("/career");
        });
        step("Выбираем 'Смотреть вакансии'", () -> {
            $("a[href*='/education/programs']").click();
        });
        step("Открываем список городов", () -> {
            $(".card.card_size-medium.card_fill-gray").shouldHave(text("Пиши на kontur-student@kontur.ru"));
        });
    }

    @Test
    @DisplayName("Наличие юридического адреса компании")
    void TestAddress() {
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Выбираем 'Контакты'", () -> {
            $("a[href*='/contacts']").click();
        });
        step("Открываем 'Реквизиты компании'", () -> {
            $("a[href*='/rekvizity']").click();
        });
        step("Проверяем, что на странице содержится юридический адрес", () -> {
            $(".content-block").shouldHave(text("620144, Екатеринбург, ул. Народной Воли, 19а."));
        });
    }

    @Test
    @DisplayName("Сообщение о незаполненном поле адреса в Журнале")
    void TestValidationErrorMessage() {
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Выбираем 'Журнал'", () -> {
            open("/");
            $("a[href*='/articles']").click();
        });
        step("Нажимаем кнопку 'Подписаться'", () -> {
            $(".hiddenSubmit").click();
        });
        step("Проверяем, что на странице появилась подсказка о незаполненном поле", () -> {
            $(".field-validation-error").shouldHave(text("Заполните поле"));
        });
    }
}
