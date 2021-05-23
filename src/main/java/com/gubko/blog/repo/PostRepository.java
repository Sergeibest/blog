package com.gubko.blog.repo;

import com.gubko.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

/*Cоздаем репу для манипулирования(управления внесением изменений) таблицей*/
public interface PostRepository extends CrudRepository<Post, Long> {
    /*Тут указываем что наш интерфейс наследует все от интерфейса CrudRepository,
    т. к. у него уже есть все встроеныее функции для изменеия удаления обновленя данных в табличке*/
}
