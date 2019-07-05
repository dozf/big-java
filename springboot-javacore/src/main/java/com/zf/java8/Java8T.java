package com.zf.java8;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zf
 * @create 2019-03-12 17:48
 */
public class Java8T {

    public static void main(String[] args){
        //testList();
        testListSort();
        //testList2Map();
        //testList2MapSort();
        //testListFilter();
        //testListGroup();
    }

    // list类型转换
    public static  void  testList(){
        List<Integer> list = Arrays.asList(new Integer[]{1,9,4,6,2,7,5,3});  //构造list，填充值

        List<String> list2 = list.stream().map(e -> {
            return e+"ss";
        }).collect(Collectors.toList());

        list2.stream().forEach(
                e -> System.out.println(e)
        );

        System.out.println("=========================");

        List<B> list3 = new ArrayList<>();
        list3.add(new B(31,"31"));
        list3.add(new B(15,"15"));
        list3.add(new B(12,"12"));
        list3.add(new B(16,"16"));
        list3.add(new B(6,"6"));

        list3.stream().forEach(e->{
            if(e.age <10){
                e.setName("我666");
            }
        });
        list3.stream().forEach(
                e -> System.out.println(e)
        );
    }


    // list排序
    public static  void  testListSort(){
        List<Integer> list = Arrays.asList(new Integer[]{1,9,4,6,2,7,5,3});  //构造list，填充值
        list  = list .stream().sorted(Integer::compareTo).collect(Collectors.toList());

        list.stream().forEach(
                e -> System.out.println(e)
        );

        System.out.println("=========================");
        List<B> list2 = new ArrayList<>();
        list2.add(new B(31,"31"));
        list2.add(new B(15,"15"));
        list2.add(new B(12,"12"));
        list2.add(new B(16,"16"));
        list2.add(new B(6,"6"));

        //正序
        list2.sort((B b1, B b2) -> b1.getAge().compareTo(b2.getAge()));

        //倒序
        //list2.sort((B b1, B b2) -> b2.getAge().compareTo(b1.getAge()));
        list2.sort(Comparator.comparing(B::getAge).reversed());
        //通过流进行排序
        //list2  = list2 .stream().sorted(Comparator.comparing(B::getAge)).collect(Collectors.toList());
        list2.stream().forEach(
                e -> System.out.println(e)
        );

        List<ComBean> list3 = new ArrayList<>();
        list3.add(ComBean.builder().name("a").age(12).innerBean(ComBean.InnerBean.builder().bname("123").price(new BigDecimal("1.6")).build()).build());
        list3.add(ComBean.builder().name("b").age(35).innerBean(ComBean.InnerBean.builder().bname("1234").price(new BigDecimal("33.6")).build()).build());
        list3.add(ComBean.builder().name("c").age(22).innerBean(ComBean.InnerBean.builder().bname("12346").price(new BigDecimal("6.6")).build()).build());
        list3.add(ComBean.builder().name("d").age(82).innerBean(ComBean.InnerBean.builder().bname("134").price(new BigDecimal("53.6")).build()).build());
        list3.add(ComBean.builder().name("e").age(72).innerBean(ComBean.InnerBean.builder().bname("14").price(new BigDecimal("31.6")).build()).build());

        //根据内部类的属性排序，内部类不能为空
        //list3.add(ComBean.builder().name("e").age(99).build());


        //list3.sort(Comparator.comparing(ComBean::getAge));
        list3.sort(Comparator.comparing(ComBean::getSort));
        list3.stream().forEach(System.out::println);
    }

    public static  void testList2Map(){
        List<B> list = new ArrayList<>();
        list.add(new B(31,"31"));
        list.add(new B(15,"15"));
        list.add(new B(12,"12"));
        list.add(new B(16,"16"));
        list.add(new B(6,"6"));

       Map<String,B> map = list.stream().collect(Collectors.toMap(B::getName, Function.identity()));

       System.out.println("map:"+map);
    }

    // list 转map ,并排序
    public static  void testList2MapSort(){
        List<B> list = new ArrayList<>();
        list.add(new B(31,"31"));
        list.add(new B(15,"15"));
        list.add(new B(12,"12"));
        list.add(new B(16,"16"));
        list.add(new B(6,"6"));

        Map<Integer,B> map = new LinkedHashMap<>();

        list.stream()
                //正序
                //.sorted((e1,e2)->Integer.compare(e1.getAge(),e2.getAge()))
                .sorted(Comparator.comparing(B::getAge))
                .forEachOrdered(x -> map.put(x.getAge(), x));

        System.out.println("map"+map);

        for (Map.Entry<Integer,B> entry : map.entrySet()) {
            System.out.println("age= " + entry.getKey() + "   name= " + entry.getValue().getName());
        }

    }

    public static  void testListFilter(){
        List<Integer> list = Arrays.asList(new Integer[]{1,9,4,6,2,7,5,3});  //构造list，填充值

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"1");
        map.put(3,"3");
        map.put(9,"9");

        //过滤list ，只取存在map中的值
        List<Integer> list2 = list.stream().filter(x -> map.containsKey(x)).collect(Collectors.toList());

        list2.stream().forEach(
                e -> System.out.println(e)
        );

    }

    /**
     * 分组
     */
    public static  void testListGroup(){
        List<B> list = new ArrayList<>();
        list.add(new B(1,"1A"));
        list.add(new B(1,"1B"));
        list.add(new B(12,"12"));
        list.add(new B(16,"16A"));
        list.add(new B(16,"16B"));

        // 单按状态分组
        Map<Integer, List<B>> map = list.stream().
                collect(Collectors.groupingBy(B::getAge));
        System.out.println(map);

        for (Map.Entry<Integer,List<B>> entry : map.entrySet()) {
            System.out.println(" age= " + entry.getKey()+"人：");
            entry.getValue().stream().forEach(e->System.out.println(e.name));
        }

    }


    static class B{
        private Integer age;
        private String name;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public B(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "B{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
