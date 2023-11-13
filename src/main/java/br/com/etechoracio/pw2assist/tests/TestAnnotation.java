package br.com.etechoracio.pw2assist.tests;

import br.com.etechoracio.pw2assist.entity.Equipamento;
import br.com.etechoracio.pw2assist.entity.OrdemServico;
import jakarta.persistence.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestAnnotation {


    private static final Map<String, FieldDescriptor> FIELD_MAP;

    static {
        FIELD_MAP = new HashMap<>();

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("ID_ORDEM_SERVICO");
            fd.setGenerationType(GenerationType.IDENTITY);
            fd.setAnnotations(Set.of(Id.class, GeneratedValue.class, Column.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("ID_EQUIPAMENTO");
            fd.setAnnotations(Set.of(JoinColumn.class, ManyToOne.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("ID_CLIENTE");
            fd.setAnnotations(Set.of(JoinColumn.class, ManyToOne.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("DT_ENTRADA");
            fd.setAnnotations(Set.of(Column.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("DT_PREVISAO");
            fd.setAnnotations(Set.of(Column.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("TX_DEFEITO");
            fd.setAnnotations(Set.of(Column.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("NR_PRIORIDADE");
            fd.setEnumType(EnumType.ORDINAL);
            fd.setAnnotations(Set.of(Column.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("DT_SAIDA");
            fd.setAnnotations(Set.of(Column.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

        {
            FieldDescriptor fd = new FieldDescriptor();
            fd.setColumnName("TX_STATUS");
            fd.setEnumType(EnumType.STRING);
            fd.setAnnotations(Set.of(Column.class));
            FIELD_MAP.put(fd.getColumnName(), fd);
        }

    }



    public static void main(String[] args) {
        AtomicInteger pontos = new AtomicInteger();
        Class<?> clazz = OrdemServico.class;


        if (clazz.isAnnotationPresent(Entity.class)) {
            pontos.getAndIncrement();
            System.out.println("Ponto Entity - " + pontos);
        }

        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            if ("TBL_ORDEM_SERVICO".equals(table.name())) {
                pontos.getAndIncrement();
                System.out.println("Ponto Table - " + pontos);
            }
        }

        List<Field> allFields = Arrays.asList(clazz.getDeclaredFields()).stream().filter(e -> e.isAnnotationPresent(Column.class))
                                                                                             .collect(Collectors.toList());

        allFields.forEach(e -> {
            System.out.println(">>>>>> " + e.getName());
            FieldDescriptor fieldDescriptor = getFieldDescriptor(e);
            if (fieldDescriptor == null) {
                return;
            }

            if (e.isAnnotationPresent(Enumerated.class)) {
                if (fieldDescriptor.isEnum()) {
                    if (fieldDescriptor.getEnumType() == e.getAnnotation(Enumerated.class).value()) {
                        pontos.getAndIncrement();
                        System.out.println("Ponto Enumerated - " + pontos);
                    }
                }
            }
            if (e.isAnnotationPresent(Id.class)) {
                GeneratedValue generatedValue = e.getAnnotation(GeneratedValue.class);
                if (generatedValue != null && fieldDescriptor.getGenerationType().equals(generatedValue.strategy())) {
                    pontos.getAndIncrement();
                    System.out.println("Ponto GenerationType - " + pontos);
                }
            }

            Stream.of(e.getDeclaredAnnotations()).forEach(i -> {
                if (fieldDescriptor.getAnnotations().contains(i.annotationType())) {
                    pontos.getAndIncrement();
                    System.out.println("Ponto " + i.annotationType() + " - " + pontos);
                }
            });
        });





        System.out.println("Total: " + pontos);

    }

    private static FieldDescriptor getFieldDescriptor(Field e) {
        Column column = e.getAnnotation(Column.class);
        FieldDescriptor fieldDescriptor = FIELD_MAP.get(column.name());
        if (fieldDescriptor != null) {
            return fieldDescriptor;
        }
        JoinColumn joinColumn = e.getAnnotation(JoinColumn.class);
        return FIELD_MAP.get(joinColumn.name());
    }


}


class FieldDescriptor {
    private String columnName;
    private EnumType enumType;
    private GenerationType generationType;

    private Set<Class<? extends Annotation>> annotations = new HashSet<>();

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Set<Class<? extends Annotation>> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Set<Class<? extends Annotation>> annotations) {
        this.annotations = annotations;
    }

    public EnumType getEnumType() {
        return enumType;
    }

    public void setEnumType(EnumType enumType) {
        this.enumType = enumType;
    }

    public boolean isEnum() {
        return enumType != null;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }
}