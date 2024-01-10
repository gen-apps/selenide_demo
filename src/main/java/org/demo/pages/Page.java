package org.demo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.demo.utils.Self;
import org.openqa.selenium.By;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Objects;
import java.util.function.Consumer;

public class Page<T extends Page<T>> implements Self<T> {


    public SelenideElement body() {
        return $("body");
    }

    public T job(Consumer<T> consumer) {
        consumer.accept(self());

        return self();
    }

    public T delay(Duration duration) {
        if (!duration.isNegative()) {
            Selenide.sleep(duration.toMillis());
        }

        return self();
    }

    public ElementsCollection $$(String selector) {
        return Selenide.$$(selector);
    }

    public ElementsCollection $$(By by) {
        return Selenide.$$(by);
    }

    public SelenideElement $(String selector) {
        return Selenide.$(selector);
    }

    public SelenideElement $(By by) {
        return Selenide.$(by);
    }

    public static <P extends Page<P>> P loadModel(@NonNull Class<P> klass) {
        Objects.requireNonNull(klass);

        try {
            return klass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
