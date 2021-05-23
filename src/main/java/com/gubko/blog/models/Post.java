package com.gubko.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*Создаем модель БД*/
/*чтобы сказать что мы создали модель мы должны прописать анатоцию  Entity*/
@Entity
public class Post {

    @Id /*указываем что это будет уникальный идентификатор. Важно указать что это будет идти из либы javax.persistence. */
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*это позволит при добавелнии каждой записи генерировать значение нового поля.
      Пример добавили новую статью и у нас это уже будет с новым ID + добавили авто генерецию */
    private Long id;

    private String title, anons, full_text;
    private int views;


    public Post() { /*пустой конструктор - чтобы не ломалось - ни фига не понял зачем это костыль*/
    }

    /*Создаем методы который будет получать значения из полей
    и устанавливать их значения в каждое из полей*/


    /*Для ревью!
    Почему не ламбок? С ними не знаю пока как работать*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }
    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
    /*Метод для views*/
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    /*Далее нужно создать репу для манипулирования(управления внесением изменений) таблицей*/

    public Post(String title, String anons, String full_text) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;

    }
}
