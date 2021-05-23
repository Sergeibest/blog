package com.gubko.blog.controllers;


import com.gubko.blog.models.Post;
import com.gubko.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller /*это анотоция*/
public class BlogController {

    /*создаем перемунную которая будетссылаться на репозиторий PostRepository созадее эту переменную через Autowired
    * */
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/") /*Если мы хотим чтобы запрос обрабатывал информацию с главной страницы оставляем просто "/" */
    public String home(Model model) {
        model.addAttribute("title", "Главная страница"); /*Тут указываем имя параметра который будет передан в шаблон*/
        return "main-page";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Cтраница про нас ");
        return "about";
    }

    @GetMapping("/blog") /*Это нужно для того чтобы отслеживать какой - то u - rl адрес*/
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll(); /*указываем модель получения данных массив значений которые будут получены из БД*/
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/blog";

    }

    @GetMapping("/login")
    public String blogLogin(Model model) {
        return "login";
    }
    /**/
    @GetMapping("/blog/{id}")/*Достаем нужный id статьи*/
    public String blogDetails(
        /*анотацией @PathVariable берем динамическое значение из URL адреса
        и в параметрах указываем какой дин парм принимаем */
            @PathVariable(value = "id") long id, Model model) {
        /*Идем в бд и ищем там нужную нам запись по айди. Помещаем полученый id в объект на оснвое класса
         * Optional и указываем модель с которой мы будем работать для передачи его в шаблон*/
        if (!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        /*Чтобы шаблону было проще работать с объктом Optional<Post> post и не было ошибок
 переводим(преобразовываем) его в класс ArrayList  */
        ArrayList<Post> res= new ArrayList<>();
        /*Теперь обращаемся к post и говорим что мы сейчас
        все переводим из класса Optional в ArrayList*/
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)){
            return "redirect:/blog";
        }
    Optional<Post> post = postRepository.findById(id);
    ArrayList<Post> res= new ArrayList<>();
    post.ifPresent(res::add);
    model.addAttribute("post",res);
    return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow(null); /*такая запись потомучто 8-ка не поддерживает метод orElseThrow без аргуменента*/
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(null); /*+ еще костыль*/
        postRepository.delete(post);
        return "redirect:/blog";
    }


}
