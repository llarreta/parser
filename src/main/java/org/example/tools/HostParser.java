package org.example.tools;

import org.example.annotations.AnnotationsTool;
import org.example.annotations.HostEntity;
import org.example.annotations.HostFieldDate;
import org.example.annotations.HostFieldString;
import org.example.exceptions.HostParserException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HostParser<T extends  HashMap<String, Object>> {

    Map<Class, HostParserWorker> workers;
    Map<Class, HostParserDataComparable> comparables;


    public HostParser(){
        workers = new HashMap<>();
        workers.put(HostFieldString.class, new HostParserStringWorker());
        workers.put(HostFieldDate.class, new HostParserDateWorker());

        comparables = new HashMap<>();
        comparables.put(HostFieldString.class, new HostFieldStringDataComparable());
        comparables.put(HostFieldDate.class, new HostFieldDateDataComparable());
    }


    public T parse(String hostResponseText, Class<T> hostEntityAnnotatedClass) throws HostParserException {

        T response;

        if (Objects.isNull(hostResponseText) || hostResponseText.isEmpty() || Objects.isNull(hostEntityAnnotatedClass)){
            throw new HostParserException("hostResponseText & hostEntityAnnotatedClass are needed");
        }

        if (!hostEntityAnnotatedClass.isAnnotationPresent(HostEntity.class)){
            throw new HostParserException("this class is not hostEntityAnnotatedClass");
        }

        try {
            response = hostEntityAnnotatedClass.getConstructor().newInstance();
        } catch (Exception e){
            throw new HostParserException("cant instantiate hostEntityAnnotatedClass");
        }

        Arrays.stream(hostEntityAnnotatedClass.getDeclaredMethods()).forEach(new Consumer<Method>() {
            @Override
            public void accept(Method method) {
                processField(hostResponseText, response, method);
            }
        });


        return response;
    }

    private void processField(String hostResponseText, T response, Method method) {
        Arrays.stream(method.getAnnotations()).filter(new Predicate<Annotation>() {
            @Override
            public boolean test(Annotation annotation) {
                // Filtrar para que solo queden los tipos de anotaciones propias de HostEntity
                return  AnnotationsTool.isKnowedAnnotation(annotation);
            }
        }).forEach(new Consumer<Annotation>() {
            @Override
            public void accept(Annotation annotation) {
               HostParserWorker worker = workers.get(annotation.annotationType());
               if (worker!=null){
                   worker.process(hostResponseText, response, annotation);
               }
            }
        });
    }

    public String toString(T obj){
        StringBuilder builder = new StringBuilder();
        Collection<Annotation> knowedAnnotations = new ArrayList<>();

        // Nos quedamos solo con las anotaciones conocidas
        Arrays.stream(obj.getClass().getDeclaredMethods()).forEach(new Consumer<Method>() {
            @Override
            public void accept(Method method) {
                knowedAnnotations.addAll(Arrays.stream(method.getAnnotations()).filter(new Predicate<Annotation>() {
                    @Override
                    public boolean test(Annotation annotation) {
                        // Filtrar para que solo queden los tipos de anotaciones propias de HostEntity
                        return AnnotationsTool.isKnowedAnnotation(annotation);
                    }
                }).collect(Collectors.toList()));
            }
        });

        // Ordenamos las anotaciones por el start y luego generamos el string de salida
        knowedAnnotations.stream().sorted(new Comparator<Annotation>() {
                    @Override
                    public int compare(Annotation o1, Annotation o2) {
                        // Se ordenan las anotaciones
                        HostParserDataComparable comparable1 = comparables.get(o1.getClass());
                        HostParserDataComparable comparable2 = comparables.get(o2.getClass());
                        if ((comparable1!=null) && (comparable2!=null)) {
                            if (comparable1.start(o1) < comparable2.start(o2)){
                                return 1;
                            }
                            return -1;
                        }
                        return 0;
                    }
                }).forEach(new Consumer<Annotation>() {
                    @Override
                    public void accept(Annotation annotation) {
                        HostParserWorker worker = workers.get(annotation.annotationType());
                        if (worker!=null){
                            // Agregamos cada campo al String final
                            builder.append(worker.fieldToString(obj, annotation));
                        }
                    }
                });


        return builder.toString();
    }

}
