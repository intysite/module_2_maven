package lesson7;

import com.google.common.collect.HashBiMap;
import lesson7.task2.Gender;
import lesson7.task2.People;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lesson7 {
    public static void main(String[] args) {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("London", "UK");
        biMap.put("Berlin", "Deutschland");
        System.out.println(biMap);

        //        Условие: дана коллекция строк Arrays.asList(«a1», «a2», «a3», «a1»), давайте посмотрим как её можно
//        обрабатывать используя методы filter, findFirst, findAny, skip и count:
//        Вернуть количество вхождений объекта «a1»
//        Вернуть первый элемент коллекции или 0, если коллекция пуста
//        Вернуть последний элемент коллекции или «empty», если коллекция пуста
//        Найти элемент в коллекции равный «a3» или кинуть ошибку
//        Вернуть третий элемент коллекции по порядку
//        Вернуть два элемента начиная со второго
//        Выбрать все элементы содержащие "1" и поместить их в List

        List<String> strings = Arrays.asList("a1", "a2", "a3", "a1");
        System.out.println(strings.stream().filter(e -> e == "a1").count());
        System.out.println(strings.stream().findFirst().orElse("0"));
        System.out.println(strings.stream().skip(strings.size() - 1).findFirst().orElse("empty"));
        System.out.println(strings.stream().anyMatch(e -> e == "a3"));
        System.out.println(strings.stream().skip(2).findFirst());
        System.out.println(strings.stream().skip(1).limit(2).collect(Collectors.toList()));
        System.out.println(strings.stream().filter(e -> e.contains("1")).collect(Collectors.toList()));

//            -------------------------------------------------------------------------------------------------------

//        Условие: дана коллекция класс People (с полями name — имя,  age — возраст, sex — пол), вида Arrays.asList(
//        new People(«Вася», 16, Sex.MAN), new People(«Петя», 23, Sex.MAN), new People(«Елена», 42, Sex.WOMEN), new
//        People(«Иван Иванович», 69, Sex.MAN)). Давайте посмотрим примеры как работать с таким классом:
//        Выбрать мужчин-военнообязанных (от 18 до 27 лет)
//        Найти средний возраст среди мужчин
//        Найти кол-во потенциально работоспособных людей в выборке (т.е. от 18 лет и учитывая что женщины выходят в
//        55 лет, а мужчина в 60)

        List<People> people = Arrays.asList(new People("Вася", 16, Gender.MAN),
                                            new People("Петя", 23, Gender.MAN),
                                            new People("Елена", 42, Gender.WOMAN),
                                            new People("Анна", 24, Gender.WOMAN),
                                            new People("Иван Иванович", 69, Gender.MAN));
        people.stream()
                .filter(e -> e.getAge() > 18 && e.getAge() < 27 && e.getSex() == Gender.MAN)
                .forEach(System.out::println);
        int rezult = (int) people.stream()
                .filter(e -> e.getSex() == Gender.MAN)
                .map(People::getAge)
                .flatMapToInt(IntStream::of)
                .average().orElse(0);
        System.out.println(rezult);
        System.out.println(people.stream()
                .filter(e -> (e.getAge() < 60 && e.getSex() == Gender.MAN) || (e.getAge() < 55 && e.getSex() == Gender.WOMAN))
                .count());

//            -------------------------------------------------------------------------------------------------------
//        Рассмотрим результаты работы над коллекцией Collection ordered = Arrays.asList(«a1», «a2», «a2», «a3»,
//        «a1», «a2», «a2») и Collection nonOrdered = new HashSet<>(ordered).

//        Получение коллекции без дубликатов из неупорядоченного стрима

//        Получение коллекции без дубликатов из упорядоченного стрима

        List<String> ordered = Arrays.asList("a1", "a2", "a2", "a3", "a1", "a2", "a2");
        HashSet<String> strings1 = new HashSet<>(ordered);
        System.out.println(strings1);

//            -------------------------------------------------------------------------------------------------------


//        Условие: дана коллекция строк Arrays.asList(«a1», «a2», «a3», «a1»), давайте посмотрим, как её можно
//        обрабатывать используя Match функции
//        Найти существуют ли хоть один «a1» элемент в коллекции
//        Найти существуют ли хоть один «a8» элемент в коллекции
//        Найти есть ли символ «1» у всех элементов коллекции
//        Проверить что не существуют ни одного «a7» элемента в коллекции

        System.out.println(strings.stream().anyMatch(e -> e.equals("a1")));
        System.out.println(strings.stream().anyMatch(e -> e.equals("a8")));
        System.out.println(strings.stream().allMatch(e -> e.contains("1")));
        System.out.println(strings.stream().noneMatch(e -> e.equals("a7")));

//            -------------------------------------------------------------------------------------------------------
//        Условие: даны две коллекции collection1 = Arrays.asList(«a1», «a2», «a3», «a1») и collection2 = Arrays
//        .asList(«1,2,0», «4,5»), давайте посмотрим как её можно обрабатывать используя различные map функции
//        Добавить "_1" к каждому элементу первой коллекции
//        В первой коллекции убрать первый символ и вернуть массив чисел (int[])
//        Из второй коллекции получить все числа, перечисленные через запятую из всех элементов
//        Из второй коллекции получить сумму всех чисел, перечисленных через запятую

        List<String> list1 = Arrays.asList("a1", "a2", "a3", "a1");
        List<String> list2 = Arrays.asList("1,2,0", "4,5");
        list1.stream().map(e -> e + "_1").forEach(System.out::println);
        List<Integer> list3 = list1.stream()
                                     .map(e -> e.substring(1))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());
        System.out.println(list3);
        List<Integer> list4 = list2.stream()
                                    .map(e -> List.of(e.split(",")))
                                    .flatMap(e -> e.stream().map(Integer::parseInt))
                                    .collect(Collectors.toList());
        System.out.println(list4);
        System.out.println(list4.stream().reduce(Integer::sum).orElse(0));
//            -------------------------------------------------------------------------------------------------------

//        Условие: даны две коллекции коллекция строк Arrays.asList(«a1», «a4», «a3», «a2», «a1», «a4») и коллекция
//        людей класса People (с полями name — имя, age — возраст, sex — пол), вида Arrays.asList( new People(«Вася»,
//        16, Sex.MAN), new People(«Петя», 23, Sex.MAN), new People(«Елена», 42, Sex.WOMEN), new People(«Иван
//        Иванович», 69, Sex.MAN)). Давайте посмотрим примеры как их можно сортировать:

//        Отсортировать коллекцию строк по алфавиту

//        Отсортировать коллекцию строк по алфавиту в обратном порядке

//        Отсортировать коллекцию строк по алфавиту и убрать дубликаты

//        Отсортировать коллекцию строк по алфавиту в обратном порядке и убрать дубликаты

//        Отсортировать коллекцию людей по имени в обратном алфавитном порядке

//        Отсортировать коллекцию людей сначала по полу, а потом по возрасту
//            -------------------------------------------------------------------------------------------------------

//        Условие: дана коллекция строк Arrays.asList(«a1», «a2», «a3», «a1»), и коллекция класса Peoples из прошлых
//        примеров про Sorted и Filter функции.

//        Найти максимальное значение среди коллекции строк

//        Найти минимальное значение среди коллекции строк

//        Найдем человека с максимальным возрастом

//        Найдем человека с минимальным возрастом
//            -------------------------------------------------------------------------------------------------------

//        Условие: Дана коллекция чисел Arrays.asList(1, 2, 3, 4, 2) выполним над ними несколько действий используя reduce.

//        Получить сумму чисел или вернуть 0

//        Вернуть максимум или -1

//        Вернуть сумму нечетных чисел или 0
//            -------------------------------------------------------------------------------------------------------

//        Условие: Дана коллекция чисел Arrays.asList(1, 2, 3, 4), рассмотрим работу collect и toArray с ней

//        Получить сумму нечетных чисел

//        Вычесть от каждого элемента 1 и получить среднее

//        Прибавить к числам 3 и получить статистику

//        Разделить числа на четные и нечетные
//            -------------------------------------------------------------------------------------------------------

//        Условие: Дана коллекция строк Arrays.asList(«a1», «b2», «c3», «a1»), рассмотрим работу collect и toArray с ней

//        Получение списка без дубликатов

//        Получить массив строк без дубликатов и в верхнем регистре

//        Объединить все элементы в одну строку через разделитель: и обернуть тегами <b>… </b>

//        Преобразовать в map, где первый символ ключ, второй символ значение

//        Преобразовать в map, сгруппировав по первому символу строки

//        Преобразовать в map, сгруппировав по первому символу строки и объединим вторые символы через :

    }
}
