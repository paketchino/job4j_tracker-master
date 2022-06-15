package ru.job4j.samopoznanye;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ArticleTest {

    @Test
    public void generateBy() {
        assertThat(
                Article.generateBy("Мама мыла раму и окно",
                        "мыла окно"
                ),
                is(true));
    }

    @Test
    public void generateBy2() {
        assertThat(Article.generateBy("Мама мыла раму и окно", "мыла пол"), is(false));
    }

    @Test
    public void whenLongTextTrue() {
        assertThat(
                Article.generateBy(
                        "Мой дядя самых честных правил, "
                                + "Когда не в шутку занемог, "
                                + "Он уважать себя заставил "
                                + "И лучше выдумать не мог. "
                                + "Его пример другим наука; "
                                + "Но, боже мой, какая скука "
                                + "С больным сидеть и день и ночь, "
                                + "Не отходя ни шагу прочь! "
                                + "Какое низкое коварство "
                                + "Полуживого забавлять, "
                                + "Ему подушки поправлять, "
                                + "Печально подносить лекарство, "
                                + "Вздыхать и думать про себя: "
                                + "Когда же черт возьмет тебя!",
                        "Мой дядя мог думать про тебя и день и ночь"
                ),
                is(true)
        );

    }

    @Test
    public void whenLongTextFalse() {
        assertThat(
                Article.generateBy(
                        "Мой дядя самых честных правил, "
                                + "Когда не в шутку занемог, "
                                + "Он уважать себя заставил "
                                + "И лучше выдумать не мог. "
                                + "Его пример другим наука; "
                                + "Но, боже мой, какая скука "
                                + "С больным сидеть и день и ночь, "
                                + "Не отходя ни шагу прочь! "
                                + "Какое низкое коварство "
                                + "Полуживого забавлять, "
                                + "Ему подушки поправлять, "
                                + "Печально подносить лекарство, "
                                + "Вздыхать и думать про себя: "
                                + "Когда же черт возьмет тебя!",
                        "Мой дядя мог думать про Linux и Java день и ночь"
                ),
                is(false)
        );
    }

    @Test
    public void whenTextFalse() {
        assertThat(Article.generateBy("a b c d", "Мой дядя мыл апельсин"), is(false));
    }

    @Test
    public void whenTextTrue() {
        assertThat(Article.generateBy("a b c d", "a"), is(true));
    }

    @Test
    public void whenTextTr() {
        assertThat(Article.generateBy("1234 56 78", "56"), is(true));
    }

}