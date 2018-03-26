package app;

import app.common.Awards;
import app.domain.Book;
import app.repo.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer(@Value ("${custom.http.port}") int httpPort) {
        UndertowServletWebServerFactory undertow = new UndertowServletWebServerFactory();
        undertow.addBuilderCustomizers(builder -> builder.addHttpListener(httpPort, "0.0.0.0"));
        return undertow;
    }

    @Bean
    public CommandLineRunner saveSomeBooks(BookRepo bookRepo) {
        return args -> {
            for (int i = 0; i < 40; i++) {
                bookRepo.save(new Book(null, "Okuyoruz " + i, "Yazıyoruz " + i,
                        "tr_TR " + i, String.valueOf(i * 50), LocalDate.now().minusMonths(i),
                        getAward(i)));
            }
            log.info("Finished creating dummy book records");
        };
    }

    private Awards getAward(int index) {
        switch (index % 5) {
            case 0:
                return Awards.BEST_BOOK;
            case 1:
                return Awards.WORST_BOOK;
            case 2:
                return Awards.GOOD_ENOUGH;
            case 3:
                return Awards.CANT_GET_ANY_WORSE;
            case 4:
                return Awards.MADE_RICH;
            default:
                return null;
        }
    }

}